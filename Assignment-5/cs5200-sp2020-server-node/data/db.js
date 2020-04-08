module.exports = function () {
    const mongoose = require('mongoose');
    const databaseName = 'new';
    let connectionString =
        'mongodb://localhost:27017/';
    connectionString += databaseName;
    mongoose.connect(connectionString,{useNewUrlParser: true, useUnifiedTopology: true });
};


// const mongoose = require('mongoose');
// //const LOCAL_DB = 'mongodb://${MONGODB_HOST}/white-board'
// //const REMOTE_DB = 'URL_TO_YOUR_REMOTE_MONGODB_SERVER'
// const MONGO_DB = 'mongodb://localhost:27017/white-board1' //local host
// mongoose.connect(MONGO_DB,{ useNewUrlParser: true, useUnifiedTopology: true });
//