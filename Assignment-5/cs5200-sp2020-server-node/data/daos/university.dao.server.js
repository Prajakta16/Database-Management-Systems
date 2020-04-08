// const answerModel = require('../models/answers/answers.model.server');
// const questionModel = require('../models/questions/questions.model.server');
// const quizModel = require('../models/quizzes/quizzes.model.server');
// const studentModel = require('../models/students/students.model.server');

const studentDao = require('./students.dao.server');
const questionDao = require('./questions.dao.server');
const answerDao = require('./answers.dao.server');

const truncateDatabase = async () => {
    await answerDao.deleteAll()
    await questionDao.deleteAll()
    await studentDao.deleteAll()
}


const populateDatabase = async () => {
    await studentDao.createStudent({
        _id: 123,
        username: "alice",
        password: "alice",
        firstName: "Alice",
        lastName: "Wonderland",
        gradYear: 2020,
        scholarship: 15000
    })
        // .then(student => {
        //     console.log(student)
        // })

    await studentDao.createStudent({
        _id: 234,
        username: "bob",
        password: "bob",
        firstName: "Bob",
        lastName: "Hope",
        gradYear: 2021,
        scholarship: 12000
    })
        // .then(student => {
        //     console.log(student)
        // })

    await questionDao.createQuestion({
        _id: 321,
        question: "Is the following schema valid?",
        points: 10,
        questionType: "TRUE_FALSE",
        trueFalse: {
            _id: 321,
            isTrue: false
        }
    })
        // .then(question => {
        //     console.log(question)
        // })

    await questionDao.createQuestion({
        _id: 432,
        question: "Dao stands for Dynamic Access Object",
        points: 10,
        questionType: "TRUE_FALSE",
        trueFalse: {
            _id: 432,
            isTrue: false
        }
    })
        // .then(question => {
        //     console.log(question)
        // })

    await questionDao.createQuestion({
        _id: 543,
        question: "What does JPA stand for?",
        points: 10,
        questionType: "MULTIPLE_CHOICE",
        multipleChoice: {
            _id: 543,
            choices: "Java Persistence API,Java Persisted Application,JavaScript Persistence API,JSON Persistent Associations",
            correct: 1
        }
    })
        // .then(question => {
        //     console.log(question)
        // })

    await questionDao.createQuestion({
        _id: 654,
        question: "What does ORM stand for?",
        points: 10,
        questionType: "MULTIPLE_CHOICE",
        multipleChoice: {
            _id: 654,
            choices: "Object Relational Model,Object Relative Markup,Object Reflexive Model,Object Relational Mapping",
            correct: 4
        }
    })
        // .then(question => {
        //     console.log(question)
        // })

    await answerDao.answerQuestion(
        123,
        true,
        null,
        "123",
        "321")
        // .then(answer => {
        //     console.log(answer)
        // })

    await answerDao.answerQuestion(
        234,
        true,
        null,
        "123",
        "432")
        // .then(answer => {
        //     console.log(answer)
        // })

    await answerDao.answerQuestion(
        345,
        null,
        1,
        "123",
        "543")
        // .then(answer => {
        //     console.log(answer)
        // })

    await answerDao.answerQuestion(
        456,
        null,
        2,
        "123",
        "654")
        // .then(answer => {
        //     console.log(answer)
        // })

    await answerDao.answerQuestion(
        567,
        false,
        null,
        "234",
        "321")
        // .then(answer => {
        //     console.log(answer)
        // })

    await answerDao.answerQuestion(
        678,
        true,
        null,
        "234",
        "432")
        // .then(answer => {
        //     console.log(answer)
        // })

    await answerDao.answerQuestion(
        789,
        null,
        3,
        "234",
        "543")
        // .then(answer => {
        //     console.log(answer)
        // })

    await answerDao.answerQuestion(
        890,
        null,
        4,
        "234",
        "654")
        // .then(answer => {
        //     console.log(answer)
        // })
}

module.exports = {
    truncateDatabase,
    populateDatabase
}