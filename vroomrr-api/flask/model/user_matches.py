from ext import db
from dataclasses import dataclass
from dataclasses_json import dataclass_json

@dataclass_json
@dataclass
class User_matches(db.Model):
	chat_id: str
	user_id_first: str
	user_id_second: str

	chat_id = db.Column(db.String(64), unique=True)
	user_id_first = db.Column(db.String(64))
	user_id_second = db.Column(db.String(64))