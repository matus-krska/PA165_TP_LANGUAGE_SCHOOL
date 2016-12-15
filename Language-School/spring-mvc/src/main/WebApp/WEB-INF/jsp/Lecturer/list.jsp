<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
    <title>Language School Lecturers</title>
</head>
<body>

<table class="table table-striped">
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
            <td><c:out value="${lecturer.id}" /></td>
            <td><c:out value="${lecturer.name}" /></td>
            <td><c:out value="${lecturer.surname}" /></td>
            <td><my:a href="/lecturer/view/${lecturer.id}"
                      class="btn btn-primary">view</my:a></td>
        </tr>
    </c:forEach>
    </tbody>

</table>


</body>
</html>