FROM amazoncorretto:17

ENV LANG=C.UTF-8 \
  LC_ALL=C.UTF-8

VOLUME /tmp

RUN mkdir -p /aws; \
    yum update -y \
    && yum upgrade -y \
    && yum install -y \
    groff \
    less \
    python \
    python-pip \
    jq \
    && pip install awscli \
    && yum clean all

# We make four distinct layers so if there are application changes the library layers can be re-used
COPY ../target/app.jar /app.jar

RUN bash -c 'touch /app.jar'
COPY entrypoint.sh /entrypoint.sh
RUN chmod 777 /entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]
