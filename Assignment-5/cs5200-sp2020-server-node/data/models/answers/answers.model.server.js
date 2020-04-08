const mongoose = require("mongoose");
const answerSchema = require('./answers.schema.server');

const answerModel = mongoose.model("AnswerModel",answerSchema);

module.exports = answerModel;