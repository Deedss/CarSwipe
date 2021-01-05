#!/bin/sh
apt-get update && apt-get install -y python3 && apt-get install python3-pip -y
/usr/bin/pip3 install Flask
/usr/bin/pip3 install uwsgi
/usr/bin/pip3 install Flask-SQLAlchemy
/usr/bin/pip3 install pymysql
/usr/bin/pip3 install dataclasses-json
/usr/bin/pip3 install requests
