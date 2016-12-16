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
    <title>Lecture view</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/lecture/delete/${lecture.id}" class="btn btn-primary">Delete</a>
<a href="${pageContext.request.contextPath}/lecture/edit/${lecture.id}" class="btn btn-primary">Edit</a>

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
    <tr>
        <td>${lecture.id}</td>
        <td>${lecture.code}</td>
        <td>${lecture.topic}</td>
        <td>${lecture.description}</td>
        <td><fmt:formatDate value="${lecture.lectureTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td>${lecture.course.name}</td>
        <td>${lecture.taughtBy.name} ${lecture.taughtBy.surname}</td>
    </tr>
    </tbody>
</table>
</body>
</html>
