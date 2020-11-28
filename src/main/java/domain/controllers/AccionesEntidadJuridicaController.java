package domain.controllers;

import domain.entities.apiMercadoLibre.*;
import domain.entities.entidades.EntidadJuridica;
import domain.entities.tipoEntidadJuridica.TipoEntidadJuridica;
import domain.entities.usuarios.Usuario;
import domain.entities.vendedor.Proveedor;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AccionesEntidadJuridicaController {

    private ModalAndViewController modalAndViewController;
    private OperadorController operadorController;
    private Repositorio<EntidadJuridica> repoEntidadJuridica;
    private Repositorio<Pais> respoPaises;
    private Repositorio<Estado> repoProvincias;
    private Repositorio<Ciudad> repoCiudades;

    public AccionesEntidadJuridicaController(ModalAndViewController modalAndViewController, OperadorController operadorController) {
        this.modalAndViewController = modalAndViewController;
        this.repoEntidadJuridica = FactoryRepositorio.get(EntidadJuridica.class);
        this.operadorController = operadorController;
        this.respoPaises = FactoryRepositorio.get(Pais.class);
        this.repoProvincias = FactoryRepositorio.get(Estado.class);
        this.repoCiudades =FactoryRepositorio.get(Ciudad.class);
    }

    private ModelAndView modalAndViewAltaEntidadJuridica(){

        return new ModelAndView(modalAndViewController.getParametros(),"accionesEntidadesJuridicas.hbs");
    }

    public ModelAndView mostrarPaginaAccionesEntidadJuridica(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewAltaEntidadJuridica);
    }

    private ModelAndView modalAndViewMostrarFormularioNuevaEntidadJuridica() {
        List<Pais> listaPaisesEJ = respoPaises.buscarTodos();
        List<Estado> listaProvinciasEJ = repoProvincias.buscarTodos();
        List<Ciudad> listaCiudadesEJ = repoCiudades.buscarTodos();
        modalAndViewController.getParametros().put("listaPaisesEJ", listaPaisesEJ);
        modalAndViewController.getParametros().put("listaProvinciasEJ", listaProvinciasEJ);
        modalAndViewController.getParametros().put("listaCiudadesEJ", listaCiudadesEJ);
        return new ModelAndView(modalAndViewController.getParametros(),"modalNuevaEntidadJuridica.hbs");
    }

    public ModelAndView mostrarModalNuevaEntidadJuridica(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewMostrarFormularioNuevaEntidadJuridica);
    }

    public ModelAndView guardarEntidadJuridica(Request request, Response response){
        String nombre = request.queryParams("nombre");
        String nombreFicticio = request.queryParams("nombreFicticio");
        String codigoInscripcion = request.queryParams("codigoInscripcionDefinitiva");
        String razonSocial = request.queryParams("razonSocial");
        String cuit_cuil = request.queryParams("cuit");

        DireccionPostal direccionPostal = operadorController.generarDireccionPostal(request);

        EntidadJuridica entidadJuridicaAGuardar = new EntidadJuridica(nombre, nombreFicticio, razonSocial,
                cuit_cuil, null, codigoInscripcion, null);

        if (operadorController.persistenciaNoValida(repoEntidadJuridica, entidadJuridicaAGuardar)){
            modalAndViewController.getParametros().put("mensaje", "No se guardaron los datos, intentelo nuevamente.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        modalAndViewController.getParametros().put("mensaje", "Se persistio correctamente la entidad juridica.");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");

    }

    private ModelAndView modalAndViewMostrarListaEntidadesJuridicasInhabilitadas() {
        List<EntidadJuridica> listaEntidadesJuridicas = repoEntidadJuridica.buscarTodos();
        List<EntidadJuridica> listaEntidadesJuridicasInhabilitadas = listaEntidadesJuridicas.stream().filter(entidadJuridica -> !entidadJuridica.estoyHabilitado()).collect(Collectors.toList());
        modalAndViewController.getParametros().put("listaEntidadesJuridicasInhabilitadas", listaEntidadesJuridicasInhabilitadas);
        return new ModelAndView(modalAndViewController.getParametros(), "modalHabilitarEntidadesJuridicas.hbs");
    }

    public ModelAndView mostrarModalHabilitarEntidadesjuridicas(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewMostrarListaEntidadesJuridicasInhabilitadas);
    }

    private ModelAndView modalAndViewMostrarListaEntidadesJuridicasAEditar() {
        List<EntidadJuridica> listaEntidadesJuridicas = repoEntidadJuridica.buscarTodos();
        List<EntidadJuridica> listaEntidadesJuridicasHabilitadas = listaEntidadesJuridicas.stream().filter(EntidadJuridica::estoyHabilitado).collect(Collectors.toList());
        modalAndViewController.getParametros().put("listaEntidadesJuridicasHabilitadas", listaEntidadesJuridicasHabilitadas);
        return new ModelAndView(modalAndViewController.getParametros(), "modalEditarEntidadesJuridicas.hbs");
    }
    public ModelAndView mostrarModalEditarEntidadesJuridicas(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewMostrarListaEntidadesJuridicasAEditar);
    }


}
