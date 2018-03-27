FROM openjdk:8-jre

MAINTAINER "Mark Eyberg <mark_eyberg@optum.com>"

EXPOSE 8080

# Copy the docker-entrypoint.sh script to /usr/local/bin, so it is available on the $PATH variable
COPY docker-entrypoint.sh /usr/local/bin

WORKDIR /opt

# JAR_FILE get substituted by Gradle at build-time to be the archive name
COPY $JAR_FILE .

# Create a symbolic link to a static name for run convenience.
RUN ln -s $JAR_FILE app.jar

# Define our entrypoint script and start the application
ENTRYPOINT ["docker-entrypoint.sh"]
CMD [""]
