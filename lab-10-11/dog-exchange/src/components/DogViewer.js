import './DogViewer.css';

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

export default DogViewer;