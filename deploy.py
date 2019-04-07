from flask import Flask, render_template, request, send_from_directory
from flask_alchemy import SQLAlchemy

app = Flask(__name__)	

app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///site.db'

db = SQLAlchemy(app)

@app.route('/')
def index():
	return render_template("websiteinprogress.html")