@echo off
rem mvn dependency:build-classpath
set CP=C:\Users\narkesau\.m2\repository\org\mb4j\mb4j-servlet\0.3.5-SNAPSHOT\mb4j-servlet-0.3.5-SNAPSHOT.jar;C:\Users\narkesau\.m2\repository\org\mb4j\mb4j-component\0.3.5-SNAPSHOT\mb4j-component-0.3.5-SNAPSHOT.jar;C:\Users\narkesau\.m2\repository\org\mb4j\mb4j-brick\0.3.5-SNAPSHOT\mb4j-brick-0.3.5-SNAPSHOT.jar;C:\Users\narkesau\.m2\repository\com\samskivert\jmustache\1.9\jmustache-1.9.jar;C:\Users\narkesau\.m2\repository\com\google\code\findbugs\jsr305\2.0.1\jsr305-2.0.1.jar;C:\Users\narkesau\.m2\repository\org\mb4j\mb4j-example-domain\0.3.5-SNAPSHOT\mb4j-example-domain-0.3.5-SNAPSHOT.jar;C:\Users\narkesau\.m2\repository\com\google\guava\guava\17.0\guava-17.0.jar;C:\Users\narkesau\.m2\repository\junit\junit\4.11\junit-4.11.jar;C:\Users\narkesau\.m2\repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar;C:\Users\narkesau\.m2\repository\org\hamcrest\hamcrest-all\1.3\hamcrest-all-1.3.jar;C:\Users\narkesau\.m2\repository\javax\servlet\javax.servlet-api\3.1.0\javax.servlet-api-3.1.0.jar;C:\Users\narkesau\.m2\repository\org\eclipse\jetty\aggregate\jetty-all\9.2.3.v20140905\jetty-all-9.2.3.v20140905.jar;C:\Users\narkesau\.m2\repository\javax\websocket\javax.websocket-api\1.0\javax.websocket-api-1.0.jar;C:\Users\narkesau\.m2\repository\com\google\inject\extensions\guice-servlet\3.0\guice-servlet-3.0.jar;C:\Users\narkesau\.m2\repository\com\google\inject\guice\3.0\guice-3.0.jar;C:\Users\narkesau\.m2\repository\javax\inject\javax.inject\1\javax.inject-1.jar;C:\Users\narkesau\.m2\repository\aopalliance\aopalliance\1.0\aopalliance-1.0.jar;C:\Users\narkesau\.m2\repository\org\slf4j\slf4j-simple\1.7.5\slf4j-simple-1.7.5.jar;C:\Users\narkesau\.m2\repository\org\slf4j\slf4j-api\1.7.5\slf4j-api-1.7.5.jar
set CP=%CP%;target/test-classes
set CP=%CP%;target/classes

java -cp %CP% org.mb4j.example.servlet.StartServletSampleJetty
