package domain.controllers;

import domain.entities.usuarios.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class PerfilUsuarioEstandarController {

    private ModalAndViewController modalAndViewController;
    private Repositorio<Usuario> repoUsuario ;

    public PerfilUsuarioEstandarController(ModalAndViewController modalAndViewController) {
        this.modalAndViewController = modalAndViewController;
        this.repoUsuario = FactoryRepositorio.get(Usuario.class);
    }

    public ModelAndView mostrarPaginaPerfilUsuarioEstandar(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewPerfilUsuarioEstandar);
    }

    private ModelAndView modalAndViewPerfilUsuarioEstandar() {
        Usuario usuarioLogueado = this.modalAndViewController.getUsuario();

        modalAndViewController.getParametros().put("miNombre", usuarioLogueado.getNombre());
        modalAndViewController.getParametros().put("miApellido", usuarioLogueado.getApellido());
        modalAndViewController.getParametros().put("miContrasenia", usuarioLogueado.getContrasenia());

        return new ModelAndView(modalAndViewController.getParametros(), "editarPerfilUsuarioEstandar.hbs");
    }

    public ModelAndView actualizarDatosPerfilUsuarioEstandar(Request request, Response response) {

        Usuario usuarioLogueado = this.modalAndViewController.getUsuario();
        String nombreEditado = request.queryParams("nombre");
        String apellidoEditado = request.queryParams("apellido");
        usuarioLogueado.actualizarDatosPerfil(nombreEditado,apellidoEditado);
        repoUsuario.modificar(usuarioLogueado);
        modalAndViewController.getParametros().put("mensaje","Se actualizaron los datos correctamente.");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");
    }


    public ModelAndView actualizarContraseniaPerfilUsuarioEstandar(Request request, Response response) {
        Usuario usuarioLogueado = this.modalAndViewController.getUsuario();
        String contraseniaEditada = request.queryParams("contrasenia");
        usuarioLogueado.verificarSiLaContraseniaFueActualizada(contraseniaEditada);
        repoUsuario.modificar(usuarioLogueado);
        modalAndViewController.getParametros().put("mensaje","La contraseña se actualizó correctamente.");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");

    }
}
