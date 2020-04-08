const mongoose = require("mongoose");
const questionSchema = require('../questions/questions.schema.server');

const quizSchema = mongoose.Schema({
    _id: Number,
    questions: [{
        type: mongoose.Schema.Types.ObjectID,
        ref: 'QuestionModel'
    }]
},{collection: 'quizzes'})

module.exports = quizSchema;