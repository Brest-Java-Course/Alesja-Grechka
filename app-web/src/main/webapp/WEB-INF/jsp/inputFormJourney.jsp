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

<form action="/submitJourneyData" method="post">
    <label path="date">journey date(yyyy-MM-dd):</label><input type="text" name="date"/><br/>
    <label path="originDestination">origin-destination:</label><input type="text" name="originDestination"/><br/>
    <label path="distance">distance:</label><input type="text" name="distance"/><br/>
    <label path="automobile">automobile:</label>
    <form:form method="get" modelAttribute="journeys">
    <ul>
        <select name="automobile">
            <c:forEach items="${automobiles}" var="automobile">
            <option value =${automobile.id}>${automobile}</option>
            </c:forEach>
        </select>
    </ul>
    </form:form>

    <input type="submit" name="Submit">
</form>
</body>
</html>