#!/usr/bin/env bash

APP_HOME_VAR="-DAPP_HOME=$APP_HOME"

[[ -z "$DEBUG_PORT" ]] && DEBUG="" || DEBUG="-agentlib:jdwp=transport=dt_socket,address=$DEBUG_PORT,server=y,suspend=n"
[[ -z "$JMX_PORT" ]] && JMX_OPTS="" || JMX_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=$JMX_PORT -Dcom.sun.management.jmxremote.rmi.port=$JMX_PORT -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Djava.rmi.server.hostname=localhost -Dcom.sun.management.jmxremote.local.only=false"

JVM_OPTS="-Xms512M -Xmx1024M"
JAVA_ARGS=" $APP_HOME_VAR $JAVA_OPTS $JVM_OPTS $JMX_OPTS $DEBUG"

exec java -server $JAVA_ARGS -jar $SERVICE_JAR --spring.config.location=classpath:application.properties,$APP_HOME/conf/application-override.properties
