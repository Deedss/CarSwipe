from ext import db
from dataclasses import dataclass
from dataclasses_json import dataclass_json

@dataclass_json
@dataclass
class Session(db.Model):
	session_id: str
	user_id: str
	time: str

	session_id = db.Column(db.String(64), primary_key=True)
	user_id = db.Column(db.String(64))
	time = db.Column(db.String(64))
	
	def __init__(self, uid, id):
		self.session_id = id
		self.user_id = uid
