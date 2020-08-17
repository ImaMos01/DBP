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
	<h1>Lista  Alumnos</h1>
	
	<a href = "adminSystem?action=gotoalumn">Volver</a>
	<br>
	
	<c:forEach var ="asignatura" items="${asignaturas}" varStatus = "loop">
		<table border="1" width ="100%">
			<tr>
				<td><c:out value="${asignatura.id}"/></td>
				<td><c:out value="${asignatura.curso}"/></td>
				<td><c:out value="${asignatura.horario}"/></td>
				<td><c:out value="${asignatura.grado}"/></td>
				<td><c:out value="${asignatura.grupo}"/></td>
				<td><c:out value="${asignatura.profesor}"/></td>
				<td><c:out value="${asignatura.capacidad}"/></td>
				<td>editar</td>
				<td><a href="adminSystem?action=nuevoadmin&id=<c:out value="${asignatura.id}"/>">Agregar Alumno</a> </td>				
			</tr>
			<c:forEach var = "alumno" items = "${alumnos[loop.index]}">
			<tr>
				<td><c:out value="${alumno.id}"/></td>
				<td><c:out value="${alumno.apellidos}"/></td>
				<td><c:out value="${alumno.CUI}"/></td>
				<td><c:out value="${alumno.nombres}"/></td>
				<td><c:out value="${alumno.correo}"/></td>
				<td><c:out value="${alumno.asignatura}"/></td>
				<td><a href="adminSystem?action=showedit&id=<c:out value="${alumno.id}" />">Editar</a></td>
				<td><a href="adminSystem?action=eliminar&id=<c:out value="${alumno.id}"/>&idas=<c:out value="${asignatura.id}"/>">Eliminar</a> </td>				
			</tr>
			</c:forEach>			
		</table>
		<br>
	</c:forEach>
</body>
</html>