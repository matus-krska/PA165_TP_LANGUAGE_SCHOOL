<%--
  Created by IntelliJ IDEA.
  User: Matus Krska, 410073
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Course view</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/course/delete/${course.id}" class="btn btn-primary">Delete</a>
<a href="${pageContext.request.contextPath}/course/edit/${course.id}" class="btn btn-primary">Edit</a>

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
        <tr>
            <td>${course.id}</td>
            <td>${course.name}</td>
            <td>${course.language}</td>
            <td>${course.language_level}</td>
            <td>${course.description}</td>
        </tr>
        </tbody>
    </table>
</body>
</html>
