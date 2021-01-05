import requests
import re

class Wikimedia:
	
	SERVER = 'https://en.wikipedia.org/w/api.php'
	
	def getImageUrl(self, query):
		try:
			search = requests.get(url=self.SERVER+'?action=query&format=json&list=search&srsearch='+query.replace(' ','%20')).json()
			article = requests.get(url=self.SERVER+'?action=query&prop=images&format=json&pageids='+str(search['query']['search'][0]['pageid'])).json()
			imageurl = requests.get(url=self.SERVER+'?action=query&titles='+article['query']['pages'][str(search['query']['search'][0]['pageid'])]
			['images'][0]['title'].replace(' ','%20')+'&prop=imageinfo&iilimit=1&iiprop=timestamp|user|url&format=json').json()

			return imageurl['query']['pages']['-1']['imageinfo'][0]['url']
		except:
			return False
		
	def getDescription(self, query):
		try:
			search = requests.get(url=self.SERVER+'?action=query&format=json&list=search&srsearch='+query.replace(' ','%20')).json()
			#Remove HTML from description
			cleanr = re.compile('<.*?>|&([a-z0-9]+|#[0-9]{1,6}|#x[0-9a-f]{1,6});')
			cleantext = re.sub(cleanr, '', search['query']['search'][0]['snippet'])
			return cleantext.split('.',1)[0]
		except:
			return False
