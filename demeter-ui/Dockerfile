#FROM node:16-alpine AS build
#WORKDIR /app
#COPY . .
#RUN npm install
#RUN npm run build
#
## Serve Application using Nginx Server
#FROM nginx:alpine
#COPY --from=build /app/dist/demeter-ui /usr/share/nginx/html
#COPY nginx.conf /etc/nginx/nginx.conf
#EXPOSE 80
FROM node:19-alpine AS build
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:1.23.2-alpine
COPY ./nginx.conf /etc/nginx/nginx.conf
COPY --from=build /app/dist/demeter-ui /usr/share/nginx/html

