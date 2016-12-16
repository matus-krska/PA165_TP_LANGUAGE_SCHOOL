<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
    <title>Language School</title>
</head>

<body>
<section class="container">
    <article class="row">
        <div class="col-md-3">

            <h3>
            <a href="${pageContext.request.contextPath}/course/list">Courses</a>
            </h3>

        </div>
        <div class="col-md-3">
            <h3>
                <a href="${pageContext.request.contextPath}/lecture/list">Lectures</a>
            </h3>
        </div>
        <div class="col-md-3">
            <h3>
                <a href="${pageContext.request.contextPath}/lecturer/list">Lecturers</a>
            </h3>
        </div>
        <div class="col-md-3">
            <h3>
                <a href="${pageContext.request.contextPath}/students/list">Students</a>
            </h3>
        </div>
    </article>
</section>

</body>
</html>