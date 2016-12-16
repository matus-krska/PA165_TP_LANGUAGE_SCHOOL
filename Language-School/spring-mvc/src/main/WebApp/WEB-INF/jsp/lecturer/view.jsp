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
    <title>Lecturer view</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/lecturer/delete/${lecturer.id}" class="btn btn-primary">Delete</a>
<a href="${pageContext.request.contextPath}/lecturer/edit/${lecturer.id}" class="btn btn-primary">Edit</a>

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
            <td>${lecturer.id}</td>
            <td>${lecturer.name}</td>
            <td>${lecturer.surname}</td>
        </tr>
        </tbody>
    </table>
</body>
</html>
