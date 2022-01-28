<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Clinic Management Application</title>
 <link rel="stylesheet"
href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-md navbar-light">
<div>
<a class="navbar-brand"> Clinic Management Application </a>
</div>
<ul class="navbar-nav">
<li><a href="<%=request.getContextPath()%>/ClinicServlet/dashboard"
class="nav-link">Back to Dashboard</a></li>
</ul>
</nav>
<div class="container col-md-6">
<div class="card">
<div class="card-body">
<c:if test="${clinic != null}">
<form action="update" method="post">
</c:if>
<c:if test="${clinic == null}">
<form action="insert" method="post">
</c:if>
<caption>
<h2>
<c:if test="${clinic != null}">
Edit Clinic
</c:if>
<c:if test="${clinic == null}">
Add New Clinic
</c:if>
</h2>
</caption>
<c:if test="${clinic != null}">
<input type="hidden" name="id" value="<c:out
value='${clinic.id}' />" />
</c:if>
<fieldset class="form-group">
<label>Clinic Name</label> <input type="text" value="<c:out
value='${clinic.clinic_name}' />" class="form-control" name="clinic_name" required="required">
</fieldset>
<fieldset class="form-group">
<label>Address</label> <input type="text" value="<c:out
value='${clinic.address}' />" class="form-control" name="address">
</fieldset>
<fieldset class="form-group">
<label>Location Name</label> <input type="text" value="<c:out
value='${clinic.location_name}' />" class="form-control" name="location_name">
</fieldset>
<fieldset class="form-group">
<label> Image</label> <input type="text" value="<c:out
value='${clinic.image}' />" class="form-control" name="image">
</fieldset>
<fieldset class="form-group">
<label> Description </label> <input type="text" value="<c:out
value='${clinic.description}' />" class="form-control" name="description">
</fieldset>
<fieldset class="form-group">
<label> Opening Hours</label> <input type="text" value="<c:out
value='${clinic.opening_hours}' />" class="form-control" name="opening_hours">
</fieldset>
<fieldset class="form-group">
<label> Opening_days</label> <input type="text" value="<c:out
value='${clinic.opening_days}' />" class="form-control" name="opening_days">
</fieldset>
<fieldset class="form-group">
<label> Contact Number</label> <input type="text" value="<c:out
value='${clinic.contact_number}' />" class="form-control" name="contact_number">
</fieldset>
<fieldset class="form-group">


<button type="submit" class="btn btn-success">Save</button>
</form>
</div>
</div>
</div>

</body>
</html>