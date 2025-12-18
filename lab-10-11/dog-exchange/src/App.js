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
