FROM openjdk:8
LABEL authors="Administrator"
ENV MyPath /opt/project
WORKDIR $MyPath
# 复制主机jar包至镜像内，复制的目录需放置在Dockerfile文件同级目录下
ADD target/springboot2-project-0.0.1-SNAPSHOT.jar $MyPath/app.jar
COPY config/* $MyPath/config/
# 容器启动执行命令
ENTRYPOINT ["java","-jar","./app.jar"]
# 对外暴露的端口号
EXPOSE 10001