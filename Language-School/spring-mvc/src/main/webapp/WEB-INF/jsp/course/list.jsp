<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Matus Krska, 410073
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Courses list</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/course/new" class="btn btn-primary">
    New course
</a>

<table class="table">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>language</th>
        <th>language level</th>
        <th>description</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${courses}" var="course">
        <tr>
            <td>${course.id}</td>
            <td>${course.name}</td>
            <td>${course.language}</td>
            <td>${course.language_level}</td>
            <td>${course.description}</td>
            <td><a href="${pageContext.request.contextPath}/course/view/${course.id}">View</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
