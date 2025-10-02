FROM nginx
RUN mkdir -p /dist
COPY ./nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
ADD ./dist /dist
CMD ["nginx", "-g", "daemon off;"]
