const answerModel = require('../models/answers/answers.model.server')

const answerQuestion = (id, trueFalseAnswer, multipleChoiceAnswer, studentId, questionId) =>
    answerModel.create({
        _id: id,
        trueFalseAnswer: trueFalseAnswer,
        multipleChoiceAnswer: multipleChoiceAnswer,
        student: studentId,
        question: questionId
    });


const findAllAnswers = () =>
    answerModel.find()
        .populate('student', 'username')
        .populate('question', 'question')
        .exec()

const findAnswerById = (id) =>
    answerModel.findById(id)
        .populate('student', 'username')
        .populate('question', 'question')
        .exec()

const findAnswersByStudent = (studentId) =>
    answerModel.find({student: studentId})
        .populate('student', 'username')
        .populate('question', 'question')
        .exec()

const findAnswersByQuestion = (questionId) =>
    answerModel.find({question: questionId})
        .populate('student', 'username')
        .populate('question', 'question')
        .exec()

const deleteByStudentAndQuestion = (studentId, questionId) =>
    answerModel.deleteOne({student: studentId, question: questionId})

const deleteAnswer = (id) =>
    answerModel.deleteOne({_id: id})

const deleteAll =() =>
    answerModel.deleteMany({})

module.exports = {
    answerQuestion,
    findAllAnswers,
    findAnswerById,
    findAnswersByQuestion,
    findAnswersByStudent,
    deleteAnswer,
    deleteAll,
    deleteByStudentAndQuestion
}