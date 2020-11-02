package domain.controllers;

import domain.entities.usuarios.Usuario;
import spark.Request;

import javax.servlet.http.HttpSession;

public class AdministradorDeSesion {

	private int id;
	private Usuario usuario;
	
	public <T> void iniciarSesion(Request request,Usuario usuario) {
		spark.Session sesion = request.session(true);
    	sesion.attribute("id",obtenerId(usuario));
    	this.id = obtenerId(usuario);
    	this.usuario = usuario;
	}
	
	public void cerrarSesion(Request request) {
		HttpSession sesion = request.session().raw();
    	sesion.invalidate();
	}
	
	public int obtenerIdDeSesion(Request request) {
		HttpSession sesion = request.session().raw();
		if (sesion.getAttribute("id") == null){
			return -1;
		}
		return (int) sesion.getAttribute("id");
	}
	
	private <T> int obtenerId(T usuario) {
		Usuario unUsuario = (Usuario) usuario;
		return unUsuario.getId();
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
