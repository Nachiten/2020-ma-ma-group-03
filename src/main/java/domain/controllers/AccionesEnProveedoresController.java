package domain.controllers;

import domain.entities.apiMercadoLibre.*;
import domain.entities.vendedor.Proveedor;
import domain.repositories.RepoProveedores;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AccionesEnProveedoresController {
    private Repositorio<Proveedor> repoProveedor;
    private Repositorio<Pais> respoPaises;
    private Repositorio<Estado> repoProvincias;
    private Repositorio<Ciudad> repoCiudades;
    private ModalAndViewController modalAndViewController;
    private OperadorController operadorController;


    public AccionesEnProveedoresController(ModalAndViewController modalAndViewController, OperadorController operadorController) {

        this.repoProveedor = FactoryRepositorio.get(Proveedor.class);
        this.modalAndViewController = modalAndViewController;
        this.operadorController = operadorController;
        this.respoPaises = FactoryRepositorio.get(Pais.class);
        this.repoProvincias = FactoryRepositorio.get(Estado.class);
        this.repoCiudades =FactoryRepositorio.get(Ciudad.class);

    }

    private ModelAndView modalAndViewAccionesProveedores() {
        return new ModelAndView(modalAndViewController.getParametros(), "accionesProveedores.hbs");
    }

    public ModelAndView mostrarPaginaAccionesProveedores(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewAccionesProveedores);
    }

    private ModelAndView modelAndViewNuevoProveedor(){
        List<Pais> listaPaises = respoPaises.buscarTodos();
        List<Estado> listaProvincias = repoProvincias.buscarTodos();
        List<Ciudad> listaCiudades = repoCiudades.buscarTodos();
        modalAndViewController.getParametros().put("listaPaises", listaPaises);
        modalAndViewController.getParametros().put("listaProvincias", listaProvincias);
        modalAndViewController.getParametros().put("listaCiudades", listaCiudades);
        return new ModelAndView(modalAndViewController.getParametros(),"modalNuevoProveedor.hbs");
    }
    public ModelAndView mostrarModalNuevoProveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modelAndViewNuevoProveedor);
    }

    private ModelAndView modelAndViewconfirmarNuevoProveedor() {
        modalAndViewController.getParametros().put("mensaje", "¿Está seguro que quiere crear a este nuevo proveedor?");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativoConfirmarNuevoProveedor.hbs");
    }

    //muestra modal para que se confirme si se desea guardar nuevo proveedor
    public ModelAndView mostrarModalParaConfirmarNuevoProveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modelAndViewconfirmarNuevoProveedor);
    }

    private boolean yaExisteRazonsocial(String razonSocial){
        List<Proveedor> proveedores = repoProveedor.buscarTodos();
        return proveedores.stream().anyMatch(unProveedor -> unProveedor.getRazonSocialProveedor().equals(razonSocial));
    }

    private boolean yaExisteCuit(String cuit){
        List<Proveedor> proveedores = repoProveedor.buscarTodos();
        return proveedores.stream().anyMatch(unProveedor -> unProveedor.getCuit().equals(cuit));
    }
    private ModelAndView modelAndViewGuardarNuevoProveedor(Request request){
        String nombreIngresado = request.queryParams("nombre");
        String apellidoIngresado = request.queryParams("apellido");
        String razonSocialIngresado = request.queryParams("razonSocial");
        String cuit_cuilIngresado = request.queryParams("cuit_cuil");
        if (operadorController.soyCadenaVaciaONula(razonSocialIngresado)){
            modalAndViewController.getParametros().put("mensaje", "Ingrese una razón social.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        if (operadorController.soyCadenaVaciaONula(cuit_cuilIngresado)){
            modalAndViewController.getParametros().put("mensaje", "Ingrese un CUIT/CUIL.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        String idPais = request.queryParams("pais");
        String idProvincia = request.queryParams("provincia");
        String idCiudad = request.queryParams("ciudad");
        if (operadorController.noSeleccioneUnaOpcion(idPais)){
            modalAndViewController.getParametros().put("mensaje", "Seleccione un país.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        if (operadorController.noSeleccioneUnaOpcion(idProvincia)){
            modalAndViewController.getParametros().put("mensaje", "Seleccione una provincia.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        if (operadorController.noSeleccioneUnaOpcion(idCiudad)){
            modalAndViewController.getParametros().put("mensaje", "Seleccione una cuidad.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        String calle = request.queryParams("calle");
        String alturaString = request.queryParams("altura");

        if (operadorController.soyCadenaVaciaONula(calle)){
            modalAndViewController.getParametros().put("mensaje", "Ingrese un nombre de calle.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        if (operadorController.soyCadenaVaciaONula(alturaString)){
            modalAndViewController.getParametros().put("mensaje", "Ingrese la altura de la calle.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        if (yaExisteRazonsocial(razonSocialIngresado)){
            modalAndViewController.getParametros().put("mensaje", "La razón social ingresada ya se encuentra registrada.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        if (yaExisteCuit(cuit_cuilIngresado)){
            modalAndViewController.getParametros().put("mensaje", "El CUIT/CUIL ingresado ya se encuentra registrado.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        DireccionPostal direccionPostal = operadorController.generarDireccionPostal(request);
        Proveedor proveedorAGuardar = new Proveedor(nombreIngresado, apellidoIngresado, cuit_cuilIngresado, direccionPostal, razonSocialIngresado);

        if (operadorController.persistenciaNoValida(repoProveedor, proveedorAGuardar)){
            modalAndViewController.getParametros().put("mensaje", "No se pudo crear el nuevo proveedor, inténtelo nuevamente.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        modalAndViewController.getParametros().put("mensaje", "Se creo exitosamente el nuevo proveedor.");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativoConfirmacionDeNuevoProveedor.hbs");
    }

    public ModelAndView guardarNuevoProveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modelAndViewGuardarNuevoProveedor(request));
    }

    private ModelAndView modalAndViewListarProveedoresInhabilitados(){

        List<Proveedor> proveedores = this.repoProveedor.buscarTodos();
        List<Proveedor> proveedoresInhabilitados = proveedores.stream().filter(proveedor -> !proveedor.getEstoyHabilitado()).collect(Collectors.toList());
        modalAndViewController.getParametros().put("listadoProveedoresInhabilitados", proveedoresInhabilitados);
        return new ModelAndView(modalAndViewController.getParametros(), "modalHabilitarProveedores.hbs");
    }

    public ModelAndView mostrarModalHabilitarProveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewListarProveedoresInhabilitados);
    }

    private ModelAndView modalAndViewHabilitarProveedor(Request request) {
        int idProveedor = Integer.parseInt(request.params("id"));
        Proveedor proveedorAEditar = this.repoProveedor.buscar(idProveedor);
        proveedorAEditar.cambiarAHabilitado();
        this.repoProveedor.modificar(proveedorAEditar);
        modalAndViewController.getParametros().put("mensaje", "El proveedor "+proveedorAEditar.getRazonSocialProveedor()+" se habilitó correctamente");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
    }

    public ModelAndView mostrarConfirmacionHabilitarProveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewHabilitarProveedor(request));
    }



    private ModelAndView modalAndViewListarProveedoresHabilitados() {
        List<Proveedor> proveedores = this.repoProveedor.buscarTodos();
        List<Proveedor> proveedoresInhabilitados = proveedores.stream().filter(Proveedor::getEstoyHabilitado).collect(Collectors.toList());
        modalAndViewController.getParametros().put("listadoProveedoresHabilitados", proveedoresInhabilitados);
        return new ModelAndView(modalAndViewController.getParametros(), "modalEditarProveedores.hbs");
    }

    public ModelAndView mostrarModalEditarProveedores(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewListarProveedoresHabilitados);
    }

    private ModelAndView modalAndViewEditarUnProveedor(Request request) {
        int idProveedor = Integer.parseInt(request.params("id"));
        Proveedor proveedorAEditar = this.repoProveedor.buscar(idProveedor);

        modalAndViewController.getParametros().put("idProveedor", proveedorAEditar.getId());
        modalAndViewController.getParametros().put("nombreProveedor", proveedorAEditar.getNombreProveedor());
        modalAndViewController.getParametros().put("apellidoproveedor", proveedorAEditar.getApellidoProveedor());
        modalAndViewController.getParametros().put("razonSocial", proveedorAEditar.getRazonSocialProveedor());
        modalAndViewController.getParametros().put("cuil", proveedorAEditar.getCuit());

        DireccionPostal direccionPostalproveedor = proveedorAEditar.getDireccionPostal();
        if (Objects.equals(direccionPostalproveedor, null)){
            direccionPostalproveedor = new DireccionPostal();
            proveedorAEditar.asociarDireccionPostal(direccionPostalproveedor);
        }
        Pais paisProveedor = direccionPostalproveedor.getPais();
        Estado provinciaProveedor = direccionPostalproveedor.getProvincia();
        Ciudad ciudadProveedor = direccionPostalproveedor.getCiudad();
        modalAndViewController.getParametros().put("ciudadProveedor", ciudadProveedor.getName());
        modalAndViewController.getParametros().put("provinciaProveedor", provinciaProveedor.getName());
        modalAndViewController.getParametros().put("paisProveedor", paisProveedor.getName());

        Direccion direccionProveedor = direccionPostalproveedor.getDireccion();
        if (Objects.equals(direccionProveedor, null)){
            direccionProveedor = new Direccion();
            direccionPostalproveedor.asociarDireccion(direccionProveedor);
        }
        modalAndViewController.getParametros().put("barrio", direccionProveedor.getBarrio());
        modalAndViewController.getParametros().put("calle",direccionProveedor.getCalle());
        modalAndViewController.getParametros().put("numeroCalle", direccionProveedor.getNumero());

        int piso = direccionProveedor.getPiso();
        if (piso!=0) modalAndViewController.getParametros().put("pisoDepartamento", direccionProveedor.getPiso());

        modalAndViewController.getParametros().put("departamento", direccionProveedor.getDpto());

        return new ModelAndView(modalAndViewController.getParametros(), "modalDatosAEditarDeUnProveedor.hbs");
    }

    public ModelAndView mostrarModalParaEditarUnProveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewEditarUnProveedor(request));
    }

    private ModelAndView modalAndViewActualizarDireccionDeProveedor(Request request) {
        int idProveedor = Integer.parseInt(request.params("id"));
        Proveedor proveedorAEditar = this.repoProveedor.buscar(idProveedor);

        modalAndViewController.getParametros().put("idProveedor", proveedorAEditar.getId());

        List<Pais> listaPaisesApi = respoPaises.buscarTodos();
        List<Estado> listaProvinciasApi = repoProvincias.buscarTodos();
        List<Ciudad> listaCiudadesApi = repoCiudades.buscarTodos();
        modalAndViewController.getParametros().put("listaPaisesApi", listaPaisesApi);
        modalAndViewController.getParametros().put("listaProvinciasApi", listaProvinciasApi);
        modalAndViewController.getParametros().put("listaCiudadesApi", listaCiudadesApi);
        return new ModelAndView(modalAndViewController.getParametros(), "modalCambiarDireccionProveedor.hbs");
    }

    public ModelAndView mostrarModalActualizarDireccionProveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewActualizarDireccionDeProveedor(request));
    }

    private ModelAndView modalAndViewDarDeBajaProveedor(Request request) {
        int idProveedor = Integer.parseInt(request.params("id"));
        Proveedor proveedorAEditar = this.repoProveedor.buscar(idProveedor);
        proveedorAEditar.cambiarAInhabilitado();
        this.repoProveedor.modificar(proveedorAEditar);
        modalAndViewController.getParametros().put("mensaje", "Se dio de baja correctamente al proveedor "+proveedorAEditar.getRazonSocialProveedor());
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
    }

    public ModelAndView mostrarModalConfirmacionBajaProveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewDarDeBajaProveedor(request));
    }

}
