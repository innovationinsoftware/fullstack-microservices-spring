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
