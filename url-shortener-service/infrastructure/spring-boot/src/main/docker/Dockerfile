FROM amazoncorretto:17-al2-jdk
MAINTAINER Sergio Exposito <sergio.exposito@teracode.com>

# ENV PORT                 <port to run server>
# ENV DATABASE_HOST        <postgres server host name>
# ENV DATABASE_PORT        <postgres server port>
# ENV DATABASE_SCHEMA      <postgres schema>
# ENV DATABASE_USER        <postgres username>
# ENV DATABASE_PASSWORD    <postgres password>
# ENV REDIS_HOST           <redis server host name>
# ENV REDIS_PORT           <redis server port>
# ENV SHORTENER_DOMAIN     <url shortener domain>

ADD maven/${project.build.finalName}.jar /opt/${project.build.finalName}.jar

RUN bash -c 'touch /opt/${project.build.finalName}.jar'

RUN echo "#!"

RUN echo "#!/usr/bin/env bash" > /opt/entrypoint.sh && \
    echo "" >> /opt/entrypoint.sh && \
    echo "echo \"===============================================\" " >> /opt/entrypoint.sh && \
    echo "echo \"PORT: \$PORT \" " >> /opt/entrypoint.sh && \
    echo "echo \"DATABASE_HOST: \$DATABASE_HOST \" " >> /opt/entrypoint.sh && \
    echo "echo \"DATABASE_PORT: \$DATABASE_PORT \" " >> /opt/entrypoint.sh && \
    echo "echo \"DATABASE_SCHEMA: \$DATABASE_SCHEMA \" " >> /opt/entrypoint.sh && \
    echo "echo \"DATABASE_USER: \$DATABASE_USER \" " >> /opt/entrypoint.sh && \
    echo "echo \"DATABASE_PASSWORD: \$DATABASE_PASSWORD \" " >> /opt/entrypoint.sh && \
    echo "echo \"REDIS_HOST: \$REDIS_HOST \" " >> /opt/entrypoint.sh && \
    echo "echo \"REDIS_PORT: \$REDIS_PORT \" " >> /opt/entrypoint.sh && \
    echo "echo \"===============================================\" " >> /opt/entrypoint.sh && \
    echo "" >> /opt/entrypoint.sh && \
    echo "echo \"singleServerConfig:\" > /opt/redisson.yaml " >> /opt/entrypoint.sh && \
    echo "echo \"  address: redis://\$REDIS_HOST:\$REDIS_PORT\" >> /opt/redisson.yaml " >> /opt/entrypoint.sh && \
    echo "" >> /opt/entrypoint.sh && \
    echo "java \
        -Dserver.port=\$PORT \
        -Dmanagement.server.port=\$PORT \
        -Dspring.datasource.host=\$DATABASE_HOST \
        -Dspring.datasource.port=\$DATABASE_PORT \
        -Dspring.datasource.schemaName=\$DATABASE_SCHEMA \
        -Dspring.datasource.username=\$DATABASE_USER \
        -Dspring.datasource.password=\$DATABASE_PASSWORD \
        -Dspring.jpa.properties.hibernate.cache.redisson.config=/opt/redisson.yaml \
        -Dfarmu.interview.service.urlshortener.domain=\$SHORTENER_DOMAIN \
        -jar /opt/${project.build.finalName}.jar" >> /opt/entrypoint.sh

RUN chmod 755 /opt/entrypoint.sh

EXPOSE ${PORT}

ENTRYPOINT [ "/opt/entrypoint.sh" ]

