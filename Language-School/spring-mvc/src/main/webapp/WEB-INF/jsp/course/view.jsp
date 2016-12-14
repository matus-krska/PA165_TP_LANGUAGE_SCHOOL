<%--
  Created by IntelliJ IDEA.
  User: Matus Krska, 410073
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course view</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/course/delete/${course.id}">
    <button type="submit" class="btn btn-primary">Delete</button>
</form>


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
            <td><c:out value="${course.name}"/></td>
            <td><c:out value="${product.language}"/></td>
            <td><c:out value="${product.language_level}"/></td>
            <td><c:out value="${product.description}}"/></td>
        </tr>
        </tbody>
    </table>
</body>
</html>
