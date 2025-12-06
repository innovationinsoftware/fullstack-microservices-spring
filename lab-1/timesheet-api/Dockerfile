FROM node:25-alpine

# Create app directory
WORKDIR /usr/src/app

# Install dependencies (production only).
COPY package.json package-lock.json ./
RUN npm ci

# Copy app sources
COPY . .

# Create a non-root user to run the app
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Default port and environment
ENV NODE_ENV=production
ENV PORT=3000

EXPOSE 3000

# Start the server
CMD ["node", "./bin/www"]
