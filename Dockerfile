FROM adoptopenjdk:11-jre-hotspot

RUN mkdir /opt/app

WORKDIR /opt/app

COPY target/Storage-0.0.1-SNAPSHOT.war .

EXPOSE 9999

CMD ["java", "-jar", "Storage-0.0.1-SNAPSHOT.war", "--spring.config.location=resources/", "--spring.profiles.active=prod"]
