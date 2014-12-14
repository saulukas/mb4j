#mvn dependency:build-classpath
MVN_LIB=/Users/saulukas/.m2/repository
CP=/Users/saulukas/.m2/repository/org/mb4j/mb4j-servlet/0.3.5-SNAPSHOT/mb4j-servlet-0.3.5-SNAPSHOT.jar:/Users/saulukas/.m2/repository/org/mb4j/mb4j-component/0.3.5-SNAPSHOT/mb4j-component-0.3.5-SNAPSHOT.jar:/Users/saulukas/.m2/repository/org/mb4j/mb4j-brick/0.3.5-SNAPSHOT/mb4j-brick-0.3.5-SNAPSHOT.jar:/Users/saulukas/.m2/repository/com/google/guava/guava/17.0/guava-17.0.jar:/Users/saulukas/.m2/repository/com/google/code/findbugs/jsr305/2.0.1/jsr305-2.0.1.jar:/Users/saulukas/.m2/repository/junit/junit/4.11/junit-4.11.jar:/Users/saulukas/.m2/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar:/Users/saulukas/.m2/repository/org/hamcrest/hamcrest-all/1.3/hamcrest-all-1.3.jar:/Users/saulukas/.m2/repository/org/eclipse/jetty/aggregate/jetty-all/9.2.5.v20141112/jetty-all-9.2.5.v20141112.jar:/Users/saulukas/.m2/repository/javax/websocket/javax.websocket-api/1.0/javax.websocket-api-1.0.jar:/Users/saulukas/.m2/repository/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar
CP=$CP:target/test-classes
CP=$CP:target/classes

time java -cp $CP org.mb4j.servlet.BrickJettyTest
