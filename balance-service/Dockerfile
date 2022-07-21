
#stage 1
#Start with a base image containing Java runtime
FROM openjdk:17-alpine as build

# Add Maintainer Info
LABEL maintainer="Ricardo A. Ferrari <ricardoaugusto.ferrari@gmail.com>"

# The application's jar file
# The jar file is the same defined on pom.xml
ARG JAR_FILE=target/balance-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
COPY ${JAR_FILE} app.jar

#unpackage jar file
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

#stage 2
#Same Java runtime
FROM openjdk:18-alpine

EXPOSE 8081

# Add volume pointing to /tmp
VOLUME /tmp

#Copy unpackaged application to new container
ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

#execute the application
ENTRYPOINT ["java","-cp","app:app/lib/*","com.autopeca.client.BalanceApplication"]