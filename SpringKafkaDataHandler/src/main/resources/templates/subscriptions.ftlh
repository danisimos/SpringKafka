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
    function addSubscription(firstName, lastName, age, subscriptionType, weekCount) {
        let body = {
            "user": {
                "firstName": firstName,
                "lastName": lastName,
                "age": age
            },
            "subscriptionType": subscriptionType,
            "weekNumber": weekCount
        };
        $.ajax("http://localhost:30080/subscriptions", {
            method: "POST",
            data: JSON.stringify(body),
            headers: {
                'Content-Type': 'application/json'
            },
            success: function(data, textStatus, jqXHR) {
                document.getElementById('messageContainer').innerText = data.toString();
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
                        <input type="text" class="form-control" id="firstName" placeholder="Enter your first name">
                        <input type="text" class="form-control" id="lastName" placeholder="Enter your last name">
                        <input type="number" min="18" max="100" class="form-control" id="age" placeholder="Enter your age">
                    </div>
                    <div class="form-group">
                        <label>Subscription Information</label>
                        <select id="subscriptionType" class="form-control">
                            <option selected>LITE</option>
                            <option>CLASSIC</option>
                            <option>PREMIUM</option>
                        </select>
                        <input type="number" min="1" max="50" class="form-control" id="weekCount" placeholder="Enter week count">
                    </div>
                </form>

                    <button class="btn btn-primary" onclick="addSubscription(
                        document.getElementById('firstName').value,
                        document.getElementById('lastName').value,
                        document.getElementById('age').value,
                        document.getElementById('subscriptionType').value,
                        document.getElementById('weekCount').value
                    )">Submit</button>

                    <br>
                <div class="form-group">
                    <label id="messageContainer"></label>
                </div>
        </div>
    </div>
</div>
</div>
</body>
</html>