from flask import Flask, jsonify, request, send_file
from model.user import User
from model.session import Session
from model.car import Car
from model.car_images import Car_images
from model.filter import Filter

from ext import db
from rdw import RDW
from wikimedia import Wikimedia

import uuid, hashlib, urllib.request

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://admin:password@localhost/vroomrr'
db.init_app(app)


@app.route('/register', methods=['POST'])
def register():
	user = User.from_dict(request.json)
	# Check if values are correct
	if user.name == User.name or user.password == User.password or user.username == User.username:
		return jsonify({"error": "Please provide a valid name, username and password"})

	user.user_id = str(uuid.uuid4())
	# Hash password using ID as salt
	user.password = hashlib.sha256((user.user_id + user.password).encode('utf-8')).hexdigest()
	# Add new user to database
	db.session.add(user)
	db.session.commit()
	return jsonify(user)


@app.route('/login', methods=['POST'])
def login():
	tmpuser = User.from_dict(request.json)
	user = User.query.filter_by(username=tmpuser.username).first()
	# Check if user exists
	if user is None:
		return jsonify({"error": "Wrong username or password"})
	# Check user password
	if user.password == hashlib.sha256((user.user_id + tmpuser.password).encode('utf-8')).hexdigest():
		session = Session(user.user_id, str(uuid.uuid4()))
		# Add new session to database
		db.session.add(session)
		# Check if filter rules exists for user if not create empty rules
		car_filter = Filter.query.filter_by(user_id=user.user_id).first()
		if not car_filter:
			db.session.add(Filter(user_id=user.user_id, build_year_max=9999, build_year_min=1900, fuel_types='', colors='', brands='', horsepower_min=0))
		db.session.commit()
		return jsonify(session)
	else:
		return jsonify({"error": "Wrong username or password"})


# Returns user profile using UID
@app.route('/user', methods=['POST'])
def getUser():
	user = User.from_dict(request.json)
	user_self = checkSession(request)
	# Check session is valid
	if user_self:
		# If no user ID or user_id field provided, get data for current session user
		if user.user_id == "" or not isinstance(user.user_id, str):
			user = user_self
		userdata = User.query.filter_by(user_id=user.user_id).first()
		# Check if user exists
		if userdata is None:
			return jsonify({"error": "This user does not exist"})
		# Remove hashed password from output
		userdata.password = ''
		return jsonify(userdata)
	else:
		return jsonify({"error": "Session has expired or does not exist"})


# Returns match candidates using UID
# TODO add /candidates/chats
# TODO add /chat and /chat/messages and /chat/send
@app.route('/candidates')
def getCandidates():
	user_self = checkSession(request)
	# Check session is valid
	if user_self:
		candidates = User.query.filter(User.user_id != user_self.user_id).all()
		p = Filter.query.filter(Filter.user_id == user_self.user_id).first()
		# Add selected car to data for every user and remove password
		data = []
		for cand in candidates:
			cand.password = ''
			car = Car.query.filter(Car.user_id == cand.user_id, Car.selected).first()
			images = Car_images.query.filter(Car_images.license_plate == car.license_plate).all()
			if (p.colors.lower().find(car.color.lower().strip()) != -1 or p.colors == '') \
					and (p.brands.lower().find(car.brand.lower().strip()) != -1 or p.brands == '') \
					and (p.fuel_types.lower().find(car.fuel_type.lower().strip()) != -1 or p.fuel_types == '') \
					and (car.horsepower > p.horsepower_min) \
					and (p.build_year_min <= car.build_year) \
					and (p.build_year_max >= car.build_year):
				data.append({'user': cand, 'car': car, 'car_images': images})
		return jsonify(data)
	else:
		return jsonify({"error": "Session has expired or does not exist"})


# Get session user filter used for match candidates
@app.route('/candidates/filter')
def getUserFilter():
	user = checkSession(request)
	if user:
		# Check if filter rules exists for user
		car_filter = Filter.query.filter_by(user_id=user.user_id).first()
		if not car_filter:
			return jsonify("error", "No filter found, something very wong")
		else:
			return jsonify(car_filter)
	else:
		return jsonify({"error": "Session has expired or does not exist"})


# Update filter information for current session user
@app.route('/candidates/filter/update', methods=['POST'])
def updateFilter():
	n_filter = Filter.from_dict(request.json)
	if n_filter.build_year_max == "" or n_filter.build_year_min == "" or n_filter.horsepower_min == "":
		return jsonify(
			{"error": "years, and horsepower cannot be empty"})
	user = checkSession(request)
	if user:
		# Check if filter rules exists for user if not create filter
		if not Filter.query.filter_by(user_id=user.user_id).first():
			db.session.add(n_filter)
		else:
			Filter.query.filter(Filter.user_id == user.user_id).update({Filter.brands: n_filter.brands, Filter.build_year_max: n_filter.build_year_max,
																		Filter.build_year_min: n_filter.build_year_min, Filter.colors: n_filter.colors,
																		Filter.fuel_types: n_filter.fuel_types, Filter.horsepower_min: n_filter.horsepower_min })
		db.session.commit()
		return jsonify(Filter.query.filter_by(user_id=user.user_id).first())
	else:
		return jsonify({"error": "Session has expired or does not exist"})

# Returns all cars for specific UID
@app.route('/cars', methods=['POST'])
def getCars():
	user = User.from_dict(request.json)
	user_self = checkSession(request)
	# Check session is valid
	if user_self:
		# If no user ID or user_id field provided, get cars for current session user
		if user.user_id == "" or not isinstance(user.user_id, str):
			user = user_self
		cars = Car.query.filter_by(user_id=user.user_id).all()
		return jsonify(cars)
	else:
		return jsonify({"error": "Session has expired or does not exist"})


# Add car based on license plate
@app.route('/cars/add/<kenteken>')
def addCar(kenteken):
	# Check session is valid
	user = checkSession(request)
	if user:
		car = RDW().getVehicle(kenteken)
		car_fuel = RDW().getVehicleFuel(kenteken)
		if car:
			newcar = Car()
			newcar.license_plate = car['kenteken']
			newcar.brand = car['merk']
			newcar.build_year = car['datum_eerste_afgifte_nederland']
			newcar.color = car['eerste_kleur']
			newcar.fuel_type = car_fuel['brandstof_omschrijving']
			newcar.selected = False
			newcar.type = car['handelsbenaming']
			newcar.user_id = user.user_id
			newcar.description = Wikimedia().getDescription(car['merk'] + ' ' + car['handelsbenaming'])
			# convert KW to HP
			if 'nettomaximumvermogen' in car_fuel:
				newcar.horsepower = int(float(car_fuel['nettomaximumvermogen']) * 1.3410220924)
			# Check if electric car
			if 'netto_max_vermogen_elektrisch' in car_fuel:
				newcar.horsepower = int(float(car_fuel['netto_max_vermogen_elektrisch']) * 1.3410220924)
			db.session.add(newcar)

			# Download image for car from Wikimedia
			image_id = str(uuid.uuid4())
			urllib.request.urlretrieve(Wikimedia().getImageUrl(car['merk'] + ' ' + car['handelsbenaming']), 'car_images/' + image_id + '.jpg')
			image = Car_images()
			image.car_images_id = image_id
			image.license_plate = newcar.license_plate
			image.image = 'car_images/' + image_id + '.jpg'

			db.session.add(image)
			db.session.commit()
			return jsonify(newcar)
		return jsonify({"error": "License plate not found"})
	else:
		return jsonify({"error": "Session has expired or does not exist"})


# Update car information for current session user
@app.route('/cars/update', methods=['POST'])
def updateCar():
	car = Car.from_dict(request.json)
	if car.brand == "" or car.build_year == "" or car.color == "" or car.fuel_type == "" or car.horsepower == "" or car.type == "" or car.selected == "" or car.description == "":
		return jsonify(
			{"error": "brand, year, color, fueltype, horsepower, description or car type cannot be empty"})
	# Check session is valid
	user = checkSession(request)
	if user:
		# Check if car exists
		tmpcar = Car.query.filter_by(license_plate=car.license_plate).first()
		if not tmpcar:
			return jsonify({"error": "Car not found"})
		# Make sure user has owns this car
		if user.user_id == tmpcar.user_id:
			update = Car.query.filter_by(license_plate=car.license_plate).update({Car.brand: car.brand, Car.build_year: car.build_year, Car.color: car.color, Car.fuel_type: car.fuel_type, Car.horsepower: car.horsepower, Car.type: car.type, Car.selected: car.selected, Car.description: car.description})
			db.session.commit()
			if car.selected:
				Car.query.filter(Car.license_plate != car.license_plate, Car.user_id == user.user_id).update(
					{Car.selected: False})
				db.session.commit()
			if update:
				return jsonify(Car.query.filter_by(license_plate=car.license_plate).first())
			else:
				return jsonify({"error": "Update failed"})
		else:
			return jsonify({"error": "Insufficient rights you do not own this car"})
	else:
		return jsonify({"error": "Session has expired"})


# Delete car for current session user
@app.route('/cars/delete', methods=['POST'])
def deleteCar():
	car = Car.from_dict(request.json)
	if car.license_plate == "":
		return jsonify({"error": "license plate cannot be empty"})
	# Check session is valid
	user = checkSession(request)
	if user:
		# Check if car exists
		tmpcar = Car.query.filter_by(license_plate=car.license_plate).first()
		if not tmpcar:
			return jsonify({"error": "Car not found"})
		# Make sure user has owns this car
		if user.user_id == tmpcar.user_id:
			delete = Car.query.filter_by(license_plate=car.license_plate).delete()
			db.session.commit()
			if delete:
				return jsonify({"message": "Car deleted"})
			else:
				return jsonify({"error": "Delete failed"})
		else:
			return jsonify({"error": "Insufficient rights you do not own this car"})
	else:
		return jsonify({"error": "Session has expired"})


@app.route('/image/<id>')
def getImage(id):
	img = Car_images.query.filter_by(car_images_id=id).first()
	if img:
		return send_file(img.image, mimetype='image/jpg')
	else:
		return jsonify({"error": "Image not found"})


# Returns all images for specific license plate
@app.route('/cars/images', methods=['POST'])
def getCarImages():
	car = Car.from_dict(request.json)
	if car.license_plate == "":
		return jsonify({"error": "license plate cannot be empty"})
	# Check session is valid
	if checkSession(request):
		images = Car_images.query.filter_by(license_plate=car.license_plate).all()
		return jsonify(images)
	else:
		return jsonify({"error": "Session has expired or does not exist"})


def checkSession(req):
	session = Session.query.filter_by(session_id=req.headers['Session-Id']).first()
	if session is None:
		return False
	else:
		return session


if __name__ == "__main__":
	app.run(threaded=True, host='0.0.0.0')
