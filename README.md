# Vroomrr
Android Project for the Minor Secure Programming

## Contributors
- Gertjan Rolink
- Nick Belt
- Luuk Esselbrugge
- Sidoi Klumper

## Installation Guide
### Backend (Docker)
In order to run the development or production backend server you need to install Docker for your operating system.

#### Ubuntu
- Install Docker using `snap install docker`
- Clone this repo `git clone https://github.com/Deedss/Vroomrr/`
- Change directory `cd Vroomrr/vroomrr-api`
- Build docker image `docker build --tag vroomrr-api .`
- Run Docker container with output using `docker run --publish 5000:5000 -it vroomrr-api` replace argument `-it` with `-dit` for headless mode
- Kill the container using `CTRL+C`

The backend server is now ready to be used verify by going to `localhost:5000`
- If you ran Docker in headless mode view list of running containers using `docker ps` or `docker ps -a` to view all containers
- Connect to the container using the NAME acquired using the command above `docker attach hopeful_panini`
- Kill the container using `CTRL+C` or `docker kill hopeful_panini` 
- Detach using `CTRL+P and CTRL+Q`

To run the production server yourself (Instead of the one we are running at https://$SERVER.nl/vroomrr/) you will need to have installed NGINX on your server. Follow all the steps like above but run docker in headless mode. You will need to have prior experience with Linux and servers.
- Follow steps above for Ubuntu until build
- Build docker image `docker build --tag vroomrr-api - < Dockerfile.production`
- Run Docker container in headless mode using `docker run --publish 5000:5000 -dit vroomrr-api`
- Copy NGINX virtual host file to NGINX `cp vroomrr-api/vroomrr.nginx /etc/nginx/sites-available/vroomrr`
- Change file as needed replacing `SERVER_NAME` and `ssl_certificate` path with correct information regarding your webserver
- Create symlink `ln -s /etc/nginx/sites-available/vroomrr /etc/nginx/sites-enabled/vroomrr`
- Configure HTTPS on your NGINX server (for example using lets encrypt)
- Restart NGINX `service nginx restart`

#### Windows

### Application
