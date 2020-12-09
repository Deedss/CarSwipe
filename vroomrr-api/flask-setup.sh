#!/bin/sh
apt-get update && apt-get install -y python3 && apt-get install python3-pip -y && apt-get install uwsgi-plugin-python -y
/usr/bin/pip3 install Flask
/usr/bin/pip3 install uwsgi
/usr/bin/pip3 install mysql-connector
