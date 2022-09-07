FROM openjdk:17

COPY target/tweetapp.jar tweetapp.jar

ENTRYPOINT ["java","-jar","/tweetapp.jar"]