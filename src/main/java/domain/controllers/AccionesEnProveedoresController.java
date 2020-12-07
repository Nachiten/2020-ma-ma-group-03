package domain.controllers;

import domain.entities.apiMercadoLibre.*;
import domain.entities.vendedor.Proveedor;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

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
        String calle = request.queryParams("calle");
        String alturaString = request.queryParams("altura");
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

    private ModelAndView modalAndViewConfirmarHabilitarProveedor(Request request) {
        int idProveedor = Integer.parseInt(request.params("id"));
        Proveedor proveedorAHabilitar = this.repoProveedor.buscar(idProveedor);
        modalAndViewController.getParametros().put("id", proveedorAHabilitar.getId());
        modalAndViewController.getParametros().put("mensaje", "¿Está seguro de habilitar al proveedor "+proveedorAHabilitar.getRazonSocialProveedor()+"?");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativoConfirmarHabilitarProveedor.hbs");
    }

    public ModelAndView mostrarModalParaConfirmarHabilitarProveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewConfirmarHabilitarProveedor(request));
    }

    private ModelAndView modalAndViewHabilitarProveedor(Request request) {
        int idProveedor = Integer.parseInt(request.params("id"));
        Proveedor proveedorAEditar = this.repoProveedor.buscar(idProveedor);
        proveedorAEditar.cambiarAHabilitado();
        this.repoProveedor.modificar(proveedorAEditar);
        modalAndViewController.getParametros().put("mensaje", "El proveedor "+proveedorAEditar.getRazonSocialProveedor()+" se habilitó correctamente.");
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

    private ModelAndView modalAndViewParaConfirmarEditarUnProveedor(Request request) {
        int idProveedor = Integer.parseInt(request.params("id"));
        Proveedor proveedorAEditar = this.repoProveedor.buscar(idProveedor);
        modalAndViewController.getParametros().put("id", proveedorAEditar.getId());
        modalAndViewController.getParametros().put("mensaje", "¿Está seguro de editar al proveedor "+proveedorAEditar.getRazonSocialProveedor()+"?");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativoConfirmarEditarUnProveedor.hbs");
    }

    public ModelAndView mostrarModalParaConfirmarEditarUnProveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewParaConfirmarEditarUnProveedor(request));
    }

    private void mostrarDatosVaciosDePaisSiNoLoTuviera(){
        modalAndViewController.getParametros().put("paisProveedor", "");
        modalAndViewController.getParametros().put("provinciaProveedor", "");
        modalAndViewController.getParametros().put("ciudadProveedor", "");
    }
    private void verificarSiTieneDireccionPostal(DireccionPostal direccionPostal){
        if (Objects.equals(direccionPostal, null)){
            mostrarDatosVaciosDePaisSiNoLoTuviera();
        }else{
            Pais paisProveedor = direccionPostal.getPais();
            Estado provinciaProveedor = direccionPostal.getProvincia();
            Ciudad ciudadProveedor = direccionPostal.getCiudad();
            if (Objects.equals(paisProveedor, null) || Objects.equals(provinciaProveedor, null) || Objects.equals(ciudadProveedor, null)){
                mostrarDatosVaciosDePaisSiNoLoTuviera();
            }else {
                modalAndViewController.getParametros().put("paisProveedor", paisProveedor.getName());
                modalAndViewController.getParametros().put("provinciaProveedor", provinciaProveedor.getName());
                modalAndViewController.getParametros().put("ciudadProveedor", ciudadProveedor.getName());
            }

        }

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

        verificarSiTieneDireccionPostal(direccionPostalproveedor);

        return new ModelAndView(modalAndViewController.getParametros(), "modalDatosAEditarDeUnProveedor.hbs");
    }

    public ModelAndView mostrarModalParaEditarUnProveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewEditarUnProveedor(request));
    }

    private ModelAndView modalAndViewConfirmarCambiosEnDatosDelProveedor(Request request) {
        int idProveedor = Integer.parseInt(request.params("id"));
        Proveedor proveedorAEditar = this.repoProveedor.buscar(idProveedor);
        modalAndViewController.getParametros().put("miId", proveedorAEditar.getId());
        modalAndViewController.getParametros().put("mensaje", "¿Está seguro de actualizar los datos del proveedor "+proveedorAEditar.getRazonSocialProveedor()+"?");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativoConfirmarActualizarDatosDelProveedor.hbs");
    }

    public ModelAndView mostrarModalParaConfirmarCambiosEnDatosDelProveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewConfirmarCambiosEnDatosDelProveedor(request));
    }

    private ModelAndView modalAndViewConfirmacionCambiosEnDatosDelProveedor(Request request) {
        String nombreActualizado = request.queryParams("nombre");
        String apellidoActualizado = request.queryParams("apellido");
        String razonSocialActualizado = request.queryParams("razonSocial");
        String cuit_cuilActualizado = request.queryParams("cuit_cuil");
        int idProveedor = Integer.parseInt(request.params("id"));
        Proveedor proveedorAEditar = this.repoProveedor.buscar(idProveedor);
        proveedorAEditar.actualizarCambiosEnMisAtributos(nombreActualizado, apellidoActualizado, razonSocialActualizado, cuit_cuilActualizado);
        repoProveedor.modificar(proveedorAEditar);
        modalAndViewController.getParametros().put("miId", proveedorAEditar.getId());
        modalAndViewController.getParametros().put("mensaje","Se actualizó correctamente los datos del proveedor "+proveedorAEditar.getRazonSocialProveedor());
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativoConfirmacionActualizacionDatosDelProveedor.hbs");
    }

    public ModelAndView mostrarModalConfirmacionDatosActualizadosDelProveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewConfirmacionCambiosEnDatosDelProveedor(request));
    }

    private ModelAndView modalAndViewParaConfirmarEditarDireccionUnProveedor(Request request) {
        int idProveedor = Integer.parseInt(request.params("id"));
        Proveedor proveedorAEditar = this.repoProveedor.buscar(idProveedor);
        modalAndViewController.getParametros().put("id", proveedorAEditar.getId());
        modalAndViewController.getParametros().put("mensaje", "¿Está seguro de editar la dirección del proveedor "+proveedorAEditar.getRazonSocialProveedor()+"?");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativoConfirmarEditarDireccionUnProveedor.hbs");
    }

    public ModelAndView mostrarModalParaConfirmarEditarDireccionProveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewParaConfirmarEditarDireccionUnProveedor(request));
    }

    private void verificarSitieneDireccion(DireccionPostal direccionPostal) {

        if (Objects.equals(direccionPostal, null)){
            modalAndViewController.getParametros().put("barrio", "");
            modalAndViewController.getParametros().put("calle", "");
            modalAndViewController.getParametros().put("numero", "");
            modalAndViewController.getParametros().put("departamento", "");
            modalAndViewController.getParametros().put("piso", "");
        }else {
            Direccion direccion = direccionPostal.getDireccion();
            modalAndViewController.getParametros().put("barrio", direccion.getBarrio());
            modalAndViewController.getParametros().put("calle", direccion.getCalle());
            modalAndViewController.getParametros().put("numero", direccion.getNumero());
            modalAndViewController.getParametros().put("departamento", direccion.getDpto());
            modalAndViewController.getParametros().put("piso", direccion.getPiso());
        }
    }

    private ModelAndView modalAndViewParaActualizarDireccionDeProveedor(Request request) {
        int idProveedor = Integer.parseInt(request.params("id"));
        Proveedor proveedorAEditar = this.repoProveedor.buscar(idProveedor);

        modalAndViewController.getParametros().put("idProveedor", proveedorAEditar.getId());

        List<Pais> listaPaisesApi = respoPaises.buscarTodos();
        List<Estado> listaProvinciasApi = repoProvincias.buscarTodos();
        List<Ciudad> listaCiudadesApi = repoCiudades.buscarTodos();
        modalAndViewController.getParametros().put("listaPaisesApi", listaPaisesApi);
        modalAndViewController.getParametros().put("listaProvinciasApi", listaProvinciasApi);
        modalAndViewController.getParametros().put("listaCiudadesApi", listaCiudadesApi);
        DireccionPostal direccionPostal = proveedorAEditar.getDireccionPostal();
        verificarSitieneDireccion(direccionPostal);
        return new ModelAndView(modalAndViewController.getParametros(), "modalCambiarDireccionProveedor.hbs");
    }

    public ModelAndView mostrarModalParaActualizarDireccionProveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewParaActualizarDireccionDeProveedor(request));
    }

    private ModelAndView modalAndViewConfirmarActualizarDireccionDeProveedor(Request request) {
        int idProveedor = Integer.parseInt(request.params("id"));
        Proveedor proveedorAEditar = this.repoProveedor.buscar(idProveedor);
        modalAndViewController.getParametros().put("id", proveedorAEditar.getId());
        modalAndViewController.getParametros().put("mensaje", "¿Está seguro de actualizar la dirección del proveedor "+proveedorAEditar.getRazonSocialProveedor()+"?");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativoConfirmarActualizarDireccionProveedor.hbs");
    }

    public ModelAndView mostrarModalParaConfirmarActualizarDireccionProveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewConfirmarActualizarDireccionDeProveedor(request));
    }

    private ModelAndView modalAndViewConfirmacionActualizadoDireccionDeProveedor(Request request) {
        int idProveedor = Integer.parseInt(request.params("id"));
        Proveedor proveedorAEditar = this.repoProveedor.buscar(idProveedor);
        modalAndViewController.getParametros().put("miId", proveedorAEditar.getId());

        String idPaisNuevaDireccion = request.queryParams("pais");
        String idProvinciaNuevaDireccion = request.queryParams("provincia");
        String idCiudadNuevaDireccion = request.queryParams("ciudad");
        String calleNuevaDireccion = request.queryParams("calle");
        String alturaStringNuevaDireccion = request.queryParams("altura");
        String pisoStringNuevaDireccion = request.queryParams("piso");
        String departamentoNuevaDireccion = request.queryParams("departamento");
        String barrioNuevaDireccion = request.queryParams("barrio");

        //En caso que el proveedor no tenga una direccion postal y se quiera agregar una
        //se evalua la no existencia de la misma y se realizan las validaciones para crearlo
        if (Objects.equals(proveedorAEditar.getDireccionPostal(), null)){

            if (operadorController.noSeleccioneUnaOpcion(idPaisNuevaDireccion)){
                modalAndViewController.getParametros().put("mensaje", "No seleccionó un país.");
                return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
            }

            if (operadorController.noSeleccioneUnaOpcion(idProvinciaNuevaDireccion)){
                modalAndViewController.getParametros().put("mensaje", "No seleccionó una provincia.");
                return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
            }

            if (operadorController.noSeleccioneUnaOpcion(idCiudadNuevaDireccion)){
                modalAndViewController.getParametros().put("mensaje", "No seleccionó una cuidad.");
                return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
            }
            if (operadorController.soyCadenaVaciaONula(calleNuevaDireccion)){
                modalAndViewController.getParametros().put("mensaje", "No ingresó el nombre de la calle.");
                return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
            }

            if (operadorController.soyCadenaVaciaONula(alturaStringNuevaDireccion)){
                modalAndViewController.getParametros().put("mensaje", "No ingresó la altura de la calle.");
                return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
            }
            DireccionPostal direccionPostal = operadorController.generarDireccionPostal(request);
            proveedorAEditar.asociarDireccionPostal(direccionPostal);
            repoProveedor.modificar(proveedorAEditar);
            modalAndViewController.getParametros().put("mensaje","Se actualizó correctamente la dirección del proveedor "+proveedorAEditar.getRazonSocialProveedor());
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativoConfirmacionActualizacionDireccionProveedor.hbs");
        }

        //En caso de que si tenga una dirección postal y se quiera modificar
        DireccionPostal direccionPostal = proveedorAEditar.getDireccionPostal();
        //evaluo si se cambió el pais, provincia y ciudad, caso contrario no realizará nada
        if (!operadorController.noSeleccioneUnaOpcion(idPaisNuevaDireccion)){
            Repositorio<Pais> repoPais =   FactoryRepositorio.get(Pais.class);
            Pais paisElegido = repoPais.buscar(idPaisNuevaDireccion);
            direccionPostal.setPais(paisElegido);
        }
        if (!operadorController.noSeleccioneUnaOpcion(idProvinciaNuevaDireccion)){
            Repositorio<Estado> repoEstado = FactoryRepositorio.get(Estado.class);
            Estado provinciaElegida = repoEstado.buscar(idProvinciaNuevaDireccion);
            direccionPostal.setProvincia(provinciaElegida);
        }
        if (!operadorController.noSeleccioneUnaOpcion(idCiudadNuevaDireccion)){
            Repositorio<Ciudad> repoCiudad = FactoryRepositorio.get(Ciudad.class);
            Ciudad ciudadElegida = repoCiudad.buscar(idCiudadNuevaDireccion);
            direccionPostal.setCiudad(ciudadElegida);
        }

        Direccion direccion = direccionPostal.getDireccion();
        int altura = Integer.parseInt(alturaStringNuevaDireccion);
        int piso = Integer.parseInt(pisoStringNuevaDireccion);
        direccion.modificarMisAtributos(barrioNuevaDireccion, calleNuevaDireccion, altura, piso, departamentoNuevaDireccion);
        repoProveedor.modificar(proveedorAEditar);
        modalAndViewController.getParametros().put("mensaje","Se actualizó correctamente la dirección del proveedor "+proveedorAEditar.getRazonSocialProveedor());
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativoConfirmacionActualizacionDireccionProveedor.hbs");
    }

    public ModelAndView mostrarConfirmacionDatosActualizadosDireccionProveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewConfirmacionActualizadoDireccionDeProveedor(request));
    }

    private ModelAndView modalAndViewConfirmarBajaProveedor(Request request) {
        int idProveedor = Integer.parseInt(request.params("id"));
        Proveedor proveedorAEditar = this.repoProveedor.buscar(idProveedor);
        modalAndViewController.getParametros().put("miId", proveedorAEditar.getId());
        modalAndViewController.getParametros().put("mensaje", "¿Está seguro de dar de baja al proveedor "+proveedorAEditar.getRazonSocialProveedor()+"?");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativoConfirmarDarDeBajaUnProveedor.hbs");

    }

    public ModelAndView mostrarModalParaConfirmarBajaDeUnProveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewConfirmarBajaProveedor(request));
    }

    private ModelAndView modalAndViewDarDeBajaProveedor(Request request) {
        int idProveedor = Integer.parseInt(request.params("id"));
        Proveedor proveedorAEditar = this.repoProveedor.buscar(idProveedor);
        proveedorAEditar.cambiarAInhabilitado();
        this.repoProveedor.modificar(proveedorAEditar);
        modalAndViewController.getParametros().put("mensaje", "Se dio de baja correctamente al proveedor "+proveedorAEditar.getRazonSocialProveedor()+".");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
    }

    public ModelAndView mostrarModalConfirmacionBajaProveedor(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewDarDeBajaProveedor(request));
    }

}
