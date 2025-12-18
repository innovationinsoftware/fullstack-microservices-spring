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

export async function updateDog(dog) {
    const response = await fetch(`${dogUrl}/${dog.id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dog),
    });
    if (!response.ok) {
        throw new Error('Failed to update dog');
    }
    return await response.json();
}