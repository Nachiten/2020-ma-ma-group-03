package domain.controllers;

import domain.entities.entidades.Entidad;
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
    private Repositorio<Entidad> repoEntidades;

    public PerfilUsuarioEstandarController(ModalAndViewController modalAndViewController) {
        this.modalAndViewController = modalAndViewController;
        this.repoUsuario = FactoryRepositorio.get(Usuario.class);
        this.repoEntidades = FactoryRepositorio.get(Entidad.class);
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
        Entidad entidad = usuarioLogueado.getEntidad();

        //todo arreglar entidad juridica
        modalAndViewController.getParametros().put("nombreEntidadJuridica", entidad.getEntidadJuridicaAsociada().getNombreEntidadJuridica());
        modalAndViewController.getParametros().put("nombreFicticio", entidad.getNombreFicticioEntidad());
        modalAndViewController.getParametros().put("razonSocial", entidad.getRazonSocialEntidad());
        modalAndViewController.getParametros().put("cuit", entidad.getCuitEntidad());
        modalAndViewController.getParametros().put("cid", entidad.getCodigoInscripcionDefinitiva());
        //modalAndViewController.getParametros().put("entidadesBase", entidad.getEntidadesBase());

        if(entidad.getDireccionPostalEntidad().getPais() != null){
            modalAndViewController.getParametros().put("pais", entidad.getDireccionPostalEntidad().getPais().getName());
        }

        if(entidad.getDireccionPostalEntidad().getProvincia() != null){
            modalAndViewController.getParametros().put("provincia", entidad.getDireccionPostalEntidad().getProvincia().getName());
        }

        if(entidad.getDireccionPostalEntidad().getCiudad() != null){
            modalAndViewController.getParametros().put("ciudad", entidad.getDireccionPostalEntidad().getCiudad().getName());
        }

        return new ModelAndView(modalAndViewController.getParametros(),"modalDetalleEntidadJuridica.hbs");
    }

    private Entidad buscarEntidadJuridica(int id){
        List<Entidad> entidadesJuridicas = this.repoEntidades.buscarTodos();

        for(Entidad unaEntidad : entidadesJuridicas){
            if(unaEntidad.getId() == id){
                return unaEntidad;
            }
        }
        return null;
    }


}
