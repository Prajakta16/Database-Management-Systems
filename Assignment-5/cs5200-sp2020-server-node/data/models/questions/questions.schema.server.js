const mongoose = require('mongoose');

const TrueFalseSchema = require('../true-false/true-false.schema.server.js');
const MultipleChoiceSchema = require('../multiple-choice/multiple-choice.schema.server.js');

const questionSchema = mongoose.Schema({
    _id: Number,
    question: String,
    points: Number,
    questionType: {
        type: String,
        enum: ['TRUE_FALSE', 'MULTIPLE_CHOICE']
        },
    multipleChoice: MultipleChoiceSchema,
    trueFalse: TrueFalseSchema
},{collection: "questions"});

module.exports = questionSchema;

