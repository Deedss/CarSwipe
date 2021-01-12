from ext import db
from dataclasses import dataclass
from dataclasses_json import dataclass_json

@dataclass
@dataclass_json
class Image64(db.Model):
	license_plate: str
	image64: str

	license_plate = db.Column(db.String(64), primary_key=True)
	image64 = db.Column(db.String(40960))
