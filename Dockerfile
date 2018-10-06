FROM tomcat:8-jre8
RUN apt-get update && apt-get upgrade -y

RUN rm -rf webapps/ROOT
COPY target/stationary-store-*.war webapps/ROOT.war

#ENV config
ENV TZ=America/Sao_Paulo
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN chgrp -R 0 /usr/local/tomcat && chmod -R g=u /usr/local/tomcat
