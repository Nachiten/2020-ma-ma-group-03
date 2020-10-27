package domain.controllers;

import criterioOperacion.CategoriaCriterio;
import criterioOperacion.Criterio;
import domain.entities.apiMercadoLibre.Moneda;
import domain.entities.entidades.EntidadJuridica;
import domain.entities.operaciones.*;
import domain.entities.usuarios.Usuario;
import domain.entities.vendedor.Proveedor;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntidadesController {

    private Repositorio<OperacionDeEgreso> repoOperacionEgreso;
    private Repositorio<TipoMedioDePago> repoTipoMedioPago;
    private Repositorio<TipoDocumentoComercial> repoTipoDocComercial;
    private Repositorio<Moneda> repoMonedas;
    private Repositorio<OperacionDeIngreso> repoOperacionIngreso;
    private Repositorio<Presupuesto> repoPresupuesto;
    private Repositorio<CategoriaCriterio> repoCategoriaCriterio;
    private Repositorio<Criterio> repoCriterio;
    private Repositorio<Proveedor> repoProveedor;

    private AdministradorDeSesion administradorDeSesion;
    private UsuarioController unUsuarioController;

    private Map<String, Object> parametros;
    private Usuario usuario;

    public EntidadesController(){
        this.repoTipoMedioPago = FactoryRepositorio.get(TipoMedioDePago.class);
        this.repoTipoDocComercial = FactoryRepositorio.get(TipoDocumentoComercial.class);
        this.repoOperacionEgreso = FactoryRepositorio.get(OperacionDeEgreso.class);
        this.repoOperacionIngreso = FactoryRepositorio.get(OperacionDeIngreso.class);
        this.repoMonedas = FactoryRepositorio.get(Moneda.class);
        this.repoPresupuesto = FactoryRepositorio.get(Presupuesto.class);
        this.repoCategoriaCriterio = FactoryRepositorio.get(CategoriaCriterio.class);
        this.repoCriterio = FactoryRepositorio.get(Criterio.class);
        this.repoProveedor = FactoryRepositorio.get(Proveedor.class);

        this.administradorDeSesion = new AdministradorDeSesion();
        this.unUsuarioController = new UsuarioController();

        this.parametros = new HashMap<>();
        this.usuario = new Usuario();
    }

    private void obtenerUsuarioDeId(Request request){
        int id = administradorDeSesion.obtenerIdDeSesion(request);
        // ----
        usuario = this.unUsuarioController.getRepoUsuarios().buscar(id);
        parametros.put("nombre", usuario.getNombre());
        parametros.put("apellido", usuario.getApellido());
        parametros.put("id", usuario.getId());
        parametros.put("bandejaDeMensajes", usuario.getBandejaDeMensajes());
    }
    // --- GETs ---

    public ModelAndView ingresos(Request request, Response response) {

        obtenerUsuarioDeId(request);
        List<Moneda> monedas = this.repoMonedas.buscarTodos();
        parametros.put("monedas", monedas);

        return new ModelAndView(parametros, "ingresos.hbs");
    }

    public ModelAndView principal(Request request, Response response){
        obtenerUsuarioDeId(request);

        return new ModelAndView(parametros, "inicio.hbs");
    }

    public ModelAndView egresos(Request request, Response response) {

        obtenerUsuarioDeId(request);

        List<TipoMedioDePago> tiposMediosPago = this.repoTipoMedioPago.buscarTodos();
        List<TipoDocumentoComercial> tiposDocumentoComercial = this.repoTipoDocComercial.buscarTodos();
        List<Proveedor> proveedores = this.repoProveedor.buscarTodos();

        parametros.put("proveedores", proveedores);
        parametros.put("tiposMediosDePago", tiposMediosPago);
        parametros.put("tiposDocumentoComercial", tiposDocumentoComercial);

        return new ModelAndView(parametros, "egresos.hbs");
    }

    public ModelAndView presupuestos(Request request, Response response) {

        obtenerUsuarioDeId(request);

        List<TipoDocumentoComercial> tiposDocumentoComercial = this.repoTipoDocComercial.buscarTodos();
        List<CategoriaCriterio> categoriasCriterio = this.repoCategoriaCriterio.buscarTodos();

        parametros.put("tiposDocumentoComercial", tiposDocumentoComercial);
        parametros.put("categoriasCriterio", categoriasCriterio);

        return new ModelAndView(parametros, "presupuestos.hbs");
    }

    public ModelAndView criterios(Request request, Response response) {
        obtenerUsuarioDeId(request);
        return new ModelAndView(parametros, "criterios.hbs");
    }

    public ModelAndView listadoOperaciones(Request request, Response response) {
        obtenerUsuarioDeId(request);

        List<OperacionDeEgreso> operacionesEgreso = this.repoOperacionEgreso.buscarTodos();
        List<OperacionDeIngreso> operacionesIngreso = this.repoOperacionIngreso.buscarTodos();

        parametros.put("operacionesEgreso", operacionesEgreso);
        parametros.put("operacionesIngreso", operacionesIngreso);

        return new ModelAndView(parametros, "listadoOperaciones.hbs");
    }

    public ModelAndView asociarOperacion(Request request, Response response) {
        obtenerUsuarioDeId(request);
        return new ModelAndView(parametros, "asociarOperacion.hbs");
    }

    public ModelAndView mensajes(Request request, Response response) {
        obtenerUsuarioDeId(request);
        return new ModelAndView(parametros, "mensaje.hbs");
    }

    // --- POSTs ---

    public ModelAndView guardarOperacionDeEgreso(Request request, Response response){
        // Leo los query params
        String fechaString = request.queryParams("fecha");
        String montoTotalString = request.queryParams("montoTotal");

        String tipoMedioDePagoString = request.queryParams("medioDePago");
        String tipoDocumentoComercialString = request.queryParams("documentoComercial");
        String presupuestosRequeridosString = request.queryParams("presupuestosRequeridos");
        String razonSocialProveedor = request.queryParams("proveedor");

        String numeroMedioDePagoString = request.queryParams("numeroMedioDePago");
        String numeroDocumentoComercialString = request.queryParams("numeroDocumentoComercial");

        if (noEligioMedioPago(tipoMedioDePagoString) || noEligioDocumentoComercial(tipoDocumentoComercialString)){
            // No se inserto metodo de pago o documento comercial
            parametros.put("mensaje","No se inserto metodo de pago o documento comercial");
            return new ModelAndView(parametros,"modalInformativo2.hbs");
        }

        // Leo todos los items
        List<Item> listaItems = obtenerListaItems(request);

        // Convierto de string a LocalDate
        LocalDate fecha = convertirAFecha(fechaString);
        // Convierto de string a float
        float montoTotal = Float.parseFloat(montoTotalString);
        // Convierto de string a int
        int presupuestosRequeridos = Integer.parseInt(presupuestosRequeridosString);
        int numeroMedioDePago = Integer.parseInt(numeroMedioDePagoString);
        int numeroDocumentoComercial = Integer.parseInt(numeroDocumentoComercialString);

        // Busco proveedor
        Proveedor proveedor = buscarProveedor(razonSocialProveedor);
        // Genero tipo medio pago y tipoDocComercial
        TipoMedioDePago tipoMedioPago = buscarTipoMedioPago(tipoMedioDePagoString);
        TipoDocumentoComercial tipoDocComercial = buscarTipoDocComercial(tipoDocumentoComercialString);

        // Genero medio pago y documento comercial
        MedioDePago medioDePago = new MedioDePago(tipoMedioPago, numeroMedioDePago);
        DocumentoComercial documentoComercial = new DocumentoComercial(tipoDocComercial, numeroDocumentoComercial);

        // Genero operacion de egreso
        OperacionDeEgreso operacionAGuardar = new OperacionDeEgreso(fecha, montoTotal);

        int id = administradorDeSesion.obtenerIdDeSesion(request);
        Usuario miUsuario = this.unUsuarioController.getRepoUsuarios().buscar(id);

        EntidadJuridica entidadJuridica = miUsuario.getEntidadJuridica();

        // Setters necesarios
        operacionAGuardar.setEntidadJuridicaAsociada(entidadJuridica);
        operacionAGuardar.setUsuario(miUsuario);
        operacionAGuardar.setMedioDePago(medioDePago);
        operacionAGuardar.setDocumentoComercial(documentoComercial);
        operacionAGuardar.setCantidadPresupuestosRequerida(presupuestosRequeridos);
        operacionAGuardar.setItems(listaItems);
        operacionAGuardar.setProveedorAsociado(proveedor);

        try{
            // Persistir operacion
            repoOperacionEgreso.agregar(operacionAGuardar);
        }catch (Exception e) {
            String mensajeError = e.getMessage();
            System.out.println("EXCEPCION: " + mensajeError);
            // Hubo un error
            parametros.put("mensaje", "Se produjo un erroe al gradar los datos.");
            return new ModelAndView(parametros,"modalInformativo2.hbs");
        }

        // Se persistio correctamente
        parametros.put("mensaje", "Se guardaron los datos correctamente");
        return new ModelAndView(parametros,"modalInformativo2.hbs");
    }

    public ModelAndView guardarOperacionDeIngreso(Request request, Response response){

        obtenerUsuarioDeId(request);

        String fechaString = request.queryParams("fecha");
        String montoString = request.queryParams("monto");
        String monedaString = request.queryParams("moneda");
        String descripcion = request.queryParams("descripcion");

        Map<String, Object> model = new HashMap<>();

        // No se eligio una moneda
        if (validarVacio(monedaString)){
            model.put("mensaje","No seleccionaste una moneda.");
            return new ModelAndView(model,"modalInformativo2.hbs");
        }

        //se convierte el string fecha a formato fecha
        LocalDate fecha = convertirAFecha(fechaString);
        float monto = Float.parseFloat(montoString);

        //se convierte el string moneda a tipo moneda
        Moneda monedaElegida = buscarMoneda(monedaString);

        EntidadJuridica unaEntidadJuridica = usuario.getEntidadJuridica();

        //se instancia una operacion de ingreso a persistir
        OperacionDeIngreso operacionAGuardar = new OperacionDeIngreso(descripcion, monto, fecha, monedaElegida);
        operacionAGuardar.setEntidadJuridicaAsociada(unaEntidadJuridica);

        if (!validarPersistencia(repoOperacionIngreso, operacionAGuardar)){
            model.put("mensaje", "No se guardaron los datos correctamente, intentelo nuevamente.");
            return new ModelAndView(model, "modalInformativo2.hbs");
        }

        model.put("mensaje","Los datos se guardaron correctamente.");
        return new ModelAndView(model,"modalInformativo2.hbs");
    }

    public ModelAndView guardarPresupuesto(Request request, Response response){
        // Leo query params
        String montoTotalString = request.queryParams("montoTotal");
        String tipoDocumentoComercialString = request.queryParams("documentoComercial");
        String numeroDocumentoComercialString = request.queryParams("numeroDocumentoComercial");

        if (noEligioDocumentoComercial(tipoDocumentoComercialString)){
            // No se inserto documento comercial
            parametros.put("mensaje", "No se inserto documento comercial.");
            return new ModelAndView(parametros, "modalInformativo2.hbs");
        }

        List<CategoriaCriterio> categoriasCriterio = obtenerListaCategoriaCriterio(request);

        // Convierto los numeros
        float montoTotal = Float.parseFloat(montoTotalString);
        int numeroDocumentoComercial = Integer.parseInt(numeroDocumentoComercialString);

        // Leo todos los items
        List<Item> listaItems = obtenerListaItems(request);

        // Genero tipoDocComercial
        TipoDocumentoComercial tipoDocComercial = buscarTipoDocComercial(tipoDocumentoComercialString);
        // Genero documento comercial
        DocumentoComercial documentoComercial = new DocumentoComercial(tipoDocComercial, numeroDocumentoComercial);

        Presupuesto presupuestoAGuardar = new Presupuesto();

        presupuestoAGuardar.setMontoTotal(montoTotal);
        presupuestoAGuardar.setDocumentoComercial(documentoComercial);
        presupuestoAGuardar.setListaCategoriaCriterio(categoriasCriterio);
        presupuestoAGuardar.setItems(listaItems);

        if (!validarPersistencia(repoPresupuesto,presupuestoAGuardar)){
            parametros.put("mensaje", "No se guardaron los datos correctamente, intentelo nuevamente.");
            return new ModelAndView(parametros, "modalInformativo2.hbs");
        }

        // Se persisitio correctamente
        parametros.put("mensaje","Los datos se guardaron correctamente.");
        return new ModelAndView(parametros,"modalInformativo2.hbs");
    }

    public ModelAndView guardarCriterio(Request request, Response response){
        String nombreCriterio = request.queryParams("nombreCriterio");

        List<CategoriaCriterio> listaCategoriasCriterio = obtenerYGenerarListaCategoriasCriterio(request);

        Criterio criterioAGuardar = new Criterio(nombreCriterio, listaCategoriasCriterio);

        if (!validarPersistencia(repoCriterio,criterioAGuardar)){
            parametros.put("mensaje", "No se guardaron los datos correctamente, intentelo nuevamente.");
            return new ModelAndView(parametros, "modalInformativo2.hbs");
        }

        // Se persisitio correctamente
        parametros.put("mensaje","Los datos se guardaron correctamente.");
        return new ModelAndView(parametros,"modalInformativo2.hbs");
    }

    private List<CategoriaCriterio> obtenerYGenerarListaCategoriasCriterio(Request request){
        List<CategoriaCriterio> categorias = new ArrayList<>();

        String nombreCategoria;

        for(int i = 0 ; i<30;i++){

            if ((nombreCategoria = request.queryParams("nombre_I[" + i + "]") ) != null) {
                CategoriaCriterio categoria = new CategoriaCriterio(nombreCategoria, null);

                categorias.add(categoria);
            }
        }

        return categorias;
    }

    // --- FUNCIONES AUXILIARES ---

    private boolean noEligioMedioPago(String medioPagoString){
        return medioPagoString.equals("Seleccionar medio de pago");
    }
    private boolean noEligioDocumentoComercial(String documentoComercialString){
        return documentoComercialString.equals("Seleccionar documento comercial");
    }

    private List<CategoriaCriterio> obtenerListaCategoriaCriterio(Request request){

        List<CategoriaCriterio> listaADevolver = new ArrayList<>();

        List<CategoriaCriterio> categoriasCriterioTotales = this.repoCategoriaCriterio.buscarTodos();

        for(CategoriaCriterio unaCategoriaCriterio : categoriasCriterioTotales){
            String categoriCriterioLeidaNombre = request.queryParams(unaCategoriaCriterio.getNombreCategoria());

            if (categoriCriterioLeidaNombre != null){
                listaADevolver.add(unaCategoriaCriterio);
            }
        }

        return listaADevolver;
    }

    private List<Item> obtenerListaItems(Request request){
        List<Item> items = new ArrayList<>();

        String itemNombre;
        String itemPrecioString;

        for (int i = 0; i <30; i++){

            if ((itemNombre = request.queryParams("nombre_I[" + i + "]") ) != null){
                itemPrecioString = request.queryParams("precio_I[" + i + "]");
                float itemPrecio = Float.parseFloat(itemPrecioString);

                Item miItem = new Item(itemNombre, itemPrecio);

                items.add(miItem);
            }

        }

        return items;
    }

    private LocalDate convertirAFecha(String fechaString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(fechaString, formatter);
    }

    private TipoMedioDePago buscarTipoMedioPago(String nombre){
        List<TipoMedioDePago> tiposMediosPago = this.repoTipoMedioPago.buscarTodos();

        for ( TipoMedioDePago unTipoMedioPago : tiposMediosPago ) {
            if (unTipoMedioPago.getTipoPago().equals(nombre)){
                return unTipoMedioPago;
            }
        }
        return null;
    }

    private Proveedor buscarProveedor(String razonSocial){
        List<Proveedor> proveedores = this.repoProveedor.buscarTodos();

        for ( Proveedor unProveedor : proveedores ) {
            if (unProveedor.getRazonSocial().equals(razonSocial)){
                return unProveedor;
            }
        }
        return null;
    }

    private Moneda buscarMoneda(String valorIngresado){
        List<Moneda> monedas = this.repoMonedas.buscarTodos();

        String idMoneda = valorIngresado.substring(0, 3);

        for (Moneda unaMoneda : monedas){
            if (unaMoneda.getId().equals(idMoneda)){
                return unaMoneda;
            }
        }
        return null;
    }

    private TipoDocumentoComercial buscarTipoDocComercial(String nombre){
        List<TipoDocumentoComercial> tiposDocumentoComercial = this.repoTipoDocComercial.buscarTodos();

        for ( TipoDocumentoComercial unTipoDocComercial : tiposDocumentoComercial ) {
            if (unTipoDocComercial.getNombre().equals(nombre)){
                return unTipoDocComercial;
            }
        }
        return null;
    }

    private boolean validarVacio(String cadena){
        return cadena == null || cadena.trim().isEmpty();
    }

    private boolean validarPersistencia(Repositorio<?> objetoFactory, Object objetoClase){
        try {
            objetoFactory.agregar(objetoClase);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
