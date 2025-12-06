let users = [
    {
        firstName: "John",
        lastName: "Smith",
        hoursWorked: 73
    },
    {
        firstName: "Jane",
        lastName: "Doe",
        hoursWorked: 80
    }
]

const getUsers = () => {
    return users;
}

const addUser = (user) => {
    users = users.concat([user]);
    return user;
}

module.exports = { getUsers, addUser };
