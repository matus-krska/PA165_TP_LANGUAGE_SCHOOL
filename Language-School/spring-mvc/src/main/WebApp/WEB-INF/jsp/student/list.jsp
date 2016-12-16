<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Simon Hyben, 421112
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students list</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/student/new" class="btn btn-primary">
    New student
</a>

<table class="table">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.surname}</td>
            <td><a href="${pageContext.request.contextPath}/student/view/${student.id}">View</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
