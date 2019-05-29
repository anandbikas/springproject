#!/usr/bin/env bash

APP_HOME="-DAPP_HOME=$APP_HOME"

[[ -z "$DEBUG_PORT" ]] && DEBUG="" || DEBUG="-agentlib:jdwp=transport=dt_socket,address=$DEBUG_PORT,server=y,suspend=n"

JAVA_ARGS=" $APP_HOME $JAVA_OPTS $DEBUG"

exec java -server $JAVA_ARGS -jar $SERVICE_JAR --spring.config.location=classpath:application.properties,$APP_HOME/conf/application-override.properties
