#!/bin/bash

# Build Docker images for dog-api and human-api services
# Tags images with project name and 'latest'

set -e  # Exit on error

PROJECT_NAME="fullstack-microservices"
TAG="latest"

echo "Building Docker images for fullstack-microservices project..."
echo ""

# Build dog-api image
echo "Building dog-api image..."
docker build -t "${PROJECT_NAME}-dog-api:${TAG}" -t "${PROJECT_NAME}-dog-api:latest" ./dog-api
echo "✓ dog-api image built successfully"
echo ""

# Build human-api image
echo "Building human-api image..."
docker build -t "${PROJECT_NAME}-human-api:${TAG}" -t "${PROJECT_NAME}-human-api:latest" ./human-api
echo "✓ human-api image built successfully"
echo ""

# List built images
echo "Built images:"
docker images | grep "${PROJECT_NAME}"
