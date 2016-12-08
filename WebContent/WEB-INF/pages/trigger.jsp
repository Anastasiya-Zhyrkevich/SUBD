<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <link href="<c:url value="/resources/css/united.css"/>" rel="stylesheet" type="text/css">
    <script src="<c:url value="/resources/scripts/jquery-2.1.1.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/scripts/bootstrap.js"/>" type="text/javascript"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Log In</title>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

        </div>
        <div class="navbar-collapse collapse navbar-responsive-collapse">

            <ul class="nav navbar-nav navbar-right">
                <li> <a href="${pageContext.request.contextPath}/" title="Home">Back</a></li>
                
            </ul>
        </div>
    </div>
</div>
<div class="container body-content">
    <h1>Visit</h1>
    <form:form action="/DataBase/trigger" ommandName="triggers" cssClass="form-horizontal">
        <div class="control-group">
            <label class="control-label">Positive_sum:</label>
            <div class="controls">
                <form:radiobutton path="pos_sum" value="disabled"/>Disabled
				<form:radiobutton path="pos_sum" value="enabled"/>Enabled
                <span class="error"><form:errors path="pos_sum" /></span>
            </div>
            
            <label class="control-label">Other doctor trigger:</label>
            <div class="controls">
                <form:radiobutton path="doctor_patient_relation" value="disabled"/>Disabled
				<form:radiobutton path="doctor_patient_relation" value="enabled"/>Enabled
                <span class="error"><form:errors path="doctor_patient_relation" /></span>
            </div>
            
            <label class="control-label">Correct description:</label>
            <div class="controls">
                <form:radiobutton path="correct_decription" value="disabled"/>Disabled
				<form:radiobutton path="correct_decription" value="enabled"/>Enabled
                <span class="error"><form:errors path="correct_decription" /></span>
            </div>
            
        </div>
        <br>
        <div class="form-actions">
            <tr><td><input type="submit" value="Submit" class="btn btn-primary"></td></tr>
        </div>
    </form:form>
    <hr />
    <footer>
    </footer>
</div>
</body>
</html>
