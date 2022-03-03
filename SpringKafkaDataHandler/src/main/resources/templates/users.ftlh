<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
    function getByUser(id, firstName, lastName, age) {
        $.ajax("http://localhost:8081/users", {
            method: "GET",
            data: {
                id: id,
                firstName: firstName,
                lastName: lastName,
                age: parseInt(age) || 0
            },
            success: function(data, textStatus, jqXHR) {
                document.getElementById('userContainer').innerText = data.toString();
                document.getElementById('subContainer').innerText = data.toString();
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
                        <label>User Information</label>
                        <input type="number" min="1" class="form-control", id="id", placeholder="Enter user id">
                        <input type="text" class="form-control" id="firstName" placeholder="Enter first name">
                        <input type="text" class="form-control" id="lastName" placeholder="Enter last name">
                        <input type="number" min="18" max="100" class="form-control" id="age" placeholder="Enter age">
                    </div>
                </form>

                    <button class="btn btn-primary" onclick="getByUser(
                        document.getElementById('id').value,
                        document.getElementById('firstName').value,
                        document.getElementById('lastName').value,
                        document.getElementById('age').value
                    )">Result</button>

                    <br>

                <div class="panel panel-default">
                    <div class="panel-heading" id="userContainer"></div>
                    <div class="panel-body" id="subContainer"></div>
                </div>
        </div>
    </div>
</div>
</div>
</body>
</html>