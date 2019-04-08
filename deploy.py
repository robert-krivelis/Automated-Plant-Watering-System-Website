from flask import Flask, render_template#, request, send_from_directory
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
# app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///site.db'

# start_time = time.perf_counter()

# moistURL = 'https://api.particle.io/v1/devices/350025000b47363339343638/moisturePercentage?access_token=1aa09ff2c1a069ca69c16e6822b0602412a8c1c4';
# tempURL = 'https://api.particle.io/v1/devices/350025000b47363339343638/temperature?access_token=1aa09ff2c1a069ca69c16e6822b0602412a8c1c4';
# humidURL = 'https://api.particle.io/v1/devices/350025000b47363339343638/humidity?access_token=1aa09ff2c1a069ca69c16e6822b0602412a8c1c4';

# def getjson(url):
# 	r = requests.get(url)
# 	out = r.json()['result']
# 	return out

# if ((time.perf_counter()-start_time)//1%59 == 0):
# 	globalmoist = getjson(moistURL)
# if ((time.perf_counter()-start_time)//1%58 == 0):	
# 	globalhumid = getjson(humidURL)
# if ((time.perf_counter()-start_time)//1%57 == 0):	
# 	globaltemp = getjson(tempURL)


# if ((time.perf_counter()-start_time)//1%60 == 0):
# 	newReading = Plant(moisture = 'globalmoist', temperature = 'globaltemp', humidity = 'globalhumid')
# 	db.session.add(newReading)
# 	db.session.commit()
	

# xvalues = Plant.query.with_entities(Plant.time).all()
# ytemperature = Plant.query.with_entities(Plant.temperature).all()
# ymoisture = Plant.query.with_entities(Plant.moisture).all()
# yhumidity = Plant.query.with_entities(Plant.humidity).all()

# db = SQLAlchemy(app)
# class Plant(db.Model):
# 	id = db.Column(db.Integer, primary_key=True)
# 	time = db.Column(db.String, nullable=False, default = datetime.now().strftime("%A, %B %d, %I:%M %p"))
# 	moisture = db.Column(db.Integer)
# 	temperature = db.Column(db.Float)
# 	humidity = db.Column(db.Float)

# 	def __repr__(self):
# 		return f"Plant('{self.time}','{self.moisture}','{self.temperature}','{self.humidity}')"
#---------------------------------------
object1 = {'num': [86,114,106,106,107,111,133,221,783,24782], 'rest': 'jemima'}
#we're going to need to pass to HTML: datasets(x,y)
#for our colums we're going to need entry(primary), time, moisture, temp, humidity.
#later we can add ideal moisture, ideal temp, ideal humidity. This is probably easiest if we store all the values
#in our database and just reference them when we need to.
@app.route('/')
def index():
	return render_template("websiteinprogress.html", object1=object1)# xvalues=xvalues, globalmoist=globalmoist, globaltemp=globaltemp, globalhumid=globalhumid)

	#return render_template("websiteinprogress.html", xvalues=xvalues, globalmoist=globalmoist, globaltemp=globaltemp, globalhumid=globalhumid)
#if __name__ == '__main__':
#	app.run(debug=True)