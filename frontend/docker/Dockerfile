# Stage 1: Build the Angular application
FROM node:20-alpine AS build
WORKDIR /app
COPY package*.json package-lock.json ./ ./
RUN npm install
COPY . .
RUN npm run build

# Stage 2: Serve the application with Nginx
FROM nginx:alpine
# Copy the build output to the Nginx html directory
COPY --from=build /app/dist/frontend /usr/share/nginx/html
# Expose the port that the application will run on
EXPOSE 80
# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]
