<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Tell the JSP Page that please do not ignore Expression Language -->
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<body>
<form action="/submitFindAuto" method="post">
    <label path="number:">number: (1111-aa[1-7])</label>
    <input type="text" name="number" pattern="[0-9]{4}-[a-z]{2}[1-7]{1}" value=${managedAutomobile.number}></input>
    <input type="submit" name="Submit">
</form>
</br>

<form action="/submitSelectAuto" method="post">
    <label path="selectAuto">select automobile:</label>
    <form:form method="get" modelAttribute="automobiles">
        <select name="automobile">
            <c:forEach items="${automobiles}" var="auto">
            <option value =${auto.id}>${auto}</option>
            </c:forEach>
            <option selected value=${managedAutomobile.id}>${managedAutomobile}</option>
        </select>
    </form:form>
    <input type="submit" name="Submit">
</form>
</br>
</br>

<form action="/submitManageAuto" method="post">
    <form:form method="get" modelAttribute="managedAutomobile">
        <label path="automobileId:">automobile id:</label>
        <input type="text" name="automobileId" readonly  value=${managedAutomobile.id} ></input><p/>

        <label path="make:">automobile make:</label>
        <input type="text" name="make" required value=${managedAutomobile.make}></input><p/>

        <label path="number:">number: (1111-aa[1-7])</label>
        <input type="text" name="number" required pattern="[0-9]{4}-[a-z]{2}[1-7]{1}" value=${managedAutomobile.number}></input><p/>

        <label path="fuelRate:">automobile fuelRate (00.00):</label>
        <input type="text" pattern="[0-9]{1,2}.[0-9]{1,2}" required name="fuelRate"value=${managedAutomobile.fuelRate}></input><p/>
        <input type="radio" name="manage" value="update" checked> update
        <input type="radio" name="manage" value="delete"> delete
    <input type="submit" name="Submit">
</form>

</br>
<a href='<spring:url value="/dataList" />'> <spring:message code="data.list" /></a>

</body>
</html>