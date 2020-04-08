require('./data/db')();
const mongoose = require('mongoose');
const universityDao = require('./data/daos/university.dao.server');
const studentDao = require('./data/daos/students.dao.server');
const questionDao = require('./data/daos/questions.dao.server');
const answerDao = require('./data/daos/answers.dao.server')
const assert = require('assert');

const testTruncateDatabase = async () => {
    await universityDao.truncateDatabase()
        .then(() => console.log("Database truncated"))
};

const testPopulateDatabase = async () => {
    await universityDao.populateDatabase()
        .then(() => console.log("Database populated"))
};

const testStudentsInitialCount = async () => {
    let initialStudents = await studentDao.findAllStudents()
    console.log("Students initial count is "+initialStudents.length)
    assert(2==initialStudents.length,"Students not inserted properly")
}

const testQuestionsInitialCount = async () => {
    let initialQuestions = await questionDao.findAllQuestions()
    console.log("Questions initial count is "+initialQuestions.length)
    assert(4==initialQuestions.length,"Questions not inserted properly")
}

const testAnswersInitialCount = async () => {
    let initialAnswers = await answerDao.findAllAnswers();
    console.log("Answers initial count is "+initialAnswers.length);
    assert(8==initialAnswers.length,"Answers not inserted properly");
}

const testDeleteAnswer = async () => {
    await answerDao.deleteByStudentAndQuestion(234,654).then(console.log("Deleted answer by Bob"));

    let answers = await answerDao.findAllAnswers()
    console.log("Total answers count after deletion is "+answers.length);
    assert(7==answers.length,"Answer not deleted properly");

    let answersBob = await answerDao.findAnswersByStudent(234)
    console.log("Total answers count for Bob after deletion is "+answersBob.length);
    assert(3==answersBob.length,"Answer not deleted properly");
}

const testDeleteQuestion = async () => {
    await questionDao.deleteQuestion(321).then(console.log("Deleted question - Is the following schema valid?"));
    let questions = await questionDao.findAllQuestions();
    console.log("Question count is "+questions.length);
    assert(3==questions.length,"Question not deleted properly");
}

const testDeleteStudent = async () => {
    await studentDao.deleteStudent(234).then(console.log("Deleted student Bob"));
    let students = await studentDao.findAllStudents()
    console.log("Students count is "+students.length)
    assert(1==students.length,"Student not deleted properly")
}

const test = async () => {
    await console.log("-----------------------")
    await testTruncateDatabase();
    await testPopulateDatabase();
    await console.log("-----------------------")
    await testStudentsInitialCount();
    await testQuestionsInitialCount();
    await testAnswersInitialCount();
    await console.log("-----------------------")
    await testDeleteAnswer();
    await console.log("-----------------------")
    await testDeleteQuestion();
    await console.log("-----------------------")
    await testDeleteStudent();
};

test();




