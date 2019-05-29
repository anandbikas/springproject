Ref: https://www.thomasvitale.com/https-spring-boot-ssl-certificate/
Preferred .p12 over .jks

Password: password


Generate Keystore Commands:
==========================

keytool -genkeypair -alias springproject.tomcat -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore server_keystore.p12 -validity 3650
keytool -export -keystore server_keystore.p12 -alias springproject.tomcat -file springproject.tomcat.crt -storepass password
keytool -import -alias springproject.tomcat -file springproject.tomcat.crt -storetype PKCS12 -keystore servers_truststore.p12 -storepass password

keytool -genkeypair -alias client.bikas.anand -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore client_keystore.p12 -validity 3650
keytool -export -keystore client_keystore.p12 -alias client.bikas.anand -file client.bikas.anand.crt -storepass password
keytool -import -alias client -file client.bikas.anand.crt -storetype PKCS12 -keystore clients_truststore.p12 -storepass password


Curl with cert
==========================
openssl pkcs12 -in client_keystore.p12 -out ca.pem -cacerts -nokeys

openssl pkcs12 -in client_keystore.p12 -out client.pem -clcerts -nokeys

openssl pkcs12 -in client_keystore.p12 -out key.pem -nocerts

curl --insecure --cert client.pem:MyPassword --key key.pem https://springproject.com:8443/springproject/status