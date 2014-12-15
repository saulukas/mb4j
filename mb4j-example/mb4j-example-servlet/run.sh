#mvn dependency:build-classpath
MVN_LIB=/Users/saulukas/.m2/repository
CP=target/mb4j-example-servlet/WEB-INF/lib/*
CP=$CP:$MVN_LIB/org/eclipse/jetty/aggregate/jetty-all/9.2.5.v20141112/jetty-all-9.2.5.v20141112.jar
CP=$CP:$MVN_LIB/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar
CP=$CP:target/test-classes
CP=$CP:target/classes

time java -cp $CP org.mb4j.example.servlet.StartServletSampleJetty
