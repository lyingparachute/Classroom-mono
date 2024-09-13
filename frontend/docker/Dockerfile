# Use an official Node.js runtime as a parent image
FROM node:20-alpine AS build

# Set the working directory
WORKDIR /app

# Copy the package.json and install the dependencies
COPY package*.json ./
RUN npm install

# Copy the rest of the application code
COPY . .

# Build the Angular application
RUN npm run build --prod

# Use an official Nginx image to serve the built Angular application
FROM nginx:alpine

# Copy the build output to the Nginx html directory
COPY --from=build /app/dist/classroom /usr/share/nginx/html

# Expose the port that the application will run on
EXPOSE 80

# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]
