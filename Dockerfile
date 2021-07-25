FROM maven:3.6.3-openjdk-11 AS maven_build
ADD . /src
WORKDIR /src

RUN mvn package

EXPOSE 8086
HEALTHCHECK --retries=12 --interval=10s CMD curl -s localhost:8086/health || exit 1
RUN ls -lart ./target
RUN cp ./target/credit-card-processor-0.0.1-SNAPSHOT.jar /usr/local/bin/credit-card-processor.jar

RUN chmod +x /usr/local/bin/credit-card-processor.jar
ENTRYPOINT ["java", "-jar", "/usr/local/bin/credit-card-processor.jar"]
