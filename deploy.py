from flask import Flask, render_template, request
from flask_sqlalchemy import SQLAlchemy
from datetime import datetime
import time
import requests

#datetime.now()
#yr,month,day,hour,min,second,ms
app = Flask(__name__)	
#print(today.strftime("%A, %B, %d, %Y"))
#from deploy import db? not neccesary
#db.create_all()
#from deploy import plant #not necesary
#reading1= Plant(temperature = '21.2')
#db.session.add(reading1)
#db.session.commit()
#Plant.query.all()
#Plant.query.filter_by(*).all()
#Plant.query.get(1) - we want to be able to search the database by column
#Plant.query.oder
#xvalues, yvalues will be a list

#---------------------------------------

app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///site.db'
moistURL = 'https://api.particle.io/v1/devices/350025000b47363339343638/moisturePercentage?access_token=1aa09ff2c1a069ca69c16e6822b0602412a8c1c4'
tempURL = 'https://api.particle.io/v1/devices/350025000b47363339343638/temperature?access_token=1aa09ff2c1a069ca69c16e6822b0602412a8c1c4'
humidURL = 'https://api.particle.io/v1/devices/350025000b47363339343638/humidity?access_token=1aa09ff2c1a069ca69c16e6822b0602412a8c1c4'

def getjson(url):
	r = requests.get(url)
	out = r.json()['result']
	return out
time.sleep(0.1)
globalmoist = getjson(moistURL) 
globaltemp = getjson(tempURL)
globalhumid = getjson(humidURL)

db = SQLAlchemy(app)

class Plant(db.Model):
	id = db.Column(db.Integer, primary_key=True)
	time = db.Column(db.String, nullable=False, default = datetime.now().strftime("%A, %B %d, %I:%M %p"))
	moisture = db.Column(db.Integer)
	temperature = db.Column(db.Float)
	humidity = db.Column(db.Float)

def __repr__(self):
	return f"Plant('{self.time}','{self.moisture}','{self.temperature}','{self.humidity}')"

globalmoist = getjson(moistURL)
time.sleep(0.1)
globalhumid = getjson(humidURL)
time.sleep(0.1)
globaltemp = getjson(tempURL)
time.sleep(0.1)
time.sleep(10)
newReading = Plant(moisture = globalmoist, temperature = globaltemp, humidity = globalhumid)
db.session.add(newReading)
db.session.commit()

	# What you want to run in the foreground
	

xvalues = Plant.query.with_entities(Plant.time).all()
ytemperature = Plant.query.with_entities(Plant.temperature).all()
ymoisture = Plant.query.with_entities(Plant.moisture).all()
yhumidity = Plant.query.with_entities(Plant.humidity).all()

plantobj = {'xvalues':xvalues,'ytemperature':ytemperature,'ymoisture':ymoisture,'yhumidity':yhumidity}
object1 = {'num': [86,114,106,106,107,111,133,221,783,24782], 'rest': 'jemima'}

@app.route('/')
def index():
	return render_template("websiteinprogress.html", object1=object1, plantobj=plantobj)



# def updatedatabase():
# 	try:
# 		while(1):
# 			getdata()
# 	except KeyError:
# 		print("plant offline!")
# 		updatedatabase()
# def getdata():	

# updatedatabase()
	#return render_template("websiteinprogress.html", xvalues=xvalues, globalmoist=globalmoist, globaltemp=globaltemp, globalhumid=globalhumid)
#if __name__ == '__main__':
#	app.run(debug=True)