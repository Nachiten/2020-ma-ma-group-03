package domain.controllers;


import domain.entities.usuarios.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListarUsuariosController {

    private Repositorio<Usuario> repoUsuario;

    private Map<String, Object> parametros;


    public ListarUsuariosController() {

        this.repoUsuario = FactoryRepositorio.get(Usuario.class);
        this.parametros = new HashMap<>();
    }

    public ModelAndView listarUsuarios(Request request, Response response){

        List<Usuario> usuarios = this.repoUsuario.buscarTodos();

        parametros.put("listadoDeUsuarios", usuarios);

        return new ModelAndView(parametros, "listadoDeUsuarios.hbs");

    }

}
