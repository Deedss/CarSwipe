from ext import db
from dataclasses import dataclass
from dataclasses_json import dataclass_json

@dataclass_json
@dataclass
class Chat(db.Model):
	chat_id: str
	start: datetime
    
	chat_id = db.Column(db.String(), primary_key=True)
	start = db.Column(db.datetime(1028))
