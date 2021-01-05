FROM ubuntu:latest

MAINTAINER Luuk Esselbrugge V1.0

#Install and configure SQL server and add testdata
ADD ./mysql-setup.sh /tmp/mysql-setup.sh
#Add database sql file
ADD ./database.sql /tmp/database.sql
ADD ./testdata.sql /tmp/testdata.sql
RUN /bin/bash /tmp/mysql-setup.sh

#Install Python3 and Flask API, configure test server service
ADD ./flask-setup.sh /tmp/flask-setup.sh
RUN /bin/sh /tmp/flask-setup.sh

#Add API files
ADD ./flask/ /flask/
#Forward ports
EXPOSE 5000
#Start MYSQL and Flask server
WORKDIR /flask/

#CMD /etc/init.d/mysql start ; /usr/local/bin/uwsgi --socket 0.0.0.0:5000 --protocol=http -w wsgi:app
CMD /etc/init.d/mysql start ; export FLASK_DEBUG=1 ; /usr/bin/python3 /flask/app.py
