import DogViewer from "./DogViewer";

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

export default DogList;