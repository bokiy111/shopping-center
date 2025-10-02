FROM openjdk:23
COPY target/shopping-0.0.1-SNAPSHOT.jar /application.jar
EXPOSE 8080
ENV TZ Asia/Shanghai
ENTRYPOINT ["java","-XX:MinRAMPercentage=40.0","-XX:MaxRAMPercentage=60.0","-jar","/application.jar","--spring.profiles.active=production"]
FROM nginx
RUN mkdir -p /dist
COPY ./nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
ADD ./dist /dist
CMD ["nginx", "-g", "daemon off;"]
