<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
    function getIntervals(from, to) {
        document.getElementById("processedSubContainer").innerHTML = "";
        $.ajax("http://localhost:30081/intervals", {
            method: "GET",
            data: {
                from: from,
                to: to
            },

            success: function(data, textStatus, jqXHR) {
                for(let i = 0; i < data.length; i++){
                    let firstName = data[i].user.firstName;
                    let lastName = data[i].user.lastName;
                    let age = data[i].user.age;
                    let fromData = new Date(data[i].interval.timestampFrom);
                    let toData = new Date(data[i].interval.timestampTo);
                    let averageWeek = data[i].averageWeekCount;

                    let html = "<div class=\"panel panel-default\">" +
                        "<div class=\"panel-heading\">" + firstName + " " + lastName + ", " + age + "y.o." + "</div>" +
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
        <div class="col-7">
            <div class="text-center">
                <form name="myForm" id="myForm">
                    <div class="form-group">
                        <label>Intervals Information</label>
                        <input type="datetime-local" class="form-control" id="from" placeholder="Enter your from">
                        <input type="datetime-local" class="form-control" id="to" placeholder="Enter your to">
                    </div>
                </form>

                    <button class="btn btn-primary" onclick="getIntervals(
                        document.getElementById('from').value,
                        document.getElementById('to').value
                    )">Result</button>

                    <br>
                <br>

                    <div id="processedSubContainer">

                    </div>

                </div>
        </div>
    </div>
</div>
</div>
</body>
</html>