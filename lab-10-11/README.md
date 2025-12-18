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
In this lab we will fetch dog data from your prior API with MongoDB.

