from ext import db
from dataclasses import dataclass
from dataclasses_json import dataclass_json

@dataclass_json
@dataclass
class Opinion(db.Model):
	user_id: str
	username: str
	name: str
	password: str
	public_key: str

	user_id = db.Column(db.String(64), primary_key=True)
	username = db.Column(db.String(64), unique=True)
	name = db.Column(db.String(64))
	password = db.Column(db.String(64))
	public_key = db.Column(db.String(1028))
