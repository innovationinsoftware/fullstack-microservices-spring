# Lab 13 - Kubernetes containers
## Step 1 - Build intial images
In this lab you will need to be in your Linux server the entire time.
1. Locate the file `./build-docker-images.sh` and run it.
2. Verify it ran with no errors.
## Step 2 - Examine the kubernetes yaml file
Open the file `kubernetes-deployment.yaml` and get familiar with it. Note the database images that are being pulled to support the microservices.
## Step 3 - Add the dog-api to the cluster
The dog API uses the mongodb instance. You will override the application.yaml settings for this deployment in the k8s config.
1. Add the dog-api deployment
```yaml
---
# Dog API Deployment
apiVersion: apps/v1
kind: Deployment
metadata:
  name: dog-api
  namespace: fullstack-microservices
  labels:
    app: dog-api
spec:
  replicas: 2
  selector:
    matchLabels:
      app: dog-api
  template:
    metadata:
      labels:
        app: dog-api
    spec:
      containers:
        - name: dog-api
          image: fullstack-microservices-dog-api:latest
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 8080
          env:
            - name: SPRING_DATA_MONGODB_URI
              value: "mongodb://admin:admin-password@mongodb:27017/dog_db?authSource=admin"
            - name: SPRING_DATA_MONGODB_USERNAME
              value: "admin"
            - name: SPRING_DATA_MONGODB_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: mongodb-secret
                  key: mongo-root-password
            - name: SPRING_DATA_MONGODB_DATABASE
              value: "dog_db"
          livenessProbe:
            httpGet:
              path: /actuator/health
              port: 8080
            initialDelaySeconds: 30
            periodSeconds: 10
          readinessProbe:
            httpGet:
              path: /actuator/health/readiness
              port: 8080
            initialDelaySeconds: 20
            periodSeconds: 5
          resources:
            requests:
              memory: "512Mi"
              cpu: "250m"
            limits:
              memory: "1Gi"
              cpu: "500m"
```
2. Add the dog-api service.
```yaml
---
# Dog API Service
apiVersion: v1
kind: Service
metadata:
  name: dog-api
  namespace: fullstack-microservices
spec:
  type: NodePort
  selector:
    app: dog-api
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080      
      nodePort: 30080
```
3. Using what you learned, add the human-api
## Step 4 - Run the services.
1. First, import the docker images into k8s.
```bash
docker save fullstack-microservices-dog-api:latest | /snap/bin/microk8s.ctr image import -
docker save fullstack-microservices-human-api:latest | /snap/bin/microk8s.ctr image import -
```
2. Apply the k8s config to the local system.
```bash
microk8s kubectl apply -f kubernetes-deployment.yaml
```
3. Pull the pods confirm it's working
```bash
microk8s kubectl get pods -n fullstack-microservices
```
## Step 5 - Test the service
1. Curl the dog-api: `curl http://localhost:30080/dogs`
2. Verify you get a response. It should be empty at this point.

# Conclusion
You've created a k8s depolment! This can work in a production envorment.