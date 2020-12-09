from flask import Flask, jsonify

import mysql.connector
app = Flask(__name__)

@app.after_request
def apply_caching(response):
    response.headers['Access-Control-Allow-Origin'] = '*'
    return response
    
@app.route('/ping/<msg>')
def project(msg):
	mydb = mysql.connector.connect(host="localhost", user="admin",password="password",database="vroomrr")
	cur = mydb.cursor()
	cur.execute('select * from user where user_id = %s', [msg])
	r = [dict((cur.description[i][0], value)
		for i, value in enumerate(row)) for row in cur.fetchall()]
	mydb.close()
	return jsonify({'pong' : r})
	
if __name__ == "__main__":
    app.run(threaded=True, host='0.0.0.0')
