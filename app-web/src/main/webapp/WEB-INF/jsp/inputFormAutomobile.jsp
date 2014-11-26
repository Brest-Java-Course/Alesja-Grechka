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
    <input type="text" name="make"></input><br/>
    <label path="number:">Number:</label><input type="text" name="number"/><br/>
    <label path="fuelRate:">Fuel Rate:</label><input type="text" name="fuelRate"/><br/>
    <input type="submit" name="Submit">
</form>

</body>
</html>