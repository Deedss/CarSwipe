from ext import db
from dataclasses import dataclass
from dataclasses_json import dataclass_json

@dataclass_json
@dataclass
class Message(db.Model):
	messageid: str
	chat_id: str
	user_id: str
	time: str
	content: str
    
	messageid = db.Column(db.String(64), primary_key=True)
	chat_id = db.Column(db.String(64), unique=True)
	user_id = db.Column(db.String(64), unique=True)
	time = db.Column(db.datetime())
	content = db.Column(db.String(1028))
