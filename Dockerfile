FROM openjdk:11
COPY "./target/msproduct-0.0.1-SNAPSHOT.jar" "msproduct.jar"
EXPOSE 8082
ENTRYPOINT ["java","-jar","msproduct.jar"]