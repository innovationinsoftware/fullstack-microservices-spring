# Spring Boot Microservices - Lab 1
In this lab, you will run a node based microservice then add a Java SpringBoot microservice and test them both with curl. You will connect to a remove Linux server and deploy the code there.

## Part 1 - Set up a remote SSH session in VS Code
### Create the SSH configuration file
The first thing needed is to install the Remote - SSH extension.
1. Open Visual Studio Code and click on the Extensions icon in the Activity Bar on the left side of the window.
2. Search for "Remote - SSH" in the Extensions Marketplace.
3. Click on the "Install" button to install the extension.
4. Once installed, you will see a new icon in the left sidebar that looks like a computer with a remote connection symbol.
5. Click on the icon to open the Remote Explorer.

In the Remote Explorer, hover your mouse cursor over **SSH**, click on the gear icon (⚙️) in the top right corner, and select the top option. This will open the SSH configuration file in a new editor tab.

# Linux VM information
Copy the Linux VM IP from the [spreadsheet](https://docs.google.com/spreadsheets/d/1zEhmZ6hOPM5lLw_ps83L4cjUsp6bWy7weZSFXtzDLlc/edit?usp=sharing)

### Add the SSH configuration for the lab servers.
Add the following lines to the SSH configuration file, replacing `<IP of Linux server from the spreadsheet>` with the actual IP address of your Linux server.

```plaintext
Host Linux
  HostName <IP of Linux server from the spreadsheet>
  IdentityFile C:\labs\keys\lab.pem
  User TBD
```

### Save the SSH configuration file.
Save the changes to the SSH configuration file and close it.


### Connect to the lab servers.
1. In the Remote Explorer, you should now see the entry for the Tower server under "SSH Targets."
2. Click on the entry to connect to the Linux server.
3. Visual Studio Code will open a new window connected to the Linux server.
4. When prompted for the Operating System, choose 'Linux'.
5. Accept the SSH fingerprint
6. You can now open a terminal in this new window and run commands on the Linux server.

### Clone the repo to Linux server
From your VS Code session in Linux, use the git icon to clone the lab-1 repo.
1. Click **Clone Repositories** and paste `https://github.com/scrumfish/SpringBoot-Lab-1.git`
2. Hit **Enter** and in th epop-up windows, browse to `~/`
3. Click **Select as repository destination**
4. When prompted to open the cloned repo, choose **Open**
You know have the repo on your Linux server and can modify the code in VS Code.

## Part 2 - Run Service
1. Open the bash terminal in VS Code.
2. Change into the `lab-1` folder.
3. Run `ls` to view the contents of the folder.

```plainttext
├── employee-timesheet
│   ├── public
│   ├── routes
│   ├── .dockerignore
│   ├── .gitignore
│   ├── app.js
│   ├── Dockerfile
│   ├── package.json
│   └── README.md
└── README.md
```
4. Run the microservice: 
```bash
docker compose up --build
```

When docker compose runs, it will build and spin up the timesheet-api service, but not the others. You will edit the docker compose file to add the Spring Boot service in a moment. For now, we'll test the service with Postman.
Once you have built with docker compose, you don't have to again until you change the code.
## Part 3 - Test One Microservice
In your bash terminal inside VS Code follow these teps.
1. Run 
```bash
curl http://localhost:3000/users
```
2. You will see two users returned.

3. Next, POST a user to the same URL.

```bash
curl -X POST http://localhost:3000/users \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Sammy","lastName":"Hagar","hoursWorked":12}'
```

4. Finally, run the GET again and observe your new record is listed.
## Part 4 - Add the Next Microservice
The final part of this lab is to observe the pattern laid out for the timesheet-api and add the payroll-api to the docker-compose.yml file. Use curl to get http://localhost/8080/api/employees and verify it is working.
## Conclusion
In this lab you ran a simple node express based microservice, then added a Java based microservice to the infrastructure. This is an example of polygot architecture.
