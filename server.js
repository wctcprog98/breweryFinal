const express = require('express'); 
const serveStatic = require('serve-static');
const path = require('path');
const post
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

const { Client } = require('pg');

const client = new Client({
  connectionString: process.env.DATABASE_URL,
  ssl: {
    rejectUnauthorized: false
  }
});

client.connect();

client.query('SELECT table_schema,table_name FROM information_schema.tables;', (err, res) => {
  if (err) throw err;
  for (let row of res.rows) {
    console.log(JSON.stringify(row));
  }
  client.end();
});