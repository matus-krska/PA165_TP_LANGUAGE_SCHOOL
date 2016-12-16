<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Simon Hyben, 421112
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lecturers list</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/lecturer/new" class="btn btn-primary">
    New lecturer
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
    <c:forEach items="${lecturers}" var="lecturer">
        <tr>
            <td>${lecturer.id}</td>
            <td>${lecturer.name}</td>
            <td>${lecturer.surname}</td>
            <td><a href="${pageContext.request.contextPath}/lecturer/view/${lecturer.id}">View</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
