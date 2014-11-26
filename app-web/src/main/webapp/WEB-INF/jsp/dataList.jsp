<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Tell the JSP Page that please do not ignore Expression Language -->
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<body>

<style type="text/css">
    TABLE {

        border-collapse: collapse;
    }
    TD, TH {
        padding: 3px;
        border: 1px solid black;
    }
    TH {
        background: #b0e0e6;
    }
</style>

<form:form method="get" modelAttribute="automobiles">
<h1><spring:message code="automobile.list" /></h1>
<ul>
    <table width=400px>
        <th>
            <td>id</td>
            <td>make</td>
            <td>number</td>
            <td>fuelRate</td>
        </th>
    <c:forEach items="${automobiles}" var="automobile">
        <tr>
            <td/>
            <td>${automobile.id}</td>
            <td>${automobile.make}</td>
            <td>${automobile.number}</td>
            <td>${automobile.fuelRate}</td>
        </tr>
    </c:forEach>
    </table>
</ul>
</form:form>
<a href='<spring:url value="/inputFormAutomobile" />'> <spring:message code="automobile.create" /></a>

<form:form method="get" modelAttribute="journeys">
<h1><spring:message code="journey.list" /></h1>
<ul>
    <table width = 700px>
        <th>
            <td>id</td>
            <td>automobile</td>
            <td>make</td>
            <td>number</td>
            <td>fuelRate</td>
            <td>origin-destination</td>
            <td>distance</td>
            <td>date</td>
        </th>
    <c:forEach items="${journeys}" var="journey">
        <tr>
            <td/>
            <td>${journey.id}</td>
            <td>${journey.automobile.id}</td>
            <td>${journey.automobile.make}</td>
            <td>${journey.automobile.number}</td>
            <td>${journey.automobile.fuelRate}</td>
            <td>${journey.originDestination}</td>
            <td>${journey.distance}</td>
            <td>${journey.date}</td>
        </tr>
    </c:forEach>
    </table>
</ul>
</form:form>
<a href='<spring:url value="/inputFormJourney" />'> <spring:message code="journey.create" /></a>

</body>
</html>