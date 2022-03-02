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
    function getIntervals(from, to) {
        $.ajax("http://localhost:30081", {
            method: "GET",
            data: {
                from: from,
                to: to
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
                <div class="form-group" id="intervalsContainer">

                </div>
        </div>
    </div>
</div>
</div>
</body>
</html>