<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>

<!DOCTYPE html>
<html>
<head>
    <title>Lecturer</title>

</head>
<body>

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
        <td><c:out value="${lecturer.name}" /></td>
        <td><c:out value="${lecturer.surname}" /></td>
        <td>
            <sec:authorize access="hasRole('ROLE_LECTURER')">
                <a
                        href="${pageContext.request.contextPath}/lecturer/edit/${lecturer.id}"
                        class="btn btn-primary">Edit</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a
                        href="${pageContext.request.contextPath}/lecturer/delete/${lecturer.id}"
                        class="btn btn-primary">Delete</a>
            </sec:authorize></td>
    </tr>
    </tbody>
</table>


<!--
	Lecturers lectures:
	<table class="table table-striped">
		<tbody>
			<c:forEach items="${lectures}" var="lecture">
				<tr>
					<td><c:out value="${lecture.id}" /></td>
					<td><c:out value="${courseLecturer.dayTime}" /></td>
					<td><c:out value="${courseLecturer.classroomId}" /></td>
					<td><c:out value="${courseLecturer.topic}" /></td>
					<td><my:a href="/lecturer/view/${lecture.id}"
							class="btn btn-primary">view</my:a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	 -->

</body>
</html>
