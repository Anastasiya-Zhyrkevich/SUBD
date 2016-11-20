<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <link href="<c:url value="/resources/css/united.css"/>" rel="stylesheet" type="text/css">
    <script src="<c:url value="/resources/scripts/jquery-2.1.1.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/scripts/bootstrap.js"/>" type="text/javascript"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome</title>
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
                <li> <a href="${pageContext.request.contextPath}/" title="index">Visits</a></li>
                <li> <a href="${pageContext.request.contextPath}/Patient" title="patients">Patients</a></li>
                <li> <a href="${pageContext.request.contextPath}/Req" title="Req">Requests</a></li>
                <li> <a href="${pageContext.request.contextPath}/trigger" title="trigger">Triggers</a></li>
                
            </ul>
        </div>
    </div>
</div>
<div class="container body-content">
    <div class="jumbotron">
        <h1>Patients</h1>
    </div>
    <!--  <div>
    
    <a class="btn btn-default" href="${pageContext.request.contextPath}/editResource}" role="button">
    	Add new visit</a>        	
    </div>
    -->
    <td>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/editPatient?id=0" role="button">Add</a>
    </td>
    
    <c:if test="${resources != null}">
    	<table class="table table-striped table-hover">

        <!-- column headers -->
        <thead>
        <th>Patient</th>
        <th>Address</th>
        <th>Birthdate</th>
        <th>Tooth Formula</th>
        <th>Payment Type</th>
        <th>Doctor Name</th>        
        
        </thead>
        <!-- column data -->
        <tbody>
        <c:forEach var="row" items="${resources}">
            <tr>
                <td>${row.getPatientName()}</td>
                <td>${row.getAddress()}</td>
                <td>${row.getBirthdate()}</td>
                <td>${row.getBirthdate()}</td>
                <td>
                	<a class="btn btn-default" href="${pageContext.request.contextPath}/editPatient?id=${row.getPatientId()}" role="button">Edit</a>
            	</td>
            	<td>
                	<a class="btn btn-default" href="${pageContext.request.contextPath}/deletePatient?id=${row.getPatientId()}" role="button">Delete</a>
            	</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:if>
    <c:if test="${resources == null}">
    	<div> No patients </div>
    </c:if>

    <hr />
    <footer>
    </footer>
</div>


</body>
</html>
