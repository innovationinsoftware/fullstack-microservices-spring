# Employee Timesheet (Express sample)

This Express app provides a minimal employee timesheet API.

**Quick Start**

- **Prerequisites:** `node` and `npm` installed.
- Install dependencies and start the server:

```powershell
cd timesheet-api
npm install
npm start
```

**Available Services / Endpoints**

- **GET /users**: Returns the current list of users (in-memory).
- **POST /users**: Add a new user. Expects a JSON body with at least one property. Typical fields: `firstName`, `lastName`, `hoursWorked`.

The data is stored in-memory (`routes/data/users.js`), so it resets when the server restarts.

**Examples (curl / PowerShell)**

- Fetch the users list (JSON):

```powershell
curl -i http://localhost:3000/users
```

Example successful response (HTTP 200):

```json
[
	{
		"firstName": "John",
		"lastName": "Smith",
		"hoursWorked": 73
	},
	{
		"firstName": "Jane",
		"lastName": "Doe",
		"hoursWorked": 80
	}
]
```

- Add a user (POST JSON):

```powershell
curl -i -X POST http://localhost:3000/users -H "Content-Type: application/json" -d '{"firstName":"Alice","lastName":"Cooper","hoursWorked":40}'
```

Example successful response (HTTP 201):

```json
{"firstName":"Alice","lastName":"Cooper","hoursWorked":40}
```

- Error when no body provided (HTTP 400):

```powershell
curl -i -X POST http://localhost:3000/users -H "Content-Type: application/json" -d '{}'
```

Response:

```json
{ "error": "No user provided" }
```

**Notes for developers**

- Routes are defined in `routes/users.js` and user data is implemented in `routes/data/users.js`.
- The server is started by `node ./bin/www` (see `npm start` script in `package.json`).

