<%--
  Created by IntelliJ IDEA.
  User: Simon Hyben, 421112
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>New student</title>
</head>
<body>

<form:form method="post" action="${pageContext.request.contextPath}/student/create"
           modelAttribute="student">

    <div class="form-group" >
        <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
        <div class="col-sm-10">
            <form:input path="name" cssClass="form-control"/>
        </div>
    </div>

    <div class="form-group" >
        <form:label path="surname" cssClass="col-sm-2 control-label">Surname</form:label>
        <div class="col-sm-10">
            <form:input path="surname" cssClass="form-control"/>
        </div>
    </div>

    <button class="btn btn-primary" type="submit">Create student</button>
</form:form>

</body>
</html>
