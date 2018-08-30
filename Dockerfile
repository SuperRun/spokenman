FROM jdk:8u131
MAINTAINER czg "chengzhigang@zust.edu.cn"
LABEL Description="ZUST Spokesman" Vendor="czg" Version="1.0"
RUN mkdir -p /opt/spokesman
ADD $WORKSPACE/target/ota-web-0.0.1-SNAPSHOT.jar /opt/spokesman
ENV JAVA_HOME /opt/jdk
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV M2_HOME /opt/maven
ENV PATH $JAVA_HOME/bin:$PATH
EXPOSE 8080
ENTRYPOINT ["java","-Dfile.encoding=UTF-8","-jar","/opt/spokesman/ota-web-0.0.1-SNAPSHOT.jar"]