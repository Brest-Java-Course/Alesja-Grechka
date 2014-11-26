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

<form action="/submitJourneyData" method="post">
    <label path="date">journey date(yyyy-MM-dd):</label><input type="text" name="date"/><br/>
    <label path="originDestination">origin-destination:</label><input type="text" name="originDestination"/><br/>
    <label path="distance">distance:</label><input type="text" name="distance"/><br/>
    <label path="automobileId">automobile id:</label><input type="text" name="automobileId"/><br/>
    <input type="submit" name="Submit">
</form>
</body>
</html>