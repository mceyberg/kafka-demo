

## Building and Running As a Docker Container

To build a Docker image, run the command:

```
./gradlew docker
```

This will create a new docker image, under the name `docker.optum.com/clm_devops/producer:<version>`

This container may be run normally, using the standard command:

```
docker run -p 8080:8080 docker.optum.com/clm_devops/producer:<version>
```

Any parameters passed into the `run` command will be executed as-is instead of running the app.