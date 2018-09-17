Ref: https://www.thomasvitale.com/https-spring-boot-ssl-certificate/
Preferred .p12 over .jks

Password: password


Useful Commands:
=====================

keytool -genkeypair -alias springproject.tomcat -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore server_keystore.p12 -validity 3650

keytool -import -alias springproject.tomcat -file springproject.tomcat.crt -storetype PKCS12 -keystore servers_truststore.p12 -storepass password

keytool -export -keystore client_keystore.p12 -alias client.bikas.anand -file client.bikas.anand.crt -storepass password

keytool -import -alias client -file client.bikas.anand.crt -storetype PKCS12 -keystore clients_truststore.p12 -storepass password