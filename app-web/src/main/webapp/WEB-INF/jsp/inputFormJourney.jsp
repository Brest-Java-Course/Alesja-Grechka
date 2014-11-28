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
    text-align: left;
    width: 400px;
}
input[type='text'] {
    display: block;
    text-align: left;
    width: 400px;
}
</style>

<form action="/submitJourneyData" method="post">
    <form:form method="get" modelAttribute="journeys">
    <label path="automobile">select automobile:</label>
    <select name="automobile">
        <c:forEach items="${automobiles}" var="automobile">
        <option value =${automobile.id}>${automobile}</option>
        </c:forEach>
    </select>
    </form:form></p>

    <label path="date">journey date yyyy-MM-dd:</label>
    <input type="date" required name="date"
    pattern = "(19|20)\d\d-((0[1-9]|1[012])-(0[1-9]|[12]\d)|(0[13-9]|1[012])-30|(0[13578]|1[02])-31)">
    </p>

    <label path="originDestination">origin-destination:</label>
    <input type="text" required name="originDestination"/></p>

    <label path="distance">distance:00000000.00</label>
    <input type="text" required pattern ="[0-9]{1,8}.[0-1]{1,2}" name="distance"/></p>

    </p><input type="submit" name="Submit">
</form>
</body>
</html>