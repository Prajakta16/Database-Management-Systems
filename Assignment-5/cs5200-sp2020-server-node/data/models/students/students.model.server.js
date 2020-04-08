const mongoose = require('mongoose');
const studentSchema = require('./students.schema.server');

const studentModel = mongoose.model("StudentModel",studentSchema);

module.exports = studentModel;