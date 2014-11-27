<html>
<body>
<style type='text/css'>
label {
    display: block;
    text-align: center;
    width: 200px;
}
input[type='text'] {
    display: block;
    text-align: left;
    width: 200px;
}
</style>
<form action="/submitAutomobileData" method="post">
    <label path="Automobile make:">Make:</label>
    <input type="text" name="make" required></input><br/>
    <label path="number:">Number: (1111-aa[1-7])</label>
    <input type="text" name="number" pattern="[0-9]{4}-[a-z]{2}[1-7]{1}" required></input><p/>
    <label path="fuelRate:">Fuel Rate (00.00):</label>
    <input type="text" pattern="[0-9]{1,2}.[0-9]{1,2}" name="fuelRate" required /><br/>
    <input type="submit" name="Submit">
</form>

</body>
</html>