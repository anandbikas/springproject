FROM openjdk:8-jre-slim

MAINTAINER Bikas Anand, anand.iitr.mca@gmail.com

ARG SERVICE_JAR
ARG APP_HOME

ENV SERVICE_JAR=$SERVICE_JAR
ENV APP_HOME=$APP_HOME

WORKDIR $APP_HOME

COPY run.sh $APP_HOME/
COPY $SERVICE_JAR $APP_HOME/

RUN chmod +x run.sh
RUN mkdir -p $APP_HOME/logs

RUN apt-get update && apt-get install dumb-init
ENTRYPOINT ["dumb-init", "--"]
CMD ["./run.sh"]