from ext import db
from dataclasses import dataclass
from dataclasses_json import dataclass_json

@dataclass_json
@dataclass
class Car(db.Model):
	license_plate: str
	user_id: str
	brand: str
	color: str
	type: str
	horsepower: int
	build_year: int
	fuel_type: str
	description: str
	selected: bool

	license_plate = db.Column(db.String(16), primary_key=True)
	user_id = db.Column(db.String(64), unique=True)
	brand = db.Column(db.String(64))
	color = db.Column(db.String(64))
	type = db.Column(db.String(64))
	horsepower = db.Column(db.Integer)
	build_year = db.Column(db.Integer)
	fuel_type = db.Column(db.String(16))
	description = db.Column(db.String(1024))
	selected = db.Column(db.Boolean)
