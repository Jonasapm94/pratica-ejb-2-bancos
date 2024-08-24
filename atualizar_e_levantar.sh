# !/bin/sh
mvn install && \
docker build --tag=wildfly-app . && \ 
docker run -p 8080:8080 -it wildfly-app