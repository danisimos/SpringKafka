<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
    function getByUserId(id) {
        document.getElementById("processedSubContainer").innerHTML = "";
        $.ajax("http://localhost:30081/users/" + id, {
            method: "GET",
            success: function(data, textStatus, jqXHR) {
                for(let i = 0; i < data.length; i++){
                    let firstNameData = data[i].user.firstName;
                    let lastNameData = data[i].user.lastName;
                    let ageData = data[i].user.age;
                    let fromData = new Date(data[i].interval.timestampFrom);
                    let toData = new Date(data[i].interval.timestampTo);
                    let averageWeek = data[i].averageWeekCount;

                    let html = "<div class=\"panel panel-default\">" +
                        "<div class=\"panel-heading\">" + firstNameData + " " + lastNameData + ", " + ageData + "y.o." + "</div>" +
                        "<div class=\"panel-body\">" + fromData + "  -  " + toData + "<br>" + "Average week: " + averageWeek + "</div>" +
                        "</div>";

                    document.getElementById("processedSubContainer").innerHTML += html;
                }
            },
            error: function(jqXHR, textStatus, errorThrown ) {
                alert(jqXHR.status + ' ' + jqXHR.responseText.toString());
            }
        })
    }
    function getByUser(firstName, lastName, age) {
        document.getElementById("processedSubContainer").innerHTML = "";
        $.ajax("http://localhost:30081/users", {
            method: "GET",
            data: {
                firstName: firstName,
                lastName: lastName,
                age: parseInt(age) || 0
            },
            success: function(data, textStatus, jqXHR) {
                for(let i = 0; i < data.length; i++){
                    let firstNameData = data[i].user.firstName;
                    let lastNameData = data[i].user.lastName;
                    let ageData = data[i].user.age;
                    let fromData = new Date(data[i].interval.timestampFrom);
                    let toData = new Date(data[i].interval.timestampTo);
                    let averageWeek = data[i].averageWeekCount;

                    let html = "<div class=\"panel panel-default\">" +
                        "<div class=\"panel-heading\">" + firstNameData + " " + lastNameData + ", " + ageData + "y.o." + "</div>" +
                        "<div class=\"panel-body\">" + fromData + "  -  " + toData + "<br>" + "Average week: " + averageWeek + "</div>" +
                        "</div>";

                    document.getElementById("processedSubContainer").innerHTML += html;
                }
            },
            error: function(jqXHR, textStatus, errorThrown ) {
                alert(jqXHR.status + ' ' + jqXHR.responseText.toString());
            }
        })
    }
</script>

<br>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-5">
            <div class="text-center">
                <form name="myForm" id="myForm">
                    <div class="form-group">
                        <label>User ID</label>
                        <input type="number" min="1" class="form-control", id="id", placeholder="Enter user id">
                    </div>
                </form>

                    <button class="btn btn-primary" onclick="getByUserId(
                        document.getElementById('id').value
                    )">Result</button>

                    <br>
                    <br>

                <form name="myForm" id="myForm">
                    <div class="form-group">
                        <label>User Information</label>
                        <input type="text" class="form-control" id="firstName" placeholder="Enter first name">
                        <input type="text" class="form-control" id="lastName" placeholder="Enter last name">
                        <input type="number" min="18" max="100" class="form-control" id="age" placeholder="Enter age">
                    </div>
                </form>

                <button class="btn btn-primary" onclick="getByUser(
                        document.getElementById('firstName').value,
                        document.getElementById('lastName').value,
                        document.getElementById('age').value
                    )">Result</button>

                <br>
                <br>

                <div id="processedSubContainer">

                </div>
        </div>
    </div>
</div>
</div>
</body>
</html>