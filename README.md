# springproject
Demo project for Spring with SpringBoot

Author: Bikas Anand

##### Docker Operators...
1. **Rabbit**: docker run -p 15672:15672 -p 5672:5672 -p 5671:5671 -d --hostname my-rabbit --name some-rabbit -e RABBITMQ_DEFAULT_USER=rabbit -e RABBITMQ_DEFAULT_PASS=password rabbitmq:3.7.7-management
2. **MySQL**:  docker run -p 3306:3306 -d --hostname mysql --name some-mysql -e MYSQL_ROOT_PASSWORD=root_password  mysql:latest
3. **Redis**:  docker run -p 6379:6379 -d --hostname my-redis --name some-redis redis:latest


##### Documents Reference
1. **Docker Maven Plugin**: 
    * https://github.com/spotify/docker-maven-plugin

2. **Tomcat Architecture and Performance**
    * https://www.datadoghq.com/blog/tomcat-architecture-and-performance
    * https://www.datadoghq.com/blog/tomcat-monitoring-tools
    * https://stackify.com/tomcat-performance-monitoring/
    
3. **MySQL Centos 7 setup**: 
    * https://www.mysqltutorial.org/install-mysql-centos/
    * https://electrictoolbox.com/update-max-connections-mysql/ (Max connections)
    * https://stackoverflow.com/questions/50379839/connection-java-mysql-public-key-retrieval-is-not-allowed

4. **SpringBoot Tomcat configuration**
    * https://www.baeldung.com/spring-boot-configure-tomcat  

5. **Content Negotiation**
    * https://spring.io/blog/2013/05/11/content-negotiation-using-spring-mvc/
    * https://www.javadevjournal.com/spring-mvc/spring-mvc-content-negotiation/
    * https://www.javainuse.com/spring/spring-boot-content-negotiation
    * https://www.programcreek.com/java-api-examples/?api=org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer

6. **Http Message Converters with Spring**
    * https://www.javadevjournal.com/spring/spring-http-message-converter

7. **Bean Definition override**
    * https://www.baeldung.com/spring-boot-bean-definition-override-exception

8. **Spring Boot Auto Configuration**
    * https://dzone.com/articles/what-is-spring-boot-auto-configuration

9. **DynamoDB basics**
    * https://gist.github.com/jlafon/d8f91086e3d00c4bff3b
    * https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/HowItWorks.CoreComponents.html

10. **incompatible version of frameworks**
    * https://stackoverflow.com/questions/58727497/java-lang-nosuchfielderror-import-bean-name-generator
 
11. **JMX**
    * https://www.adam-bien.com/roller/abien/entry/how_to_establish_jmx_connection
 
12. **Make Springboot executable init.d / systemd**
    * https://docs.spring.io/spring-boot/docs/current/reference/html/deployment.html#deployment-install
    * https://docs.spring.io/spring-boot/docs/current/reference/html/deployment.html#deployment-initd-service
    * https://www.freedesktop.org/software/systemd/man/systemd.service.html (no of file descriptors)

13. **systemctl**
    * https://unix.stackexchange.com/questions/506347/why-do-most-systemd-examples-contain-wantedby-multi-user-target
    * https://unix.stackexchange.com/questions/126009/cause-a-script-to-execute-after-networking-has-started
    * https://access.redhat.com/solutions/1383853
    * https://www.freedesktop.org/wiki/Software/systemd/Incompatibilities/
    * https://www.thegeekdiary.com/centos-rhel-7-systemctl-replacements-of-legacy-commands-service-and-chkconfig/
 
14. **spring profiling**
    * https://stackoverflow.com/questions/23872644/conditional-spring-configuration
  
15. **Accessing data with MySQL**
    * https://spring.io/guides/gs/accessing-data-mysql/
    * https://www.objectdb.com/java/jpa/entity/id

16. **MySQL configuration: EntityManagerFactory**
    * https://docs.spring.io/spring-boot/docs/2.0.0.M3/reference/html/howto-data-access.html
    * https://docs.spring.io/spring-boot/docs/2.0.0.M3/reference/html/howto-data-access.html
    * https://stackoverflow.com/questions/59006738/caused-by-org-springframework-beans-factory-nosuchbeandefinitionexception-no-b?noredirect=1&lq=1
    * https://stackoverflow.com/questions/23974249/spring-data-jpa-without-spring-boot
    * https://stackoverflow.com/questions/51931726/how-to-use-jpa-repositories-without-spring-boot
    * https://stackoverflow.com/questions/23172643/how-to-set-up-datasource-with-spring-for-hikaricp
    * https://github.com/brettwooldridge/HikariCP/wiki/Spring-Hibernate-with-Annotations
    * https://stackoverflow.com/questions/55664804/hikari-cp-properties-are-not-working-with-multiple-datasource-configuration-in-s
    * https://www.sitepoint.com/community/t/update-replace-v-delete-insert-mysql/271402
    * https://medium.com/@joeclever/using-multiple-datasources-with-spring-boot-and-spring-data-6430b00c02e7
    * https://www.javadevjournal.com/spring-boot/spring-boot-hikari/
    * http://zetcode.com/articles/hikaricp/
    * https://github.com/brettwooldridge/HikariCP#configuration-knobs-baby
    
17. **Custom SQL configuration**
    * https://www.javadevjournal.com/spring-boot/how-spring-boot-auto-configuration-works
    * https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-data-access
    * https://dzone.com/articles/how-springboot-autoconfiguration-magic-works
    * https://www.baeldung.com/spring-boot-custom-auto-configuration
    * spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
    * https://stackoverflow.com/questions/48416927/spring-boot-required-a-bean-named-entitymanagerfactory-that-could-not-be-foun/54663039
    * https://github.com/spring-projects/spring-boot/blob/master/spring-boot-project/spring-boot-autoconfigure/src/main/java/org/springframework/boot/autoconfigure/jdbc/DataSourceAutoConfiguration.java
    * https://stackoverflow.com/questions/33074547/spring-boot-configure-entitymanager
    * https://www.baeldung.com/spring-boot-failed-to-configure-data-source
    * https://stackoverflow.com/questions/43154067/how-to-initialize-secondary-other-datasources-programatically-without-reading-ap
    * https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto-data-access
    * https://www.baeldung.com/the-persistence-layer-with-spring-and-jpa
    * https://gist.github.com/rhamedy/b3cb936061cc03acdfe21358b86a5bc6
    * https://docs.spring.io/spring-boot/docs/2.1.0.M1/reference/html/howto-database-initialization.html

18. **Custom datasource with hikari properties**
    * https://azizkhani.github.io/2017-08-14-custom-hikari-datasource-in-spring-boot/
    * https://stackoverflow.com/questions/26933437/connection-pool-with-hikaricp-to-multiple-databases
    * https://www.baeldung.com/spring-data-jpa-multiple-databases
    * https://github.com/brettwooldridge/HikariCP/wiki/About-Pool-Sizing
       
19. **SQL Logging**
    * https://www.baeldung.com/sql-logging-spring-boot
 
20. **Command line mysql**
    * https://stackoverflow.com/questions/30990488/how-do-i-install-command-line-mysql-client-on-mac
    * https://linuxize.com/post/how-to-change-mysql-user-password/
    * https://chartio.com/resources/tutorials/how-to-grant-all-privileges-on-a-database-in-mysql/
    * https://stackoverflow.com/questions/6239131/how-to-grant-remote-access-permissions-to-mysql-server-for-user
    * https://support.rackspace.com/how-to/mysql-connect-to-your-database-remotely/

21. **Spring data JPA query**
    * https://www.baeldung.com/spring-data-jpa-query
    * https://stackoverflow.com/questions/40605834/spring-jparepositroy-save-does-not-appear-to-throw-exception-on-duplicate-save
    * https://stackoverflow.com/questions/32793403/crudrepository-and-hibernate-savelists-vs-saveentity-in-transaction
    * https://www.objectdb.com/java/jpa/entity/index
    * https://stackoverflow.com/questions/22031128/how-to-update-an-entity-with-spring-data-jpa
    * https://docs.spring.io/spring-data/data-jpa/docs/current/reference/html/#jpa.named-parameters
    * https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa
    * https://www.baeldung.com/java-jpa-transaction-locks 
      When the transaction needs to adhere to ACID rules strictly, we should use Pessimistic Locking. 
      Optimistic Locking should be applied when we need to allow multiple concurrent reads and when eventual consistency is acceptable within the application context.
     
22. **Composite primary key mysql**

23. **Interceptor to add sessionId if missing** 

24. **Grok validation** 
    * https://grokdebug.herokuapp.com/
  
25. **Logstassh with grok filter**
 
26. **Custom error page in spring**
    * https://www.techiedelight.com/display-custom-error-pages-in-spring-boot/
    * https://www.javatpoint.com/spring-boot-thymeleaf-view
 
27. **Aurora DB**
    * https://aws.amazon.com/blogs/database/using-the-data-api-to-interact-with-an-amazon-aurora-serverless-mysql-database/
    * https://www.baeldung.com/aws-aurora-rds-java
    * https://aws.amazon.com/rds/aurora/mysql-features/
 
28. **DynamobDB vs casandra**
    * https://www.kdnuggets.com/2018/08/dynamodb-vs-cassandra.html
    * https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/dynamodbv2/datamodeling/DynamoDBRangeKey.html

29. **SQL naming conventions***
    * https://launchbylunch.com/posts/2014/Feb/16/sql-naming-conventions/
 
30. **Property Source**
    * https://www.journaldev.com/21440/spring-propertysource
 
31. **Google cloud databases**
    * https://cloud.google.com/products/databases/
 
32. **Google cloud memorystore**
    * https://cloud.google.com/memorystore/   
 
34. **Long polling vs WebSockets vs HTTP2 Push vs SSE**
    * https://www.ably.io/concepts/long-polling
    * https://ably.com/topic/websockets
    * AMQP, MQTT
    * https://building.lang.ai/our-journey-from-websockets-to-http-2-4d069c54effd
    * https://www.smashingmagazine.com/2018/02/sse-websockets-data-flow-http2
    * https://samsaffron.com/archive/2015/12/29/websockets-caution-required
 
35. **RabbitMQ**
    * https://www.rabbitmq.com/access-control.html (Access Control)
    * https://engineering.nanit.com/rabbitmq-retries-the-full-story-ca4cc6c5b493 (RabbitMQ Retries and Dead Letter Exchange)
    * https://www.rabbitmq.com/confirms.html (Consumer Acknowledgements and Publisher Confirms)
    * https://www.rabbitmq.com/nack.html
    * https://docs.spring.io/spring-amqp/api/org/springframework/amqp/core/AcknowledgeMode.html
    * https://medium.com/swlh/delay-schedule-messages-in-rabbitmq-208b594cdc00
    
36. **Vault Spring boot**
    * https://spring.io/projects/spring-vault#overview
    * https://www.baeldung.com/spring-cloud-vault
    * https://banzaicloud.com/blog/vault-java-spotguide/
    * https://cloud.spring.io/spring-cloud-vault/reference/html/
    * https://spring.io/projects/spring-cloud-vault
    * https://spring.io/blog/2016/06/24/managing-secrets-with-vault

37. **LogRotate**

38. **GlobalExceptionHandler** 
    * Generic exception while processing request.

39. **Threads**
    * https://helpx.adobe.com/in/experience-manager/kb/securerandom-nextbytes-hangs-request-threads-in-aem.html
    * https://dzone.com/articles/threads-stuck-in-javanetsocketinputstreamsocketrea

40. **Kubernetes**
    * https://www.driftrock.com/blog/kubernetes-zero-downtime-rolling-updates (zero-downtime rolling updates)
    
41. **Redis**
    * https://hub.docker.com/r/rediscommander/redis-commander/ (Redis UI)
    * https://www.baeldung.com/jedis-java-redis-client-library
    * https://partners-intl.aliyun.com/help/doc-detail/98726.htm (JedisPool optimization)
    * https://www.baeldung.com/spring-data-redis-tutorial
    * (Jedis Lock)
    * https://github.com/redis/jedis/issues/1931 (No common interface for JedisCommands and JedisClusterCommands)
    * https://github.com/redis/jedis/issues/916 (Standalone redis pool connection closing issue)
    * https://stackoverflow.com/questions/14993644/configure-jedis-timeout

42. **Spring**
    * https://www.baeldung.com/running-setup-logic-on-startup-in-spring
    * https://github.com/spring-projects/spring-boot/issues/21628 (NoSuchMethodError)
    * https://spring.io/blog/2016/04/15/testing-improvements-in-spring-boot-1-4 (Testing)
    * https://dzone.com/articles/spring-boot-vs-spring-mvc-vs-spring-how-do-they-compare (Spring vs Boot vs MVC)

42. **Tomcat Keep alive** 
    * https://github.com/spring-projects/spring-boot/issues/23539 (Tomcat keepAliveTimeout and maxKeepAliveRequests)
    * https://github.com/spring-projects/spring-boot/issues/15210
    * https://www.imperva.com/learn/performance/http-keep-alive
    
43. **Spring Cloud**
    * https://www.baeldung.com/tracing-services-with-zipkin
    * https://www.baeldung.com/spring-cloud-sleuth-single-application
    * https://github.com/spring-cloud/spring-cloud-sleuth/issues/736
    * https://medium.com/swlh/microservices-observability-with-zipkin-and-spring-cloud-sleuth-66508ce6840
    
44. **Spring Boot Admin**
    * https://www.baeldung.com/spring-boot-admin
    
45. **HTTP2**
    * https://softtechbuzz.mystrikingly.com/blog/how-to-use-http2-protocol-in-spring-boot-applications
    * https://github.com/spring-projects/spring-boot/issues/15210 (max-keepalive-timeout is 2000 ms)
    * https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Keep-Alive
    * https://tomcat.apache.org/tomcat-9.0-doc/config/http2.html
    * https://stackoverflow.com/questions/42849209/configuring-maxkeepaliverequests-in-spring-boot-embedded-tomcat (maxKeepAliveRequests)
    * https://stackoverflow.com/questions/56075154/keep-alive-configurations-of-spring-boot-app-with-embedded-tomcat
    * https://stackoverflow.com/questions/39002090/spring-boot-limit-on-number-of-connections-created
    
46 **Rest Template**
    * https://howtodoinjava.com/spring-boot2/resttemplate/resttemplate-timeout-example/
    
47. **Elastic Search**
    * Post Man Collection: https://www.getpostman.com/collections/a23b54992406472f0ad9
    * https://www.elastic.co/blog/found-elasticsearch-top-down
    * https://www.elastic.co/blog/found-elasticsearch-from-the-bottom-up#building-indexes
    * https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-replication.html
    * https://www.elastic.co/blog/found-similarity-in-elasticsearch
    * https://www.elastic.co/blog/found-sizing-elasticsearch#learning-more
    * https://www.elastic.co/blog/faster-retrieval-of-top-hits-in-elasticsearch-with-block-max-wand
    * https://www.elastic.co/guide/en/elasticsearch/reference/current/release-highlights-7.0.0.html
    
48. **Spring Session**
    * Spring session library (using redis/ using sql)

#### Troubleshooting
1. https://stackoverflow.com/questions/5440429/springs-scheduled-error-only-one-asyncannotationbeanpostprocessor-may-exist
2. https://jira.qos.ch/browse/LOGBACK-1406 (logback Seems all threads blocked)