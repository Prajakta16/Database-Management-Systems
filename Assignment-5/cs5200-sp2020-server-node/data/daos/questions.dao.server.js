const questionModel = require('../models/questions/questions.model.server');

const findAllQuestions = () =>
    questionModel.find()

const findQuestionById = (id) =>
    questionModel.findById(id)

const createQuestion = (question) =>
    questionModel.create(question)

const deleteQuestion = (id) =>
    questionModel.deleteOne({_id:id})

const deleteAll =() =>
    questionModel.deleteMany({})

module.exports ={
    findAllQuestions,
    findQuestionById,
    createQuestion,
    deleteQuestion,
    deleteAll
}