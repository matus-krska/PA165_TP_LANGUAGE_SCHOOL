<%--
  Created by IntelliJ IDEA.
  User: Matus Krska, 410073
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit lecture</title>
</head>
<body>

<form:form method="post" action="${pageContext.request.contextPath}/lecture/update/${lecture.id}"
           modelAttribute="lecture">

    <div class="form-group" >
        <form:label path="code" cssClass="col-sm-2 control-label">Code</form:label>
        <div class="col-sm-10">
            <form:input path="code" cssClass="form-control"/>
        </div>
    </div>

    <div class="form-group" >
        <form:label path="topic" cssClass="col-sm-2 control-label">Topic</form:label>
        <div class="col-sm-10">
            <form:input path="topic" cssClass="form-control"/>
        </div>
    </div>

    <div class="form-group" >
        <form:label path="description" cssClass="col-sm-2 control-label">Description</form:label>
        <div class="col-sm-10">
            <form:input path="description" cssClass="form-control"/>
        </div>
    </div>

    <div class="form-group" >
        <form:label path="lectureTime" cssClass="col-sm-2 control-label">Lecture time</form:label>
        <div class="col-sm-10">
            <form:input readonly="true" path="lectureTime" cssClass="form-control"/>
        </div>
    </div>

    <div class="form-group" >
        <form:label path="course" cssClass="col-sm-2 control-label">Course</form:label>
        <div class="col-sm-10">
            <form:select path="course" cssClass="form-control">
                <c:forEach items="${courses}" var="c">
                    <form:option value="${c}">${c.name}</form:option>
                </c:forEach>
            </form:select>
        </div>
    </div>

    <div class="form-group" >
        <form:label path="taughtBy" cssClass="col-sm-2 control-label">Lecturer</form:label>
        <div class="col-sm-10">
            <form:select path="taughtBy" cssClass="form-control">
                <c:forEach items="${lecturers}" var="l">
                    <form:option value="${l}">${l.name} ${l.surname}</form:option>
                </c:forEach>
            </form:select>
        </div>
    </div>


    <button class="btn btn-primary" type="submit">Update lecture</button>
</form:form>

</body>
</html>
