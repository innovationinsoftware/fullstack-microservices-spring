var express = require('express');
const { getUsers, addUser } = require('./data/users');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {
  var users = getUsers();
  res.send(users);
});

router.post('/', function(req, res, next) {
  var user = req.body;
  if (!user || Object.keys(user).length === 0) {
    return res.status(400).json({ error: 'No user provided' });
  }
  try {
    const added = addUser(user);
    res.status(201).json(added);
  } catch (err) {
    next(err);
  }
});

module.exports = router;
