function create()
{
var ename=document.getElementById('ename').value;
var edate=document.getElementById('edate').value;
var eloc=document.getElementById('eloc').value;
var db = openDatabase('mydb', '1.0', 'Test DB', 2 * 1024 * 1024);
var msg;
db.transaction(function (tx) {
  tx.executeSql('CREATE TABLE IF NOT EXISTS LOGS (id unique, log)');
  tx.executeSql('INSERT INTO LOGS (id, log) VALUES (1, #ename)');
  tx.executeSql('INSERT INTO LOGS (id, log) VALUES (2, #edate)');
  msg = '<p>Log message created and row inserted.</p>';
  //document.querySelector('#status').innerHTML =  msg;
});

db.transaction(function (tx) {
  tx.executeSql('SELECT * FROM LOGS', [], function (tx, results) {
   var len = results.rows.length, i;
   msg = "<p>Found rows: " + len + "</p>";
   //document.querySelector('#status').innerHTML +=  msg;
   for (i = 0; i < len; i++){
     msg = "<p><b>" + results.rows.item(i).log + "</b></p>";
     //document.querySelector('#status').innerHTML +=  msg;
   }
 }, null);
});
}