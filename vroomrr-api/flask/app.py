from flask import Flask, jsonify, request
from model.user import User
from model.session import Session

from ext import db
import uuid, hashlib

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://admin:password@localhost/vroomrr'
db.init_app(app)
 
@app.route('/register', methods=['POST'])
def register():
	user = User.from_dict(request.json)
	#Check if values are correct
	if user.name == User.name or user.password == User.password or user.username == User.username:
		return jsonify({ "error" : "Please provide a valid name, username and password"}), 400
		
	user.user_id = str(uuid.uuid4())
	#Hash password using ID as salt
	user.password = hashlib.sha256((user.user_id+user.password).encode('utf-8')).hexdigest()
	#Add new user to database
	db.session.add(user)
	db.session.commit()
	return jsonify(user)

@app.route('/login', methods=['POST'])
def login():
	tmpuser = User.from_dict(request.json)
	user = User.query.filter_by(username=tmpuser.username).first()
	#Check if user exists
	if user == None:
		return jsonify({ "error" : "Wrong username or password"}), 403
	#Check user password
	if user.password == hashlib.sha256((user.user_id+tmpuser.password).encode('utf-8')).hexdigest():
		session = Session(user.user_id, str(uuid.uuid4()))
		#Add new session to database
		db.session.add(session)
		db.session.commit()
		return jsonify(session)
	else:
		return jsonify({ "error" : "Wrong username or password"}), 403 
		
@app.route('/user/<id>')
def getUser(id):
	#Check session is valid
	if checkSession(request):
		user = User.query.filter_by(user_id=id).first()
		#Check if user exists
		if user == None:
			return jsonify({ "error" : "This user does not exist"}), 404
		#Remove hashed password from output
		user.password = ''
		return jsonify(user)
	else:
		return jsonify({ "error" : "Session has expired or does not exist"}), 403
	
def checkSession(req):
	session = Session.query.filter_by(session_id=req.headers['Session-Id']).first()
	if session == None:
		return False
	else:
		return session
	
if __name__ == "__main__":
    app.run(threaded=True, host='0.0.0.0')
