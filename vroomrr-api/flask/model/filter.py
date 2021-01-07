from ext import db
from dataclasses import dataclass
from dataclasses_json import dataclass_json

@dataclass_json
@dataclass
class Filter(db.Model):
	user_id: str
	build_year_min: int
	build_year_max: int
	horsepower_min: int
	fuel_types: str
	brands: str
	colors: str

	user_id = db.Column(db.String(64), primary_key=True)
	horsepower_min = db.Column(db.Integer)
	build_year_min = db.Column(db.Integer)
	build_year_max = db.Column(db.Integer)
	fuel_types = db.Column(db.String(1024))
	brands = db.Column(db.String(1024))
	colors = db.Column(db.String(1024))
