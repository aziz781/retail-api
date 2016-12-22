FROM java:8
VOLUME /tmp
ADD retail-api app.jar
RUN bash -c 'touch app.jar'
ENTRYPOINT ["java","-jar","app.jar"]
