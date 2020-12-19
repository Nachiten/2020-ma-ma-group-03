package domain.controllers;

import domain.entities.usuarios.Usuario;
import spark.Request;

import java.util.HashMap;
import java.util.Optional;

public class AdministradorDeSesion {

	private HashMap<String, spark.Session> sesionPorID;

	public AdministradorDeSesion() {
		this.sesionPorID = new HashMap<>();
	}

	private void verificarQueEsUnica(spark.Session sesionAVerificar){
		if(sesionPorID.containsKey(sesionAVerificar.id())){
			throw new RuntimeException();
		}
	}

	public String iniciarSesion(Request request, Usuario usuario) {
		spark.Session sesion = request.session(true);
		verificarQueEsUnica(sesion);
		sesion.attribute("id",usuario.getId());
		String idDeSesion = sesion.id();
		sesionPorID.put(idDeSesion, sesion);
		return idDeSesion;
	}
	
	public void cerrarSesion(String potencialIDDeSesion) {
		if(this.esLaSesionValida(potencialIDDeSesion)){
			sesionPorID.remove(potencialIDDeSesion);
		}
	}

	public boolean esLaSesionValida(String potencialIDDeSesion) {
		return sesionPorID.containsKey(potencialIDDeSesion);
	}
}