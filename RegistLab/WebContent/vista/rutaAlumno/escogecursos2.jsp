<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mostrar Alumnos</title>
</head>
<body>
	<h1>Lista  Asignaturas</h1>
	<table>
		<tr>
			<td><a href="adminSystem?action=nuevo" >Ir al menú</a> </td>
		</tr>
		
	</table>
	
	<form action = "adminSystem?action=insertadatos"  method="post">
	
	<table border="1" width="100%">
		<tr>
		 <td> ID</td>
		 <td> CURSO</td>
		 <td> HORARIO</td>
		 <td> GRADO</td>
		 <td> GRUPO</td>
		 <td> PROFESOR</td>
		 <td>CAPACIDAD</td>
		 <td colspan=2>ACCIONES</td>
		</tr>
		<c:forEach var="asignatura" items="${lista}">
			<tr>
				<td><c:out value="${asignatura.id}"/></td>
				<td><c:out value="${asignatura.curso}"/></td>
				<td><c:out value="${asignatura.horario}"/></td>
				<td><c:out value="${asignatura.grado}"/></td>
				<td><c:out value="${asignatura.grupo}"/></td>
				<td><c:out value="${asignatura.profesor}"/></td>
				<td><c:out value="${asignatura.capacidad}"/></td>
				<td><input type = "checkbox" name = "cursos" value = "${asignatura.id}"></td>
			</tr>
		</c:forEach>
	</table>
	<input type = "submit" value = "Submit">
</form>
</body>
</html>