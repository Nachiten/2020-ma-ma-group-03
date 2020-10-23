package domain.controllers;

import domain.entities.usuarios.Usuario;
import spark.Request;

import javax.servlet.http.HttpSession;

public class AdministradorDeSesion {
	
	
	public <T> void iniciarSesion(Request request,T usuario) {
		spark.Session sesion = request.session(true);
    	sesion.attribute("id",obtenerId(usuario));
	}
	
	public void cerrarSesion(Request request) {
		HttpSession sesion = request.session().raw();
    	sesion.invalidate();
	}
	
	public int obtenerIdDeSesion(Request request) {
		HttpSession sesion = request.session().raw();
    	int id = (int) sesion.getAttribute("id");
    	return id;
	}
	
	private <T> int obtenerId(T usuario) {
		Usuario unUsuario = (Usuario) usuario;
		return unUsuario.getId();
	}
}
