from ext import db
from dataclasses import dataclass
from dataclasses_json import dataclass_json

@dataclass_json
@dataclass
class Opinion(db.Model):
	user_id: str
	user_id_match: str
	opinion: str

	user_id = db.Column(db.String(64), primary_key=True)
	user_id_match = db.Column(db.String(64), primary_key=True)
	opinion = db.Column(db.String(64))
