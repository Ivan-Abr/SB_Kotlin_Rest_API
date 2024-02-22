
function createTable(jsonData) {
    let table = '<table><tr><th>Author ID</th><th>Author Name</th><th>Email</th><th>Date of Birth</th><th>Age</th></tr>';
    for (let i = 0; i < jsonData.length; i++) {
        table += '<tr>';
        table += '<td>' + jsonData[i].author_id + '</td>';
        table += '<td>' + jsonData[i].author_name + '</td>';
        table += '<td>' + jsonData[i].email + '</td>';
        table += '<td>' + jsonData[i].dob + '</td>';
        table += '<td>' + jsonData[i].age + '</td>';
        table += '</tr>';
    }
    table += '</table>';
    htmlTable = table;
    document.getElementById("table_div").innerHTML = htmlTable;
}





fetch('http://localhost:8080/dm/v1/answer/all/org/1').then(result => {
    console.log(result)
    result.json().then(json => {
        createTable(json)
    }).catch(err => {
        console.error(err)
    })
}).catch(err => {
    console.error(err)
})