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


Curl https endpoint with cert
=============================
openssl pkcs12 -in client_keystore.p12 -out ca.pem -cacerts -nokeys

openssl pkcs12 -in client_keystore.p12 -out client.pem -clcerts -nokeys

openssl pkcs12 -in client_keystore.p12 -out key.pem -nocerts

curl --insecure --cert client.pem:MyPassword --key key.pem https://springproject.com:8443/springproject/status


cert and key to p12 and then to .jks
=====================================
https://www.wowza.com/docs/how-to-import-an-existing-ssl-certificate-and-private-key

openssl pkcs12 -export -in prod_cert.cer -inkey cert.key -out prod-keystore.p12
keytool -importkeystore -deststorepass mypassword -destkeystore keystore.jks -srckeystore prod-keystore.p12 -srcstoretype PKCS12



p7b and key to pem and then to .jks
=====================================
https://pbaris.wordpress.com/2015/10/08/import-p7b-hain-certificate-with-private-key-in-keystore/
openssl pkcs7 -print_certs  -in prod_cert.p7b  -out prod_cert_p7b.pem

openssl pkcs12 -export  -in prod_cert_p7b.pem  -inkey cert.key  -out keystore_from_p7b.p12
keytool -importkeystore  -srcstoretype pkcs12  -srckeystore prod_keystore_from_p7b.p12  -destkeystore prod_keystore_with_chain.jks


Encoding/Decoding
========================
-> Encode a data file to Base64
base64 data.txt > data.b64

-> Decode a Base64 file
base64 -d data.b64 > data.txt