<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Matus Krska, 410073
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lectures list</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/lecture/new" class="btn btn-primary">
    New course
</a>

<table class="table">
    <thead>
    <tr>
        <th>id</th>
        <th>code</th>
        <th>topic</th>
        <th>description</th>
        <th>lectureTime</th>
        <th>course</th>
        <th>taughtBy</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${lectures}" var="lecture">
        <tr>
            <td>${lecture.id}</td>
            <td>${lecture.code}</td>
            <td>${lecture.topic}</td>
            <td>${lecture.description}</td>
            <td><fmt:formatDate value="${lecture.lectureTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>${lecture.course.name}</td>
            <td>${lecture.taughtBy.name} ${lecture.taughtBy.surname}</td>
            <td><a href="${pageContext.request.contextPath}/lecture/view/${lecture.id}">View</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
