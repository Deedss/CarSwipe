from ext import db
from dataclasses import dataclass
from dataclasses_json import dataclass_json

@dataclass_json
@dataclass
class Car_images(db.Model):
	car_images_id: str
	license_plate: str
	image: str

	car_images_id = db.Column(db.Integer, unique=True, primary_key=True)
	license_plate = db.Column(db.String(16), unique=True)
	image = db.Column(db.String(4096))
