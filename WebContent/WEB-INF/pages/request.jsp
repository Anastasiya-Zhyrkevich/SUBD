<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
                <li> <a href="${pageContext.request.contextPath}/" title="Home">Back</a></li>
                <c:if test="${currentuser == null}">
                    <li><a href="${pageContext.request.contextPath}/login" title ="LogIn" id="LoginPopup">Login</a></li>
                    <li><a href="${pageContext.request.contextPath}/registration" title="Registration">Register</a></li>
                </c:if>
                <c:if test="${currentuser != null && !isAdmin}">
                    <li><a href="${pageContext.request.contextPath}/" title ="User" >${currentuser}</a></li>
                </c:if>
                <c:if test="${currentuser != null && isAdmin}">
                    <li><a href="${pageContext.request.contextPath}/admin" title ="User" >${currentuser}</a></li>
                </c:if>
                <c:if test="${currentuser != null}">
                    <li><a href="${pageContext.request.contextPath}/logout" title ="User" >Logout</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<div class="container body-content">
    <div>
        <h1>Task 1</h1>
    </div>
    <form:form action="/DataBase/Req" commandName="question" cssClass="form-horizontal">
        <div class="control-group">
            <label class="control-label">Сведения о всех пациентах, бывших на приеме в определенный день</label>
            <div class="controls">
                <form:input cssClass="input-xlarge" 
                	path="date" value = ""/>
                <span class="error"><form:errors path="date" /></span>
            </div>
            
            
            
            <table>
            <tr> <td>Result:</td> </tr>
            <tr>            
            <td>
            
            <table class="table table-striped table-hover">

		        <!-- column headers -->
		        <thead>
		        <th>Patient Id</th>
		        <th>Patient Name</th>
		        <th>Address</th>
		        <th>Birthdate</th>
		        </thead>
		        <!-- column data -->
		        <tbody>
		        <c:forEach var="row" items="${answer1}">
		            
					<tr><td>${row.getPatientId()}</td> 
					<td>${row.getPatientName()}</td>
					<td>${row.getAddress()}</td>
					<td>${row.getBirthdate()}</td>
					
					</tr>
		            
		        </c:forEach>
		        </tbody>
    		</table>
    		</td>
    		</tr>
           </table>
       
            
            
            
            <table>
            <tr> <td>Result:</td>            
            <td><c:out value="${question.result}" /></td> </tr>
            
            <tr> <td>Result Answer:</td>            
            <td><c:out value="${answer}" /></td> </tr>
            </table>
            <br>
            <hr />
            
            <label class="control-label">Сведения о пациентах, ни разу не обслуживавшихся докторами, не являющимися их лечащими врачами </label>
            <table>
            <tr> <td>Result:</td> </tr>
            <tr>            
            <td>
            
            <table class="table table-striped table-hover">

		        <!-- column headers -->
		        <thead>
		        <th>Patient Id</th>
		        <th>Patient Name</th>
		        <th>Address</th>
		        <th>Birthdate</th>
		        </thead>
		        <!-- column data -->
		        <tbody>
		        <c:forEach var="row" items="${answer2}">
		            
					<tr><td>${row.getPatientId()}</td> 
					<td>${row.getPatientName()}</td>
					<td>${row.getAddress()}</td>
					<td>${row.getBirthdate()}</td>
					
					</tr>
		            
		        </c:forEach>
		        </tbody>
    		</table>
    		</td>
    		</tr>
           </table>
       
       <br>
       <hr/>
		
		<label class="control-label">Рассчитать суммарную выручку за каждый месяц текущего года</label>
			<form:input cssClass="input-xlarge" 
                	path="date2" value = ""/>
                <span class="error"><form:errors path="date2" /></span>
            <table>
            <tr> <td>Result:</td> </tr>
            <tr>            
            <td>
            
            <table class="table table-striped table-hover">

		        <!-- column headers -->
		        <thead>
		        <th>Month</th>
		        <th>Sum</th>
		        </thead>
		        <!-- column data -->
		        <tbody>
		        <c:forEach var="row" items="${answer3}">
		            
					<tr><td>${row.getMonth()}</td> 
					<td>${row.getSum()}</td>
					
					</tr>
		            
		        </c:forEach>
		        </tbody>
    		</table>
    		</td>
    		</tr>
           </table>       
            
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
