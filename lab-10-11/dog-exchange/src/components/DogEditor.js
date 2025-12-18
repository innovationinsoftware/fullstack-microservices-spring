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