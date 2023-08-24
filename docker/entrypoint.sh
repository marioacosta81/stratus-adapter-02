#!/bin/bash

java -Djava.security.egd=file:/dev/./urandom -Xms256m -Xmx1024m -jar /app.jar
echo '**** Launching application ****'