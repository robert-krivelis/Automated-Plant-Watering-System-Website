from flask import Flask, render_template, request, send_from_directory
#from flask_alchemy import SQLAlchemy
from datetime import datetime
#datetime.now()
#yr,month,day,hour,min,second,ms
app = Flask(__name__)	

# app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///site.db'

# db = SQLAlchemy(app)
# class plant(db.Model):
# 	id = db.Column(db.Integer, primary_key=True)
# 	time = db.Column(db.Integer)
# 	moisture = db.Column(db.Integer)
# 	temperature = db.Column(db.Real)
# 	humidity = db.Column(db.Real)

# 	def __repr__(self):
# 		return f"plant('{self.time}','{self.moisture}','{self.temperature}','{self.humidity}')"


#we're going to need to pass to HTML: datasets(x,y)
#for our colums we're going to need entry(primary), time, moisture, temp, humidity.
#later we can add ideal moisture, ideal temp, ideal humidity. This is probably easiest if we store all the values
#in our database and just reference them when we need to.
@app.route('/')
def index():
	return render_template("websiteinprogress.html")
#if __name__ == '__main__':
#	app.run(debug=True)