<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cambiar Password</title>

</head>
<body>

<c:out value = "${ad}"/>

<a href="adminSystem?action=index">Volver</a><br>
<form action="adminSystem?action=vercui"  method="post">
  Inserte CUI <input type="text" name="fname"><br>
  <input type="submit" value="Submit">
</form>
	
	
</body>
</html>