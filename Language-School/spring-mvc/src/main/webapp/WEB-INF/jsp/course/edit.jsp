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
    <title>Edit course</title>
</head>
<body>

<form:form method="post" action="${pageContext.request.contextPath}/course/update/${course.id}"
           modelAttribute="course">

    <div class="form-group" >
        <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
        <div class="col-sm-10">
            <form:input path="name" cssClass="form-control"/>
        </div>
    </div>

    <div class="form-group" >
        <form:label path="language" cssClass="col-sm-2 control-label">Language</form:label>
        <div class="col-sm-10">
            <form:input path="language" cssClass="form-control"/>
        </div>
    </div>

    <div class="form-group" >
        <form:label path="language_level" cssClass="col-sm-2 control-label">Language level</form:label>
        <div class="col-sm-10">
            <form:input path="language_level" cssClass="form-control"/>
        </div>
    </div>

    <div class="form-group" >
        <form:label path="description" cssClass="col-sm-2 control-label">Description</form:label>
        <div class="col-sm-10">
            <form:input path="description" cssClass="form-control"/>
        </div>
    </div>


    <button class="btn btn-primary" type="submit">Update course</button>
</form:form>

</body>
</html>
