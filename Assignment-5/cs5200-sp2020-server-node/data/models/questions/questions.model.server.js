const mongoose = require("mongoose");
const questionSchema = require('./questions.schema.server');

const questionModel = mongoose.model("QuestionModel",questionSchema);

module.exports = questionModel;