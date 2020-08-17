<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Asignatura</title>
</head>
<body>
	<h1>Escoge las asignaturas en las que deseas matricularte</h1>
<form action="adminSystem?action=escogegrado" method="post">

  <input type="checkbox" name="grado" value="1">
  Primero<br>
  
  <input type="checkbox"  name="grado" value="2">
  Segundo<br>
  
  <input type="checkbox" name="grado" value="3">
  Tercero<br><br>
  
  <input type="checkbox"  name="grado" value="4">
  Cuarto<br>
  
  <input type="checkbox"  name="grado" value="5">
  Quinto<br>
  
  <input type="submit" value="Submit">
</form>
</body>
</html>