<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Subscriptions</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>


<br>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-7">
            <div class="text-center">
                <form name="myForm" id="myForm">
                    <div class="form-group">
                        <h2>All Subscriptions Information</h2>
                    </div>
                </form>

                <br>
                <br>

                <div id="processedSubContainer">

                </div>

            </div>
        </div>
    </div>
</div>
</div>
<script>
    function getAllSubscriptions() {
        document.getElementById("processedSubContainer").innerHTML = "";
        $.ajax("http://localhost:30081/subscriptions/getAll", {
            method: "GET",
            success: function(data, textStatus, jqXHR) {
                for(let i = 0; i < data.length; i++){
                    let firstName = data[i].user.firstName;
                    let lastName = data[i].user.lastName;
                    let age = data[i].user.age;
                    let timestamp = new Date(data[i].timestamp);
                    let subType = data[i].subscriptionType;
                    let weekCount = data[i].weekNumber;

                    let html = "<div class=\"panel panel-default\">" +
                        "<div class=\"panel-heading\">" + firstName + " " + lastName + ", " + age + "y.o." + "</div>" +
                        "<div class=\"panel-body\">" + timestamp + ", "  + "<br>" + "Subscription Type: " + subType + ", Week count: " + weekCount + "</div>" +
                        "</div>";

                    document.getElementById("processedSubContainer").innerHTML += html;
                }

            },
            error: function(jqXHR, textStatus, errorThrown ) {
                alert(jqXHR.status + ' ' + jqXHR.responseText.toString());
            }
        })
    }
    window.onload = getAllSubscriptions();
</script>
</body>
</html>