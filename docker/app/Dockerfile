FROM openjdk:17

VOLUME /data

ADD mybatis-new-0.0.1-SNAPSHOT.jar /data/app.jar

#使用外部配置文件
ADD application.yml /data/application.yml

ENTRYPOINT ["java", "-jar", "/data/app.jar", "--spring.config.location=/data/application.yml"]

