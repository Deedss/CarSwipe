from ext import db
from dataclasses import dataclass
from dataclasses_json import dataclass_json

@dataclass_json
@dataclass
class Message(db.Model):
	message_id: str
	chat_id: str
	user_id: str
	time: str
	content: str

	message_id = db.Column(db.String(64), primary_key=True)
	chat_id = db.Column(db.String(64))
	user_id = db.Column(db.String(64))
	time = db.Column(db.String(64))
	content = db.Column(db.String(1028))
