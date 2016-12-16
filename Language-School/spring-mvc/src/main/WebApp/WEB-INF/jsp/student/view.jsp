<%--
  Created by IntelliJ IDEA.
  User: Simon Hyben, 421112
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Student view</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/student/delete/${student.id}" class="btn btn-primary">Delete</a>
<a href="${pageContext.request.contextPath}/student/edit/${student.id}" class="btn btn-primary">Edit</a>

    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>surname</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.surname}</td>
        </tr>
        </tbody>
    </table>
</body>
</html>
