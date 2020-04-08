const studentModel = require('../models/students/students.model.server');

const findAllStudents = () =>
    studentModel.find()

const findStudentById = (id) =>
    studentModel.findById(id)
    //studentModel.findOne({_id:id})

const createStudent = (student) =>
    studentModel.create(student)

const deleteStudent = (id) =>
    studentModel.deleteOne({_id:id})

const deleteAll =() =>
    studentModel.deleteMany({})

module.exports = {
    findAllStudents,
    findStudentById,
    createStudent,
    deleteStudent,
    deleteAll
};