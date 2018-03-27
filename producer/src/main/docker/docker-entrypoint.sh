#!/usr/bin/env bash
set -e

# Can put default arguments here, i.e. -Denv=prod
java_opts=''

# If a parameter was passed into this entrypoint, run it. Otherwise, run the app.
if [[ -z "$1" ]]; then
  java -jar ${java_opts} /opt/app.jar
else
    # Not sure what command the user was running, so we'll just run it as-is.
    exec "$@"
fi

