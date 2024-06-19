# FROM java:8
FROM arm64v8/openjdk
VOLUME /tmp
ADD retail-api.jar app.jar
RUN bash -c 'touch app.jar'
ENTRYPOINT ["java","-jar","app.jar"]
