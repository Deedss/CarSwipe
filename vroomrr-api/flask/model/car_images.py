from ext import db
from dataclasses import dataclass
from dataclasses_json import dataclass_json

@dataclass_json
@dataclass
class Car_images(db.Model):
	car_images_id: int
	license_plate: str
	image: str
    
	user_id = db.Column(db.int(8), unique=True)
	license_plate = db.Column(db.String(16), unique=True)
	image = db.Column(db.String(4096))
