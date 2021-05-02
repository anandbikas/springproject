FROM openjdk:8-jre-slim

MAINTAINER Bikas Anand, anand.iitr.mca@gmail.com

ARG SERVICE_JAR=springproject-service-*.jar
ARG APP_HOME=/usr/local/anand/springproject-service

ENV SERVICE_JAR=$SERVICE_JAR
ENV APP_HOME=$APP_HOME
ENV JAVA_OPTS="-Xms512M -Xmx1024M"

COPY run.sh $APP_HOME/
COPY $SERVICE_JAR $APP_HOME/

RUN chmod +x $APP_HOME/run.sh
RUN mkdir -p $APP_HOME/logs

RUN apt-get update && apt-get install dumb-init
RUN apt-get install -y libtcnative-1

WORKDIR $APP_HOME

ENTRYPOINT ["dumb-init", "--"]
CMD ["./run.sh"]