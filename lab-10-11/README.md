# Lab 10 - React Basics
In this lab we will create a basic React website and write some controls with mocked values. This same app will be the starting point for Lab 11.

## Step 1 - Create app
Open a Terminal and we will create the app. This app will be to help match dogs and people for new homes (for the dogs...).

1. In the terminal run the following command `npx create-react-app dog-exchange`
2. You may be prompted to install the package, approve it with `y`
3. Once it is finished, change into the dog-exchange directory and run the app.
```powershell
cd dog-exchange
npm start
```
It will launch the default browser and you'll see the app. Leave the browser running, it will auto-refresh as you code, and return to VS Code.

## Step 2 - Clear out the defaults.
1. Open `src/App.js` and look around.
2. Remove the content from within the header, leaving the header in place.
3. Save and verify the new look in the browser.
4. Add a new title title and blurb in body of the page.
```javascript
function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Welcome to Dog Exchange!</h1>
        <p>Your one-stop platform for adopting and exchanging dogs.</p>
      </header>
    </div>
  );
}
```

5. Save and verify the look.

## Step 3 - Create a dog component
1. From `/src` add a new folder called `components`
2. In components add a new file called `DogViewer.js`
3. Create a functional component that takes a single dog to display.
```javascript
import './DogViewer.css';

const DogViewer = ({dog}) => {
  return (
    <div className="dog-viewer">
        <h2>{dog.name}</h2>
        <p>Breed: {dog.breed}</p>
        <p>Age: {dog.age} years</p>
    </div>
    );
};

export default DogViewer;
```
4. Add another file called `DogViewer.css`
```css
.dog-viewer {
    border: 2px solid #ccc;
    padding: 20px;
    border-radius: 10px;
    text-align: center;
    background-color: #f9f9f9;
    color: black;
}
```
## Step 4 - Create a dog list component
1. Add a new file `DogList.js`
```javascript
import DogViewer from "./DogViewer";

const DogList = ({dogs}) => {
    return (
        <div className="dog-list">
            <h2>Available Dogs for Exchange</h2>
            {dogs.map(dog => (
                <DogViewer key={dog.id} dog={dog} />
            ))}
        </div>
    );
}

export default DogList;
```

## Step 5 - Display a Mock list
1. Return to App.js
2. Add some mock data to the top of the file. This will be deleted in Lab-11
```javascript
const dogs = [
  { id: 1, name: 'Buddy', breed: 'Golden Retriever', age: 3, available: true },
  { id: 2, name: 'Max', breed: 'German Shepherd', age: 5, available: false },
  { id: 3, name: 'Bella', breed: 'Labrador Retriever', age: 2, available: true },
];
```
3. Display the dogs with the components you created.
```javascript
import './App.css';
import DogList from './components/DogList';

const dogs = [
  { id: 1, name: 'Buddy', breed: 'Golden Retriever', age: 3, available: true },
  { id: 2, name: 'Max', breed: 'German Shepherd', age: 5, available: false },
  { id: 3, name: 'Bella', breed: 'Labrador Retriever', age: 2, available: true },
];

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Welcome to Dog Exchange!</h1>
        <p>Your one-stop platform for adopting and exchanging dogs.</p>
        <DogList dogs={dogs} />
      </header>
    </div>
  );
}

export default App;
```

## Step 6 - Add an 'Adopt' button
In this step we'll use events and callbacks to allow for an adoption. This button will be in the DogViewer control.

1. Add a button that is disabled if the dog is not available.
```javascript
const DogViewer = ({dog}) => {
  return (
    <div className="dog-viewer">
        <h2>{dog.name}</h2>
        <p>Breed: {dog.breed}</p>
        <p>Age: {dog.age} years</p>
        <button disabled={!dog.available}>
          {dog.available ? `Adopt ${dog.name}` : `${dog.name} Not Available`}
        </button>
    </div>
    );
};
```
2. Add some styling to the button in the css file.
```css
.dog-viewer button {
    background-color: #4CAF50;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.dog-viewer button:disabled {
    background-color: #ccc;
    cursor: not-allowed;
}
```
## Step 7 - Add an event callback
In this step we will tag a dog as not available if the button is clicked.
1. Add a new parameter to the DogViewer called `onAdopt` and bind it to the button.
```javascript
const DogViewer = ({dog, onAdopt}) => {
  return (
    <div className="dog-viewer">
        <h2>{dog.name}</h2>
        <p>Breed: {dog.breed}</p>
        <p>Age: {dog.age} years</p>
        <button disabled={!dog.available} onClick={() => onAdopt(dog.id)}>
          {dog.available ? `Adopt ${dog.name}` : `${dog.name} Not Available`}
        </button>
    </div>
    );
};
```
2. Update DogList to pass through the OnAdopt event.
```javascript
const DogList = ({dogs, onAdopt}) => {
    return (
        <div className="dog-list">
            <h2>Available Dogs for Exchange</h2>
            {dogs.map(dog => (
                <DogViewer key={dog.id} dog={dog} onAdopt={onAdopt} />
            ))}
        </div>
    );
}
```
3. Update App.js to store the dog list in state (instead of direct access) and to update the state of the list on the event.
```javascript
import { useState } from 'react';
import './App.css';
import DogList from './components/DogList';

const dogsMock = [
  { id: 1, name: 'Buddy', breed: 'Golden Retriever', age: 3, available: true },
  { id: 2, name: 'Max', breed: 'German Shepherd', age: 5, available: false },
  { id: 3, name: 'Bella', breed: 'Labrador Retriever', age: 2, available: true },
];

function App() {
  const [dogs, setDogs] = useState(dogsMock);

  const handleAdopt = (id) => {
    setDogs(dogs.map(dog => dog.id === id ? {...dog, available: false} : dog));
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>Welcome to Dog Exchange!</h1>
        <p>Your one-stop platform for adopting and exchanging dogs.</p>
        <DogList dogs={dogs} onAdopt={handleAdopt} />
      </header>
    </div>
  );
}

export default App;
```

## Step 8 - Make it your own
In this step add your own css for the DogList (we put in a class name but never defined the css) to make it look like you want. Also, you can update any other CSS to get the look and feel you want for the page.

# Conclusion
You created a React app! With the app you were able to display a list of data.

**Stop here until instructed to do Lab 11**

# Lab 11 - React and REST
In this lab we will fetch dog data from your prior API with MongoDB - Lab 8.

## Step 1 - Create an API library.
Rather than have each component do `fetch` calls, we'll use a repository to centralize the REST calls.

1. Off of `/src` create a folder called `lib`
2. In `lib` create a file called `api.js`
```javascript
const dogUrl = 'http://localhost:8080/dogs';

export async function fetchDogs() {
    const response = await fetch(dogUrl);
    if (!response.ok) {
        throw new Error('Failed to fetch dogs');
    }
    return await response.json();
}

export async function addDog(dog) {
    const response = await fetch(dogUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dog),
    });
    if (!response.ok) {
        throw new Error('Failed to add dog');
    }
    return await response.json();
}
```
3. Add an updateDog function.

## Step 2 - Replace mock data with real data
1. Update App.js to call fetchDogs()
2. Use a start event hook to do this.
```javascript
import { useState, useEffect } from 'react';
import './App.css';
import DogList from './components/DogList';
import { fetchDogs } from './lib/api.js';

function App() {
  const [dogs, setDogs] = useState([]);

  useEffect(() => {
    fetchDogs().then(setDogs).catch(console.error);
  }, []);

  const handleAdopt = (id) => {
    setDogs(dogs.map(dog => dog.id === id ? {...dog, available: false} : dog));
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>Welcome to Dog Exchange!</h1>
        <p>Your one-stop platform for adopting and exchanging dogs.</p>
        <DogList dogs={dogs} onAdopt={handleAdopt} />
      </header>
    </div>
  );
}

export default App;
```

## Step 3 - Update Lab 8 to accept CORS connections
In order for this to work, your dog API from Lab 8 needs to accept cross site requests. We'll make it totally permissive.
1. Open Lab 8 in VS Code
3. In the configuration folder add an new Configuration class called `CoresConfiguration`
```java
package com.example.dog_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("*")
            .allowedHeaders("*");
    }
}
```
4. Build and run
## Step 4 - Verify data
Reload your dog exchange in the browser and verify you have data.

## Step 5 - Add `available` to the source data
Update your Java app to contain the `available` boolean we are expecting. Since this is a MongoDB app, you don't have to migrate the change to the entity, just add the field and rebuild.

## Step 6 - Now for the fun!
Using everything you've leared so far, have the callback when a dog is adoptied PUT the change back to your dog API so that it persists.
Then, try out adding a form to edit the dog, including making it availble again. The form should also work for adding a new dog.
Hint: Add a DogEditor.css and DogEditor.js
Here's a kickstarter for the editor:
```javascript
import '.DogEditor.css';
import { useState } from 'react';

const DogEditor = ({dog, onSave}) => {
  const [name, setName] = useState(dog.name);
  const [breed, setBreed] = useState(dog.breed);
  const [age, setAge] = useState(dog.age);
  const [available, setAvailable] = useState(dog.available);

  const handleSubmit = (e) => {
    e.preventDefault();
    onSave({ ...dog, name, breed, age, available });
  };
    return (
    <form className="dog-editor" onSubmit={handleSubmit}>
        <label>
            Name:
            <input type="text" value={name} onChange={e => setName(e.target.value)} />
        </label>
        <label>
            Breed:
            <input type="text" value={breed} onChange={e => setBreed(e.target.value)} />
        </label>
        <label>
            Age:
            <input type="number" value={age} onChange={e => setAge(Number(e.target.value))} />
        </label>
        <label>
            Available:
            <input type="checkbox" checked={available} onChange={e => setAvailable(e.target.checked)} />
        </label>
        <button type="submit">Save</button>
    </form>)
};

export default DogEditor;
```

You'll have to figure out `onSave` and refreshing the list when the data changes. I'm here to help you!

# Conclusion
Congraduations you've created a full stack application from microservice to UI.