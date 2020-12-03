package domain.controllers;

import domain.entities.entidades.EntidadJuridica;
import domain.entities.operaciones.OperacionDeEgreso;
import domain.entities.usuarios.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.List;

public class PerfilUsuarioEstandarController {

    private ModalAndViewController modalAndViewController;
    private Repositorio<Usuario> repoUsuario ;
    private Repositorio<EntidadJuridica> repoEntidades;

    public PerfilUsuarioEstandarController(ModalAndViewController modalAndViewController) {
        this.modalAndViewController = modalAndViewController;
        this.repoUsuario = FactoryRepositorio.get(Usuario.class);
        this.repoEntidades = FactoryRepositorio.get(EntidadJuridica.class);
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

    public ModelAndView verEntidadJuridicaAsociada(Request request, Response response) {
        Usuario usuarioLogueado = this.modalAndViewController.getUsuario();
        EntidadJuridica entidadJuridica = usuarioLogueado.getEntidadJuridica();

        modalAndViewController.getParametros().put("nombre", entidadJuridica.getNombreEntidadJuridica());
        modalAndViewController.getParametros().put("nombreFicticio", entidadJuridica.getNombreFicticioEntidadJuridica());
        modalAndViewController.getParametros().put("razonSocial", entidadJuridica.getRazonSocialEntidadJuridica());
        modalAndViewController.getParametros().put("cuit", entidadJuridica.getCuitEntidadJuridica());
        modalAndViewController.getParametros().put("cid", entidadJuridica.getCodigoInscripcionDefinitiva());
        modalAndViewController.getParametros().put("entidadesBase", entidadJuridica.getEntidadesBase());

        if(entidadJuridica.getDireccionPostalEntidadJuridica().getPais() != null){
            modalAndViewController.getParametros().put("pais", entidadJuridica.getDireccionPostalEntidadJuridica().getPais().getName());
        }

        if(entidadJuridica.getDireccionPostalEntidadJuridica().getProvincia() != null){
            modalAndViewController.getParametros().put("provincia", entidadJuridica.getDireccionPostalEntidadJuridica().getProvincia().getName());
        }

        if(entidadJuridica.getDireccionPostalEntidadJuridica().getCiudad() != null){
            modalAndViewController.getParametros().put("ciudad", entidadJuridica.getDireccionPostalEntidadJuridica().getCiudad().getName());
        }

        return new ModelAndView(modalAndViewController.getParametros(),"modalDetalleEntidadJuridica.hbs");
    }

    private EntidadJuridica buscarEntidadJuridica(int id){
        List<EntidadJuridica> entidadesJuridicas = this.repoEntidades.buscarTodos();

        for(EntidadJuridica unaEntidad : entidadesJuridicas){
            if(unaEntidad.getId() == id){
                return unaEntidad;
            }
        }
        return null;
    }


}
