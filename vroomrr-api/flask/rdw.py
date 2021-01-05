import requests

class RDW:
	
	SERVER = 'https://opendata.rdw.nl/'
	RESOURCE_VOERTUIGEN = 'resource/m9d7-ebf2.json'
	RESOURCE_FUEL = 'resource/8ys7-d773.json'
	
	def getVehicle(self, kenteken):
		#Filters - and makes all letter uppercase for RDW API
		r = requests.get(url=self.SERVER+self.RESOURCE_VOERTUIGEN+'?kenteken='+kenteken.upper().replace('-',''))
		return r.json()[0]
		
	def getVehicleFuel(self, kenteken):
		#Filters - and makes all letter uppercase for RDW API
		r = requests.get(url=self.SERVER+self.RESOURCE_FUEL+'?kenteken='+kenteken.upper().replace('-',''))
		return r.json()[0]
