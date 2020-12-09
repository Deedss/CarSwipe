#!/bin/sh
apt-get update && apt-get install -y mariadb-server
#sed -i -e"s/^bind-address\s*=\s*127.0.0.1/bind-address = 0.0.0.0/" /etc/mysql/my.cnf

/etc/init.d/mysql start
sleep 5
/usr/bin/mysql -e "GRANT ALL ON *.* TO admin@'%' IDENTIFIED BY 'password' WITH GRANT OPTION; FLUSH PRIVILEGES"
/usr/bin/mysql -e "CREATE DATABASE vroomrr"
/usr/bin/mysql vroomrr < /tmp/database.sql
