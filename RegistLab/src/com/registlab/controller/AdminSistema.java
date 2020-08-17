package com.registlab.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.registlab.dao.AlumnoDAO;
import com.registlab.dao.AsignaturaDAO;
import com.registlab.model.Alumno;
import com.registlab.model.Asignatura;
import com.registlab.model.Asignaturagenerica;
import com.registlab.model.Sistema;

import com.registlab.dao.CursoDAO;
import com.registlab.dao.GradoDAO;
import com.registlab.dao.GrupoDAO;
import com.registlab.dao.HorarioDAO;
import com.registlab.dao.ProfesorDAO;

import com.registlab.model.Curso;
import com.registlab.model.Grado;
import com.registlab.model.Grupo;
import com.registlab.model.Horario;
import com.registlab.model.Profesor;


@WebServlet("/adminSystem")
public class AdminSistema extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AlumnoDAO alumnoDAO;
	AsignaturaDAO asignaturaDAO;
	String password;
	Sistema sistema;
	
	CursoDAO cursoDAO;
	GradoDAO gradoDAO;
	GrupoDAO grupoDAO;
	HorarioDAO horarioDAO;
	ProfesorDAO profesorDAO;
	
	//AUXILIARES
	
	ArrayList<Integer> idregist;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {

			alumnoDAO = new AlumnoDAO(jdbcURL, jdbcUsername, jdbcPassword);
			cursoDAO = new CursoDAO(jdbcURL, jdbcUsername, jdbcPassword);
			gradoDAO = new GradoDAO(jdbcURL, jdbcUsername, jdbcPassword);
			grupoDAO = new GrupoDAO(jdbcURL, jdbcUsername, jdbcPassword);
			horarioDAO = new HorarioDAO(jdbcURL, jdbcUsername, jdbcPassword);
			profesorDAO = new ProfesorDAO(jdbcURL, jdbcUsername, jdbcPassword);
			asignaturaDAO = new AsignaturaDAO(jdbcURL, jdbcUsername, jdbcPassword);
			password = new String("RegistLab");
			sistema=new Sistema();
			
			idregist = new ArrayList<Integer>();
		} catch (Exception e) {
			System.out.println("error");
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminSistema() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hola Servlet..");
		String action = request.getParameter("action");
		System.out.println(action);
		try {
			switch (action) {
			case "index":
				index(request, response);
				break;
			case "nuevo":
				nuevo(request, response);
				break;
			case "register":
				System.out.println("entro");
				registrar(request, response);
				break;
			case "mostrar":
				mostrar(request, response);
				break;
			case "showedit":
				showEditar(request, response);
				break;	
			case "editar":
				editar(request, response);
				break;
			case "eliminar":
				eliminar(request, response);
				break;
			case "showpassword":
				showpassword(request,response);
				break;
			case "validpassword":
				validpassword(request,response);
				break;
			case "showchangepassword":
				showchangepassword(request,response);
				break;
			case "changepassword":
				changepassword(request,response);
				break;
			case "showchangesystem":
				showchangesystem(request,response);
				break;
			case "set_date":
				set_date(request,response);
				break;
			case "gotoadmin":
				gotoadmin(request,response);
				break;
			case "gotoasign":
				gotoasign(request,response);
				break;
			case "gotoalumn":
				gotoalumn(request,response);
				break;
			case "showas":
				asignaturas(request,response);
				break;
			case "createasign":
				gotocreateasign(request,response);
				break;
			case "addasign":
				addasign(request,response);
				break;
			case "showeditarasign":
				showEditarassign(request,response);
				break;
			case "editarasign":
				editarassign(request,response);
				break;
			case "borrarasign":
				borrarasign(request,response);
				break;
			case "showre":
				showregistro(request,response);
				break;
			case "escogegrado":
				escogegrado(request,response);
				break;
			case "insertadatos":
				escogercurso(request, response);
				break;
			case "registrarse":
				registrarse(request,response);
				break;
			case "vercui":
				buscarcui(request,response);
				break;
			case "nuevoadmin":
				nuevoadmin(request, response);
				break;
			case "menuadmin":
				menuadmin(request,response);
				break;
			default:
				break;
			}			
		} catch (SQLException e) {
			e.getStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Hola Servlet..");
		doGet(request, response);
	}
	
	private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		//mostrar(request, response);
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Alumno alumno = new Alumno(0, Integer.parseInt(request.getParameter("cui")) , request.getParameter("nombre"), request.getParameter("apellidos"), request.getParameter("correo"), Integer.parseInt(request.getParameter("Idasignatura")));
		alumnoDAO.insertar(alumno);
		
		Asignatura asignatura= asignaturaDAO.obtenerPorId(Integer.parseInt(request.getParameter("Idasignatura")));
		int grado = asignatura.getIdGrado();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/alumnos/showregister.jsp");
		List<Asignaturagenerica> listaAsignaturas= asignaturaDAO.Listarporgrado(grado);
		List<List<Alumno>> listaAlumnos=new ArrayList<List<Alumno>>();
		int size = listaAsignaturas.size();
		for (int i = 0; i < size;i++)
			listaAlumnos.add(alumnoDAO.Listarporidasignatura(listaAsignaturas.get(i).getId()));
		request.setAttribute("alumnos", listaAlumnos);
		request.setAttribute("asignaturas", listaAsignaturas);
		
		
		dispatcher.forward(request, response);
	}
	
	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		try {
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	String dateString1 = format.format(sistema.getDate1());
        	String dateString2 = format.format(sistema.getDate2());
        	Date   actual     = new Date();
        	Date   date       = format.parse (dateString1);
        	Date   date2      = format.parse (dateString2);
        	if(date2.after(actual) && date.before(actual)){
    			RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/rutaAlumno/escogecursos.jsp");
    			dispatcher.forward(request, response);
                System.out.println(
                    "Acceso abierto");
            } 
        	else
        	{
        		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/nosystem.jsp");
        		//RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/rutaAlumno/escogecursos.jsp");
    			dispatcher.forward(request, response);
                System.out.println(
                    "lo siento, sistema cerrado");
        	}
    		
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/rutaAlumno/insertaCUI.jsp");
		dispatcher.forward(request, response);
	}	
	
	private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Alumno alumno = alumnoDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("alumno", alumno);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/alumnos/editar.jsp");
		dispatcher.forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Alumno alumno = new Alumno(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("cui")) , request.getParameter("nombre"), request.getParameter("apellidos"), request.getParameter("correo"), Integer.parseInt(request.getParameter("Idasignatura")));
		alumnoDAO.actualizar(alumno);
		
		Asignatura asignatura= asignaturaDAO.obtenerPorId(Integer.parseInt(request.getParameter("Idasignatura")));
		int grado = asignatura.getIdGrado();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/alumnos/showregister.jsp");
		List<Asignaturagenerica> listaAsignaturas= asignaturaDAO.Listarporgrado(grado);
		List<List<Alumno>> listaAlumnos=new ArrayList<List<Alumno>>();
		int size = listaAsignaturas.size();
		for (int i = 0; i < size;i++)
			listaAlumnos.add(alumnoDAO.Listarporidasignatura(listaAsignaturas.get(i).getId()));
		request.setAttribute("alumnos", listaAlumnos);
		request.setAttribute("asignaturas", listaAsignaturas);
		
		
		dispatcher.forward(request, response);
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Alumno alumno = alumnoDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		alumnoDAO.eliminar(alumno);
		int max = alumnoDAO.get_idmax();
		for(int i = Integer.parseInt(request.getParameter("id")); i < max;i++)
		{
			alumnoDAO.actualizar_traseliminar(i);
		}
		alumnoDAO.setear_tabla();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/alumnos/showregister.jsp");
		int grado = asignaturaDAO.obtenerPorId(Integer.parseInt(request.getParameter("idas"))).getIdGrado();
		List<Asignaturagenerica> listaAsignaturas= asignaturaDAO.Listarporgrado(grado);
		List<List<Alumno>> listaAlumnos=new ArrayList<List<Alumno>>();
		int size = listaAsignaturas.size();
		for (int i = 0; i < size;i++)
			listaAlumnos.add(alumnoDAO.Listarporidasignatura(listaAsignaturas.get(i).getId()));
		request.setAttribute("alumnos", listaAlumnos);
		request.setAttribute("asignaturas", listaAsignaturas);
		dispatcher.forward(request, response);
		
	}
	private void showpassword(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/pagepassword.jsp");
		dispatcher.forward(request, response);
	}
	private void menuadmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/adminmenu.jsp");
		dispatcher.forward(request, response);
	}
	private void validpassword(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		if(request.getParameter("fname").equals(password))
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/adminmenu.jsp");
			dispatcher.forward(request, response);
		}
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/pagepassword.jsp");
			String ad = "contrasena incorrecta, por favor inserte otra";
			request.setAttribute("ad", ad);
			dispatcher.forward(request,response);
		}
	}
	
	private void changepassword(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		if(request.getParameter("fname").equals(request.getParameter("fname2")))
		{
			password = request.getParameter("fname");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/adminmenu.jsp");
			dispatcher.forward(request, response);
		}
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/changepassword.jsp");
			String ad = "contrasenas distintas, por favor volver a ingresar";
			request.setAttribute("ad", ad);
			dispatcher.forward(request,response);
		}
	}
	private void showchangepassword(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/changepassword.jsp");
		dispatcher.forward(request, response);
	}
	private void showchangesystem(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/sistemacierreapertura.jsp");
		request.setAttribute("dateOpen", sistema.getDate1());
		request.setAttribute("dateClose", sistema.getDate2());
		dispatcher.forward(request, response);
	}
	private void set_date(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		try {
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        	Date dateString = format.parse(request.getParameter("year")+"-"+request.getParameter("mes")+"-"+request.getParameter("dia"));
        	Date date2String = format.parse(request.getParameter("year2")+"-"+request.getParameter("mes2")+"-"+request.getParameter("dia2"));
        	sistema.setDate1(dateString);
        	sistema.setDate2(date2String);
        	
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/sistemacierreapertura.jsp");
        	request.setAttribute("dateOpen", sistema.getDate1());
    		request.setAttribute("dateClose", sistema.getDate2());
    		dispatcher.forward(request, response);
    		
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}
	private void gotoadmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/adminmenu.jsp");
		dispatcher.forward(request, response);
	}
	private void nuevoadmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/alumnos/register.jsp");
		request.setAttribute("id", request.getParameter("id"));
		dispatcher.forward(request, response);
	}
	private void gotoasign(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/asignaturas/selecciongrado.jsp");
		dispatcher.forward(request, response);
	}
	private void gotoalumn(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/alumnos/seleccionalumn.jsp");
		dispatcher.forward(request, response);
	}
	private void asignaturas(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/asignaturas/showasign.jsp");
		List<Asignaturagenerica> listaAsignaturas= asignaturaDAO.Listarporgrado(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("lista", listaAsignaturas);
		dispatcher.forward(request, response);
	}
	private void gotocreateasign(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/asignaturas/crearasignatura.jsp");
		dispatcher.forward(request, response);
	}
	private void addasign(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/asignaturas/selecciongrado.jsp");
		
		Curso curso = new Curso(0, request.getParameter("curso"));
		
		Grupo grupo = new Grupo(0, request.getParameter("grupo"));
		Horario horario = new Horario(0, request.getParameter("hor1"), request.getParameter("hor2"), request.getParameter("hor3"));
		Profesor profesor= new Profesor(0, request.getParameter("nombre"), request.getParameter("apellido"));
		
		int idgrup=0;
		
		if (grupo.getGrupo_asign().equals("A"))
			idgrup = 1;
		if (grupo.getGrupo_asign().equals("B"))
			idgrup = 2;
		if (grupo.getGrupo_asign().equals("C"))
			idgrup = 3;
		if (grupo.getGrupo_asign().equals("D"))
			idgrup = 4;
		
		cursoDAO.insertar(curso);
		
		//grupoDAO.insertar(grupo);
		horarioDAO.insertar(horario);
		profesorDAO.insertar(profesor);
		
		Asignatura asignatura = new Asignatura(0,cursoDAO.search_idwithname(curso), horarioDAO.search_idwithname(horario),gradoDAO.obtenerPorId(Integer.parseInt(request.getParameter("grado"))).getId(),idgrup,profesorDAO.search_idwithname(profesor), Integer.parseInt(request.getParameter("cantidad")));
		
		asignaturaDAO.insertar(asignatura);
		
		dispatcher.forward(request, response);
	}
	private void editarassign(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/asignaturas/selecciongrado.jsp");
		
		Curso curso = new Curso(0, request.getParameter("curso"));
		//Grado grado = new Grado(0, request.getParameter("grado"));
		Grupo grupo = new Grupo(0, request.getParameter("grupo"));
		Horario horario = new Horario(0, request.getParameter("hor1"), request.getParameter("hor2"), request.getParameter("hor3"));
		Profesor profesor= new Profesor(0, request.getParameter("nombre"), request.getParameter("apellido"));
		
		int idgrup=0;
		
		if (grupo.getGrupo_asign().equals("A"))
			idgrup = 1;
		if (grupo.getGrupo_asign().equals("B"))
			idgrup = 2;
		if (grupo.getGrupo_asign().equals("C"))
			idgrup = 3;
		if (grupo.getGrupo_asign().equals("D"))
			idgrup = 4;
		
		cursoDAO.insertar(curso);
		//gradoDAO.insertar(grado);
		
		horarioDAO.insertar(horario);
		profesorDAO.insertar(profesor);
		
		Asignatura asignatura = new Asignatura(Integer.parseInt(request.getParameter("id")),cursoDAO.search_idwithname(curso), horarioDAO.search_idwithname(horario),gradoDAO.obtenerPorId(Integer.parseInt(request.getParameter("grado"))).getId(),idgrup,profesorDAO.search_idwithname(profesor), Integer.parseInt(request.getParameter("cantidad")));
		
		asignaturaDAO.actualizar(asignatura);
		
		dispatcher.forward(request, response);
	}
	private void showEditarassign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

		Asignatura asignatura = asignaturaDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("asignatura", asignatura);
		
		Curso curso = new Curso(cursoDAO.obtenerPorId(asignatura.getIdCurso()).getId(), cursoDAO.obtenerPorId(asignatura.getIdCurso()).getCurso_asign());
		Grado grado = new Grado(gradoDAO.obtenerPorId(asignatura.getIdGrado()).getId(), gradoDAO.obtenerPorId(asignatura.getIdGrado()).getGrado_asign());
		Grupo grupo = new Grupo(grupoDAO.obtenerPorId(asignatura.getIdGrupo()).getId(), grupoDAO.obtenerPorId(asignatura.getIdGrado()).getGrupo_asign());
		Horario horario = new Horario(horarioDAO.obtenerPorId(asignatura.getIdHorario()).getId(),horarioDAO.obtenerPorId(asignatura.getIdHorario()).getHora_inicio(),horarioDAO.obtenerPorId(asignatura.getIdHorario()).getHora_fin(),horarioDAO.obtenerPorId(asignatura.getIdHorario()).getFecha());
		Profesor profesor= new Profesor(profesorDAO.obtenerPorId(asignatura.getIdProfesor()).getId(), profesorDAO.obtenerPorId(asignatura.getIdProfesor()).getNombres(), profesorDAO.obtenerPorId(asignatura.getIdProfesor()).getApellidos());
		
		request.setAttribute("curso", curso);
		request.setAttribute("grado", grado);
		request.setAttribute("grupo", grupo);
		request.setAttribute("horario", horario);
		request.setAttribute("profesor", profesor);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/asignaturas/editarasign.jsp");
		dispatcher.forward(request, response);
	}
	
	private void borrarasign(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/asignaturas/showasign.jsp");
		Asignatura asignatura= asignaturaDAO.obtenerPorId(Integer.parseInt(request.getParameter("id")));
		
		Curso curso = cursoDAO.obtenerPorId(asignatura.getIdCurso());
		Grado grado = gradoDAO.obtenerPorId(asignatura.getIdGrado());
		//Grupo grupo = grupoDAO.obtenerPorId(asignatura.getIdGrupo());
		Horario horario = horarioDAO.obtenerPorId(asignatura.getIdHorario());
		Profesor profesor = profesorDAO.obtenerPorId(asignatura.getIdProfesor());
		
		
		Alumno alumno = alumnoDAO.obtenerPorId(1);
		int max = alumnoDAO.get_idmax();
		int k = 1;
		while(k <= alumnoDAO.get_idmax())
		{
			if (k <= alumnoDAO.get_idmax()) {
				alumno = alumnoDAO.obtenerPorId(k);
				if(alumnoDAO.obtenerPorId(k).getAsignatura()==Integer.parseInt(request.getParameter("id"))){
					
					alumnoDAO.eliminar(alumno);
					int max2 = alumnoDAO.get_idmax();
					for(int j = k; j < max2 ;j++)
					{
						alumnoDAO.actualizar_traseliminar(j);
					}
					k--;
					alumnoDAO.setear_tabla();
				}
			}
			k++;
		}
		
		int grade = asignatura.getIdGrado();
		asignaturaDAO.eliminar(asignatura);
		max = asignaturaDAO.get_idmax();
		/*
		for(int i = asignatura.getId(); i < max;i++)
		{
			asignaturaDAO.actualizar_traseliminar(i);
		}
		asignaturaDAO.setear_tabla();¨*/
		/*
		cursoDAO.eliminar(curso);
	    max = cursoDAO.get_idmax();
		for(int i = curso.getId(); i < max;i++)
		{
			cursoDAO.actualizar_traseliminar(i);
		}
		cursoDAO.setear_tabla();
		/*
		gradoDAO.eliminar(grado);
		max = gradoDAO.get_idmax();
		for(int i = grado.getId(); i < max;i++)
		{
			gradoDAO.actualizar_traseliminar(i);
		}
		gradoDAO.setear_tabla();
		
		grupoDAO.eliminar(grupo);
		max = grupoDAO.get_idmax();
		for(int i = grado.getId(); i < max;i++)
		{
			grupoDAO.actualizar_traseliminar(i);
		}
		grupoDAO.setear_tabla();
		
		horarioDAO.eliminar(horario);
		max = horarioDAO.get_idmax();
		for(int i = grado.getId(); i < max;i++)
		{
			horarioDAO.actualizar_traseliminar(i);
		}
		horarioDAO.setear_tabla();
		
		profesorDAO.eliminar(profesor);
		max = profesorDAO.get_idmax();
		for(int i = grado.getId(); i < max;i++)
		{
			profesorDAO.actualizar_traseliminar(i);
		}
		profesorDAO.setear_tabla();
		*/
		
		
		
		
		List<Asignaturagenerica> listaAsignaturas= asignaturaDAO. Listarporgrado(grade);
		request.setAttribute("lista", listaAsignaturas);
		
		dispatcher.forward(request, response);
	}
	
	
	///ALUMNOS
	
	private void showregistro(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/admin/alumnos/showregister.jsp");
		List<Asignaturagenerica> listaAsignaturas= asignaturaDAO.Listarporgrado(Integer.parseInt(request.getParameter("id")));
		List<List<Alumno>> listaAlumnos=new ArrayList<List<Alumno>>();
		int size = listaAsignaturas.size();
		for (int i = 0; i < size;i++)
			listaAlumnos.add(alumnoDAO.Listarporidasignatura(listaAsignaturas.get(i).getId()));
		request.setAttribute("alumnos", listaAlumnos);
		request.setAttribute("asignaturas", listaAsignaturas);
		
		
		dispatcher.forward(request, response);
	}
	
	// RUTA DE ALUMNOS
	
	private void escogegrado(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		String[] grade = request.getParameterValues("grado");
		for (int i = 0; i < grade.length; i++) {
		    System.out.println(grade[i]); 
		}
		String data = new String("(");
		for (int i = 0; i < grade.length; i++) {
		    data = data + grade[i];
		    if(grade.length > i+1)
		    	data = data+",";
		}
		data = data+")";
		System.out.println(data);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/rutaAlumno/escogecursos2.jsp");
		List<Asignaturagenerica> listaAsignaturas= asignaturaDAO.Listarporgrados(data);
		request.setAttribute("lista", listaAsignaturas);
		dispatcher.forward(request, response);
		
	}
	private void escogercurso(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		String[] list = request.getParameterValues("cursos");
		for (int i = 0; i < list.length; i++) {
		   System.out.println(list[i]);
		   idregist.add(Integer.parseInt(list[i]));
		   System.out.println(idregist);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/rutaAlumno/escogecursos3.jsp");
		dispatcher.forward(request, response);
	}
	private void registrarse(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Alumno alumno = new Alumno(0, Integer.parseInt(request.getParameter("cui")) , request.getParameter("nombre"), request.getParameter("apellidos"), request.getParameter("correo"), 0);
		
		for(int i = 0; i < idregist.size();i++) {
			alumno.setAsignatura(idregist.get(i));
		
			alumnoDAO.insertar(alumno);
			
		}
		idregist.clear();
		List<Asignaturagenerica> listaAsignaturas = asignaturaDAO.Listarporcui(Integer.valueOf(request.getParameter("cui")));
		request.setAttribute("lista", listaAsignaturas);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/rutaAlumno/vercursosmatric.jsp");
		dispatcher.forward(request, response);
		
	}
	private void buscarcui(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		List<Asignaturagenerica> listaAsignaturas = asignaturaDAO.Listarporcui(Integer.valueOf(request.getParameter("fname")));
		request.setAttribute("lista", listaAsignaturas);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/rutaAlumno/vercursosmatric.jsp");
		dispatcher.forward(request, response);
	}
}