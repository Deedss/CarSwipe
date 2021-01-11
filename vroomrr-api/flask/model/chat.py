from ext import db
from dataclasses import dataclass
from dataclasses_json import dataclass_json

@dataclass_json
@dataclass
class Chat(db.Model):
	chat_id: str
	user_id1: str
	user_id2: str
	start: str

	chat_id = db.Column(db.String(64), primary_key=True)
	user_id1 = db.Column(db.String(64))
	user_id2 = db.Column(db.String(64))
	start = db.Column(db.String(64))
