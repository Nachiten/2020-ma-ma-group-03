package domain.controllers;

import domain.entities.usuarios.Usuario;
import spark.Request;
import java.util.Optional;

public class AdministradorDeSesion {

	private Optional<spark.Session> sesionOpcional;

	public AdministradorDeSesion() {
		this.sesionOpcional = Optional.empty();
	}

	public <T> void iniciarSesion(Request request, T usuario) {
		sesionOpcional = Optional.of(request.session(true));
		sesionOpcional.get().attribute("id",obtenerId(usuario));
	}
	
	public void cerrarSesion(Request request) {
		if(this.esLaSesionValida(request)){
			sesionOpcional.get().invalidate();
		}
	}
	
	public String idDeSesionEn(Request request) {

		return request.session().id();
	}
	
	private <T> int obtenerId(T usuario) {
		Usuario unUsuario = (Usuario) usuario;
		return unUsuario.getId();
	}

	public boolean esLaSesionValida(Request request) {
		return sesionOpcional
				.map((session) -> session.id().equals(this.idDeSesionEn(request)))
				.orElse(false);
	}
}
