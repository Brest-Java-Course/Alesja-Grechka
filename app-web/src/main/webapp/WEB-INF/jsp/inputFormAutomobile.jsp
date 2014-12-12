<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Tell the JSP Page that please do not ignore Expression Language -->
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
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
<form action= ${relativeAddress}/submitAutomobileData method="post">
    <label path="Automobile make:">Make:</label>
    <input type="text" name="make" required value ="audi"></input><br/>
    <label path="number:">Number: (1111-aa[1-7])</label>
    <input type="text" name="number" pattern="[0-9]{4}-[a-z]{2}[1-7]{1}" required  value ="0013-ih1"></input><p/>
    <label path="fuelRate:">Fuel Rate (00.00):</label>
    <input type="text" pattern="[0-9]{1,2}.[0-9]{1,2}" name="fuelRate" required value = "6.0"/><br/>
    <input type="submit" name="Submit">
</form>

</body>
</html>