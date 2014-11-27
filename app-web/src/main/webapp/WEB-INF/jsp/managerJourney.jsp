<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Tell the JSP Page that please do not ignore Expression Language -->
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">

<form action="/submitSelectJourney" method="post">
    <label path="selectJourney">select journey:</label>
    <form:form method="get" modelAttribute="journeys">
        <select name="journey">
            <c:forEach items="${journeys}" var="journey">
            <option value =${journey.id}>${journey.id} ${journey.originDestination}
            ${journey.automobile.make} ${journey.automobile.number}</option>
            </c:forEach>
            <option selected value=${managedJourney.id}>${managedJourney.id} ${managedJourney.originDestination}
                                   ${managedJourney.automobile.make} ${managedJourney.automobile.number}</option>
        </select>
    </form:form>
    <input type="submit" name="Submit">
</form>
</br>
</p>

<form action="/submitManageJourney" method="post">
    <form:form method="get" modelAttribute="managedJourney">

        <label path="journeyId:">automobile id:</label>
        <input type="text" name="journey" readonly  value=${managedJourney.id} ></input><p/>

        <label path="automobile">select automobile:</label>
        <select name="automobile">
            <c:forEach items="${automobiles}" var="automobile">
            <option value =${automobile.id}>${automobile}</option>
            </c:forEach>
            <option selected value=${managedJourney.automobile.id}>${managedJourney.automobile}</option>
        </select></p>

        <label path="date">journey date:</label>
        <input type="date" required name="date" value =${managedJourney.date}></input></p>

        <label path="originDestination">origin-destination:</label>
        <input type="text" required name="originDestination" value=${managedJourney.originDestination}></input></p>

        <label path="distance">distance:00000000.00</label>
        <input type="text" required pattern ="[0-9]{1,8}.[0-1]{1,2}" name="distance" value=${managedJourney.distance}></input></p>

        <input type="radio" name="manage" value="update" checked> update
        <input type="radio" name="manage" value="delete"> delete
    <input type="submit" name="Submit">
</form>

</br>
<a href='<spring:url value="/dataList" />'> <spring:message code="data.list" /></a>

</body>
</html>