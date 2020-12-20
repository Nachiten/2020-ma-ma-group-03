package domain.controllers;

import domain.entities.apiMercadoLibre.*;
import domain.entities.entidades.Entidad;
import domain.entities.entidades.EntidadBase;
import domain.entities.entidades.EntidadJuridica;
import domain.entities.tipoEntidadJuridica.Empresa;
import domain.entities.tipoEntidadJuridica.OrganizacionSectorSocial;
import domain.entities.tipoEntidadJuridica.Sector;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.stream.Collectors;

public class AccionesEntidadJuridicaController {

    private ModalAndViewController modalAndViewController;
    private OperadorController operadorController;
    private Repositorio<EntidadJuridica> repoEntidadJuridica;
    private Repositorio<Entidad> repoEntidad;
    private Repositorio<EntidadBase> repoEntidadBase;
    private Repositorio<Pais> respoPaises;
    private Repositorio<Estado> repoProvincias;
    private Repositorio<Ciudad> repoCiudades;
    private Repositorio<Sector> repoSector;

    public AccionesEntidadJuridicaController(ModalAndViewController modalAndViewController, OperadorController operadorController) {
        this.modalAndViewController = modalAndViewController;
        this.repoEntidadJuridica = FactoryRepositorio.get(EntidadJuridica.class);
        this.repoEntidad = FactoryRepositorio.get(Entidad.class);
        this.repoEntidadBase = FactoryRepositorio.get(EntidadBase.class);
        this.operadorController = operadorController;
        this.respoPaises = FactoryRepositorio.get(Pais.class);
        this.repoProvincias = FactoryRepositorio.get(Estado.class);
        this.repoCiudades = FactoryRepositorio.get(Ciudad.class);
        this.repoSector = FactoryRepositorio.get(Sector.class);
    }

    //Muestra página acciones entidad jurídica
    private ModelAndView modalAndViewAltaEntidadJuridica(){

        return new ModelAndView(modalAndViewController.getParametros(),"accionesEntidadesJuridicas.hbs");
    }

    public ModelAndView mostrarPaginaAccionesEntidadJuridica(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewAltaEntidadJuridica);
    }

    ///////////////
    //Muestra modal nueva entidad jurídica
    private ModelAndView modalAndViewMostrarFormularioNuevaEntidadJuridica() {
        List<Pais> listaPaisesEJ = respoPaises.buscarTodos();
        List<Estado> listaProvinciasEJ = repoProvincias.buscarTodos();
        List<Ciudad> listaCiudadesEJ = repoCiudades.buscarTodos();
        List<EntidadJuridica> listaEntidadesJuridicas = repoEntidadJuridica.buscarTodos();
        List<Sector> listaSectores = repoSector.buscarTodos();
        modalAndViewController.getParametros().put("listaPaisesEJ", listaPaisesEJ);
        modalAndViewController.getParametros().put("listaProvinciasEJ", listaProvinciasEJ);
        modalAndViewController.getParametros().put("listaCiudadesEJ", listaCiudadesEJ);
        modalAndViewController.getParametros().put("listaEntidadesJuridicas", listaEntidadesJuridicas);
        modalAndViewController.getParametros().put("listaSectores", listaSectores);
        return new ModelAndView(modalAndViewController.getParametros(),"modalNuevaEntidadJuridica.hbs");
    }

    public ModelAndView mostrarModalNuevaEntidadJuridica(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewMostrarFormularioNuevaEntidadJuridica);
    }

    //Modal para confirmar si se da de alta nueva enidad jurídica
    private ModelAndView modalAndViewMostrarModalConfirmarNuevaEntidadJuridica() {
        modalAndViewController.getParametros().put("mensaje", "¿Desea dar de alta esta nueva entidad jurídica?");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativoConfirmarNuevaEntidadJuridica.hbs");
    }

    public ModelAndView mostrarModalParaConfirmarNuevaEntidadJuridica(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewMostrarModalConfirmarNuevaEntidadJuridica);
    }

    //Guardar y confirmar que se dio de alta nueva entidad jurídica
    public ModelAndView modalAndViewMostrarModalConfirmacionNuevaEntidadJuridica(Request request){
        String nombre = request.queryParams("nombreEntidadJuridicaNueva");
        EntidadJuridica entidadJuridicaNueva = new EntidadJuridica(nombre);

        if (nombre.isEmpty()){
            modalAndViewController.getParametros().put("mensaje", "Ingrese un nombre para la nueva entidad jurídica.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        if (operadorController.persistenciaNoValida(repoEntidadJuridica, entidadJuridicaNueva)){
            modalAndViewController.getParametros().put("mensaje", "El nombre de la entidad jurídica ingresada ya existe, intentelo nuevamente con otro nombre.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        modalAndViewController.getParametros().put("mensaje", "Se dio de alta correctamente la nueva entidad juridica "+nombre+".");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
    }

    public ModelAndView mostrarModalConfirmacionNuevaEntidadJuridica(Request request, Response response){
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewMostrarModalConfirmacionNuevaEntidadJuridica(request));
    }

    private ModelAndView modalAndViewMostrarModalConfirmarNuevaEntidadEmpresa() {
        modalAndViewController.getParametros().put("mensaje", "¿Desea dar de alta esta nueva empresa?");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativoConfirmarNuevaEntidadEmpresa.hbs");
    }

    public ModelAndView mostrarModalParaConfirmarNuevaEntidadEmpresa(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewMostrarModalConfirmarNuevaEntidadEmpresa);
    }

    private boolean yaExisteCuit(String cuitEmpresa, List<Entidad> entidades) {
        return entidades.stream().anyMatch(unaEntidad -> (unaEntidad.getCuitEntidad() != null && unaEntidad.getCuitEntidad().equals(cuitEmpresa)));
    }

    private boolean yaExisteRazonsocial(String razonSocialEmpresa, List<Entidad> entidades) {
        return entidades.stream().anyMatch(unaEntidad -> unaEntidad.getRazonSocialEntidad().equals(razonSocialEmpresa));
    }

    private ModelAndView modalAndViewMostrarModalConfirmacionNuevaEntidadEmpresa(Request request) {
        String entidadJuridicaSeleccionadaEmpresa = request.queryParams("entidadJuridicaSeleccionadaEmpresa");
        String nombreFicticioEmpresa = request.queryParams("nombreFicticioEmpresa");
        String codigoInscripcionDefinitiva = request.queryParams("codigoInscripcionDefinitiva");
        String cuitEmpresa = request.queryParams("cuitEmpresa");
        String razonSocialEmpresa = request.queryParams("razonSocialEmpresa");
        String sectorEmpresa = request.queryParams("sectorEmpresa");
        String promedioVAEmpresa = request.queryParams("promedioVAEmpresa");
        String cantPersonalEmpresa = request.queryParams("cantPersonalEmpresa");

        if (operadorController.soyCadenaVaciaONula(cantPersonalEmpresa)){
            modalAndViewController.getParametros().put("mensaje", "Ingrese una cantidad de personal.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        if (operadorController.soyCadenaVaciaONula(entidadJuridicaSeleccionadaEmpresa)){
            modalAndViewController.getParametros().put("mensaje", "Seleccione una entidad jurídica.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        if (operadorController.soyCadenaVaciaONula(nombreFicticioEmpresa)){
            modalAndViewController.getParametros().put("mensaje", "Ingrese un nombre para la empresa.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        if (operadorController.soyCadenaVaciaONula(codigoInscripcionDefinitiva)){
            modalAndViewController.getParametros().put("mensaje", "Ingrese un código de inscripción definitiva.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        if (operadorController.soyCadenaVaciaONula(cuitEmpresa)){
            modalAndViewController.getParametros().put("mensaje", "Ingrese un CUIT/CUIL.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        if (operadorController.soyCadenaVaciaONula(razonSocialEmpresa)){
            modalAndViewController.getParametros().put("mensaje", "Ingrese una razón social.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        if (operadorController.soyCadenaVaciaONula(sectorEmpresa)){
            modalAndViewController.getParametros().put("mensaje", "Seleccione un sector.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        if (operadorController.soyCadenaVaciaONula(promedioVAEmpresa)){
            modalAndViewController.getParametros().put("mensaje", "Ingrese un promedio de ventas anuales.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        String calle = request.queryParams("calle");
        String altura = request.queryParams("altura");
        String pais = request.queryParams("pais");
        String provincia = request.queryParams("provincia");
        String ciudad = request.queryParams("ciudad");

        if (operadorController.soyCadenaVaciaONula(calle)){
            modalAndViewController.getParametros().put("mensaje", "Ingrese un nombre de calle.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        if (operadorController.noSeleccioneUnaOpcion(ciudad)){
            modalAndViewController.getParametros().put("mensaje", "Seleccione una cuidad.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        if (operadorController.noSeleccioneUnaOpcion(provincia)){
            modalAndViewController.getParametros().put("mensaje", "Seleccione una provincia.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        if (operadorController.noSeleccioneUnaOpcion(pais)){
            modalAndViewController.getParametros().put("mensaje", "Seleccione un país.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        if (operadorController.soyCadenaVaciaONula(altura)){
            modalAndViewController.getParametros().put("mensaje", "Ingrese la altura de la calle.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        List<Entidad> entidades = repoEntidad.buscarTodos();
        if (yaExisteCuit(cuitEmpresa, entidades)){
            modalAndViewController.getParametros().put("mensaje", "El CUIT/CUIL ingresado ya se encuentra registrado.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        if (yaExisteRazonsocial(razonSocialEmpresa, entidades)){
            modalAndViewController.getParametros().put("mensaje", "La razón social ingresada ya se encuentra registrada.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        //genero la direccion postal
        DireccionPostal direccionPostal = operadorController.generarDireccionPostal(request);
        Entidad entidadNueva = new Entidad(nombreFicticioEmpresa, razonSocialEmpresa, cuitEmpresa, direccionPostal, codigoInscripcionDefinitiva);

        //asocio la entidad jurídica a la nueva entidad empresa
        int idEntidadJuridica = Integer.parseInt(entidadJuridicaSeleccionadaEmpresa);
        EntidadJuridica entidadJuridica = repoEntidadJuridica.buscar(idEntidadJuridica);
        entidadNueva.agregarEntidadJuridicaAsociada(entidadJuridica);

        //instancio y asocio el tipo empresa a la nueva entidad
        int idSector = Integer.parseInt(sectorEmpresa);
        Sector sector = repoSector.buscar(idSector);
        int promedioVA = Integer.parseInt(promedioVAEmpresa);
        int cantPersonas = Integer.parseInt(cantPersonalEmpresa);
        Empresa empresa = new Empresa(sector, promedioVA, cantPersonas);
        entidadNueva.agregarTipoEmpresa(empresa);

        if (operadorController.persistenciaNoValida(repoEntidad, entidadNueva)){
            modalAndViewController.getParametros().put("mensaje", "No se pudo crear la nueva entidad, inténtelo nuevamente.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        modalAndViewController.getParametros().put("mensaje", "Se creo exitosamente la nueva entidad "+nombreFicticioEmpresa+".");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativoConfirmacionDeNuevaEntidad.hbs");
    }

    public ModelAndView mostrarModalConfirmacionNuevaEntidadEmpresa(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewMostrarModalConfirmacionNuevaEntidadEmpresa(request));
    }

    private ModelAndView modalAndViewMostrarModalConfirmarNuevaEntidadOrgSoc() {
        modalAndViewController.getParametros().put("mensaje", "¿Desea dar de alta esta nueva organización de sector social?");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativoConfirmarNuevaEntidadOrgSoc.hbs");
    }

    public ModelAndView mostrarModalParaConfirmarNuevaEntidadOrgSoc(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewMostrarModalConfirmarNuevaEntidadOrgSoc);
    }

    private ModelAndView modalAndViewMostrarModalConfirmacionNuevaEntidadOrgSoc(Request request) {
        String entidadJuridicaSeleccionadaOrgSoc = request.queryParams("entidadJuridicaSeleccionadaOrgSoc");
        String nombreFicticioOrgSoc = request.queryParams("nombreFicticioOrgSoc");
        String razonSocialOrgSoc = request.queryParams("razonSocialOrgSoc");
        if (operadorController.soyCadenaVaciaONula(entidadJuridicaSeleccionadaOrgSoc)){
            modalAndViewController.getParametros().put("mensaje", "Seleccione una entidad jurídica.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        if (operadorController.soyCadenaVaciaONula(razonSocialOrgSoc)){
            modalAndViewController.getParametros().put("mensaje", "Ingrese una razón social.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        if (operadorController.soyCadenaVaciaONula(nombreFicticioOrgSoc)){
            modalAndViewController.getParametros().put("mensaje", "Ingrese un nombre para la empresa.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        List<Entidad> entidades = repoEntidad.buscarTodos();
        if (yaExisteRazonsocial(razonSocialOrgSoc, entidades)){
            modalAndViewController.getParametros().put("mensaje", "La razón social ingresada ya se encuentra registrada.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        int idEntidadJuridica = Integer.parseInt(entidadJuridicaSeleccionadaOrgSoc);
        EntidadJuridica entidadJuridicaSeleccionada = repoEntidadJuridica.buscar(idEntidadJuridica);

        Entidad nuevaEntidadOrgSoc = new Entidad(nombreFicticioOrgSoc, razonSocialOrgSoc);
        nuevaEntidadOrgSoc.agregarEntidadJuridicaAsociada(entidadJuridicaSeleccionada);
        OrganizacionSectorSocial nuevaOrgSoc = new OrganizacionSectorSocial();
        nuevaEntidadOrgSoc.agregarTipoOrganizacionSectorSocial(nuevaOrgSoc);

        if (operadorController.persistenciaNoValida(repoEntidad, nuevaEntidadOrgSoc)){
            modalAndViewController.getParametros().put("mensaje", "No se pudo crear la nueva entidad, inténtelo nuevamente.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        modalAndViewController.getParametros().put("mensaje", "Se creo exitosamente la nueva entidad "+razonSocialOrgSoc+".");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativoConfirmacionDeNuevaEntidad.hbs");
    }

    public ModelAndView mostrarModalConfirmacionNuevaEntidadOrgSoc(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewMostrarModalConfirmacionNuevaEntidadOrgSoc(request));
    }

    private ModelAndView modalAndViewMostrarModalConfirmarNuevaEntidadBase() {
        modalAndViewController.getParametros().put("mensaje", "¿Desea dar de alta esta nueva entidad base?");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativoConfirmarNuevaEntidadBase.hbs");
    }

    public ModelAndView mostrarModalParaConfirmarNuevaEntidadBase(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewMostrarModalConfirmarNuevaEntidadBase);
    }

    private ModelAndView modalAndViewMostrarModalConfirmacionNuevaEntidadBase(Request request) {
        String entidadJuridicaSeleccionadaBase = request.queryParams("entidadJuridicaSeleccionadaBase");
        String nombreFicticioBase = request.queryParams("nombreFicticioBase");
        if (operadorController.soyCadenaVaciaONula(nombreFicticioBase)){
            modalAndViewController.getParametros().put("mensaje", "Ingrese un nombre para la empresa.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        if (operadorController.soyCadenaVaciaONula(entidadJuridicaSeleccionadaBase)){
            modalAndViewController.getParametros().put("mensaje", "Seleccione una entidad jurídica.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        EntidadBase nuevaEntidadBase = new EntidadBase(nombreFicticioBase);
        int idEntidadJuridica = Integer.parseInt(entidadJuridicaSeleccionadaBase);
        EntidadJuridica entidadJuridicaSeleccionada = repoEntidadJuridica.buscar(idEntidadJuridica);
        nuevaEntidadBase.asociarEntidadJuridica(entidadJuridicaSeleccionada);

        if (operadorController.persistenciaNoValida(repoEntidadBase, nuevaEntidadBase)){
            modalAndViewController.getParametros().put("mensaje", "No se pudo crear la nueva entidad, inténtelo nuevamente.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }
        modalAndViewController.getParametros().put("mensaje", "Se creo exitosamente la nueva entidad "+nombreFicticioBase+".");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativoConfirmacionDeNuevaEntidad.hbs");
    }

    public ModelAndView mostrarModalConfirmacionNuevaEntidadBase(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewMostrarModalConfirmacionNuevaEntidadBase(request));
    }

    ////////////
    private ModelAndView modalAndViewMostrarListaEntidadesJuridicasInhabilitadas() {
        List<EntidadJuridica> listaEntidadesJuridicas = repoEntidadJuridica.buscarTodos();
        List<EntidadJuridica> listaEntidadesJuridicasInhabilitadas = listaEntidadesJuridicas.stream().filter(entidadJuridica -> !entidadJuridica.estoyHabilitado()).collect(Collectors.toList());
        modalAndViewController.getParametros().put("listaEntidadesJuridicasInhabilitadas", listaEntidadesJuridicasInhabilitadas);
        return new ModelAndView(modalAndViewController.getParametros(), "modalHabilitarEntidadesJuridicas.hbs");
    }

    public ModelAndView mostrarModalHabilitarEntidadesjuridicas(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewMostrarListaEntidadesJuridicasInhabilitadas);
    }

    ///////////////
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
