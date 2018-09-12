Ref: https://www.thomasvitale.com/https-spring-boot-ssl-certificate/
Preferred .p12 over .jks

Password: password


Useful Commands:
=====================

keytool -genkeypair -alias springproject.tomcat -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore server_keystore.p12 -validity 3650

keytool -import -alias springproject.tomcat -file springproject.tomcat.crt -keystore servers_truststore.p12 -storepass password