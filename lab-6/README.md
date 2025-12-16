# Lab 6 - API Gateway and Reverse Proxy
In this lab you'll connect to your Linux server and set up an Nginx reverse proxy for two services.

## Step 1 - Connect to your Linux server
Use the Remote SSH extension to connect to the same Linux server you did in Lab 1.

## Step 2 - Refresh the code
Use git to pull the latest code from GitHub so you can do this lab on the Linux server.

## Step 3 - Review the files
Examine the Dockerfile in the dog-api, then look at the docker-compose.yaml

Notice the difference from Lab 1. We don't expose the Java service directly, but we are using a reverse proxy.

Examine the ./nginx/nginx.conf file to see how we are routing the /api/dogs to the correct service.

## Step 4 - Run the service
You are ready to run the service on the Linux box. Open a bash session and make sure you are in the lab-6 folder. Then run `docker compose up --build -d`

Once it is running test it with curl.

`curl http://localhost/api/dogs`

Confirm you got a list of dogs.

## Step 5 - Time to add a human service
We are going to add people. They can have the following attributes:
- First name
- Last name
- Address
- City
- IsOwner
- IsSelling
- IsBuying

### Step 5.1
Using Initialzr, create a new Java project called human-api and add it to your lab-6 folder, using VS Code to push it to your Linux VM.

### Step 5.2
Once it is on your Linux VM you can fill in the details. You'll need:
- A Human model/POJO
- A data repository like the dogs has
- A controller

We won't worry about Discovery with Eureka in this lab.

### Step 5.3
Run and verify with curl that it is working.

## Step 5.4
Create the Dockerfile, it should be the same as the dogs Dockerfile

## Step 6
Update the nginx.conf to route traffic to /api/humans to your new service.

## Step 7
Update docker-compose to also have the human service.

## Step 8
Run and test it!

# Conclusion
You've built a microservice enviroment with a reverse proxy. This proxy can have a Web Application Firewall (WAF) added, and other security measures put in place if it were production.
