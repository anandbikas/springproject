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

4. **SpringBoot Tomcat configuration**
    * https://www.baeldung.com/spring-boot-configure-tomcat  

5. **Content Negotiation**
    * https://spring.io/blog/2013/05/11/content-negotiation-using-spring-mvc/
    * https://www.javadevjournal.com/spring-mvc/spring-mvc-content-negotiation/
    * https://www.javainuse.com/spring/spring-boot-content-negotiation

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
     
22. **Composite primary key mysql**

23. **Interceptor to add sessionId if missing** 

24. **Grok validation** 
    * https://grokdebug.herokuapp.com/
  
25. **Logstassh with grok filter**
 
26. **Custom error page in spring**
    * https://www.techiedelight.com/display-custom-error-pages-in-spring-boot/
 
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
 
33. **Sockets**
    * https://www.smashingmagazine.com/2018/02/sse-websockets-data-flow-http2/
    * https://samsaffron.com/archive/2015/12/29/websockets-caution-required
 
34. **Long polling**
    * https://www.ably.io/concepts/long-polling
 
35. **RabbitMQ Access**
    * https://www.rabbitmq.com/access-control.html 

36. **Vault Spring boot**

37. **LogRotate**

38. **GlobalExceptionHandler** 
    * Generic exception while processing request.

39. **Threads**
    * https://helpx.adobe.com/in/experience-manager/kb/securerandom-nextbytes-hangs-request-threads-in-aem.html


#### Troubleshooting
1. https://stackoverflow.com/questions/5440429/springs-scheduled-error-only-one-asyncannotationbeanpostprocessor-may-exist
