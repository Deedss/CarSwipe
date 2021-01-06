from flask import Flask, jsonify, request, send_file
from model.user import User
from model.session import Session
from model.car import Car
from model.car_images import Car_images

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
        return jsonify({"error": "Please provide a valid name, username and password"}), 400

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
    if user == None:
        return jsonify({"error": "Wrong username or password"}), 403
    # Check user password
    if user.password == hashlib.sha256((user.user_id + tmpuser.password).encode('utf-8')).hexdigest():
        session = Session(user.user_id, str(uuid.uuid4()))
        # Add new session to database
        db.session.add(session)
        db.session.commit()
        return jsonify(session)
    else:
        return jsonify({"error": "Wrong username or password"}), 403


# Returns user profile using UID
@app.route('/user/<id>')
def getUser(id):
    # Check session is valid
    if checkSession(request):
        user = User.query.filter_by(user_id=id).first()
        # Check if user exists
        if user == None:
            return jsonify({"error": "This user does not exist"}), 404
        # Remove hashed password from output
        user.password = ''
        return jsonify(user)
    else:
        return jsonify({"error": "Session has expired or does not exist"}), 403


# Returns all cars for specific UID
@app.route('/cars', methods=['POST'])
def getCars():
    user = User.from_dict(request.json)
    user_self = checkSession(request)
    # Check session is valid
    if user_self:
        if user.user_id == "":
            user = user_self
        cars = Car.query.filter_by(user_id=user.user_id).all()
        return jsonify(cars)
    else:
        return jsonify({"error": "Session has expired or does not exist"}), 403


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
            urllib.request.urlretrieve(Wikimedia().getImageUrl(car['merk'] + ' ' + car['handelsbenaming']),
                                       'car_images/' + image_id + '.jpg')
            image = Car_images()
            image.car_images_id = image_id
            image.license_plate = newcar.license_plate
            image.image = 'car_images/' + image_id + '.jpg'

            db.session.add(image)
            db.session.commit()
            return jsonify(newcar)
    else:
        return jsonify({"error": "Session has expired or does not exist"}), 403


# Update car information for current session user
@app.route('/cars/update', methods=['POST'])
def updateCar():
    car = Car.from_dict(request.json)
    if car.brand == "" or car.build_year == "" or car.color == "" or car.fuel_type == "" or car.horsepower == "" or car.type == "" or car.selected == "" or car.description == "":
        return jsonify(
            {"error": "brand, year, color, fueltype, horsepower, description or car type cannot be empty"}), 400
    # Check session is valid
    user = checkSession(request)
    if user:
        # Check if car exists
        tmpcar = Car.query.filter_by(license_plate=car.license_plate).first()
        if not tmpcar:
            return jsonify({"error": "Car not found"}), 403
        # Make sure user has owns this car
        if user.user_id == tmpcar.user_id:

            update = Car.query.filter_by(license_plate=car.license_plate).update({Car.brand: car.brand,
                                                                                  Car.build_year: car.build_year,
                                                                                  Car.color: car.color,
                                                                                  Car.fuel_type: car.fuel_type,
                                                                                  Car.horsepower: car.horsepower,
                                                                                  Car.type: car.type,
                                                                                  Car.selected: car.selected,
                                                                                  Car.description: car.description})
            db.session.commit()

            if car.selected:
                Car.query.filter(Car.license_plate != car.license_plate, Car.user_id == user.user_id).update(
                    {Car.selected: False})
                db.session.commit()

            if update:
                return jsonify(Car.query.filter_by(license_plate=car.license_plate).first())
            else:
                return jsonify({"error": "Update failed"}), 403

        else:
            return jsonify({"error": "Insufficient rights you do not own this car"}), 403
    else:
        return jsonify({"error": "Session has expired"}), 403


# Delete car for current session user
@app.route('/cars/delete', methods=['POST'])
def deleteCar():
    car = Car.from_dict(request.json)
    if car.license_plate == "":
        return jsonify({"error": "license plate cannot be empty"}), 400
    # Check session is valid
    user = checkSession(request)
    if user:
        # Check if car exists
        tmpcar = Car.query.filter_by(license_plate=car.license_plate).first()
        if not tmpcar:
            return jsonify({"error": "Car not found"}), 403
        # Make sure user has owns this car
        if user.user_id == tmpcar.user_id:

            delete = Car.query.filter_by(license_plate=car.license_plate).delete()
            db.session.commit()

            if delete:
                return jsonify({"message": "Car deleted"})
            else:
                return jsonify({"error": "Delete failed"}), 403

        else:
            return jsonify({"error": "Insufficient rights you do not own this car"}), 403
    else:
        return jsonify({"error": "Session has expired"}), 403


@app.route('/image/<id>')
def getImage(id):
    img = Car_images.query.filter_by(car_images_id=id).first()
    if img:
        return send_file(img.image, mimetype='image/jpg')
    else:
        return jsonify({"error": "Image not found"}), 404


# Returns all images for specific license plate
@app.route('/cars/images', methods=['POST'])
def getCarImages():
    car = Car.from_dict(request.json)
    if car.license_plate == "":
        return jsonify({"error": "license plate cannot be empty"}), 400
    # Check session is valid
    if checkSession(request):
        images = Car_images.query.filter_by(license_plate=car.license_plate).all()
        return jsonify(images)
    else:
        return jsonify({"error": "Session has expired or does not exist"}), 403


def checkSession(req):
    session = Session.query.filter_by(session_id=req.headers['Session-Id']).first()
    if session == None:
        return False
    else:
        return session


if __name__ == "__main__":
    app.run(threaded=True, host='0.0.0.0')