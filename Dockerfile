### <<<------------------------------------------------------------------------------------------------------------------------------------------>>> ###
### <<<---------------------------------------------------Stage 1: Build the component----------------------------------------------------------->>> ###
### <<<------------------------------------------------------------------------------------------------------------------------------------------>>> ###

FROM alpine:3.7 AS build-stage

ENV PROJECT_HOME="/rock-paper-scissors"

# Get the source code to build
COPY src ${PROJECT_HOME}/src
COPY pom.xml ${PROJECT_HOME}

WORKDIR ${PROJECT_HOME}

# Install Java and Maven, and then build the stuff
RUN apk --update add openjdk8 maven bash procps && \
    rm -rf /var/cache/apk/* && \
    mvn dependency:resolve && \
    mvn verify && \
    mvn package



### <<<------------------------------------------------------------------------------------------------------------------------------------------>>> ###
### <<<---------------------------------------------------Stage 2: Prepare the final image------------------------------------------------------->>> ###
### <<<------------------------------------------------------------------------------------------------------------------------------------------>>> ###

FROM alpine:3.7

ENV PROJECT_HOME="/rock-paper-scissors"

RUN apk --update add openjdk8-jre maven bash

COPY --from=build-stage ${PROJECT_HOME} ${PROJECT_HOME}

WORKDIR ${PROJECT_HOME}

CMD /usr/bin/java -jar target/*.jar
