FROM amazoncorretto:17

COPY target/notesapp.jar /tmp/notesapp.jar

CMD ["java", "-jar", "/tmp/notesapp.jar"]