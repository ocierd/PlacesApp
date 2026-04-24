# Stage 1: Build the Angular application
FROM node:24-alpine AS build
WORKDIR /app

# Copy dependency files first for better caching
COPY package*.json ./
RUN npm install

# Copy the rest of the source code and build
COPY . .
RUN npm run build --configuration=production

# Stage 2: Serve the application using Nginx
FROM nginx:alpine as stageee
# Copy built files from Stage 1 to Nginx default public folder
# Note: Path may vary (e.g., /app/dist/<project-name>/browser) depending on Angular version
COPY --from=build /app/dist/places-web-app/browser /usr/share/nginx/html
COPY --from=build /app/dist/places-web-app/browser/index.csr.html /usr/share/nginx/html/index.html

# Expose port 80
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]


