# springproject
Demo project for Spring with SpringBoot

Author: Bikas Anand

##### How To setup a RabbitMQ docker instance...
docker run -p 15672:15672 -p 5672:5672 -p 5671:5671 -d --hostname my-rabbit --name some-rabbit -e RABBITMQ_DEFAULT_USER=rabbit -e RABBITMQ_DEFAULT_PASS=password rabbitmq:3.7.7-management

##### Docker Maven Plugin doc
https://github.com/spotify/docker-maven-plugin