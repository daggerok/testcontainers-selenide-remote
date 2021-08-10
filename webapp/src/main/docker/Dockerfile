FROM adoptopenjdk/openjdk8:x86_64-alpine-jdk8u292-b10-slim
LABEL MAINTAINER='Maksim Kostromin https://github.com/daggerok'
HEALTHCHECK --start-period=5s --interval=5s --timeout=5s --retries=99 \
        CMD wget --quiet --tries=1 --spider http://127.0.0.1:8080/actuator/health || exit 1
ENTRYPOINT java -jar /tmp/webapp.jar
CMD /bin/ash
ADD --chown=root ./target/*.jar /tmp/webapp.jar
