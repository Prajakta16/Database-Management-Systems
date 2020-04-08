const mongoose = require('mongoose');

const userSchema = mongoose.Schema({
    username: String,
    firstname: String,
    lastname: String,
    salary: Number,
    dob: Date
},{collection:'users'});

module.exports = userSchema;