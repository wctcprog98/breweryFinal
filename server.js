const express = require('express'); 
const serveStatic = require('serve-static');
const path = require('path');
const app = express();

// begin db connection
const conn
// end db connection

app.use('/', serveStatic(path.join(__dirname, '/dist/')))

// this * route is to serve project on different page routes except root `/`
app.get(/.*/, function (req, res) {
    res.sendFile(path.join(__dirname, '/dist/index.html'))
})

const port = process.env.PORT || 8080; 
app.listen(port); 

console.log("Listening on port: " + port); 
//testing for auto deploynent to heroku
