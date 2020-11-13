package domain.controllers;

import apiEgresoIngreso.ServicioVinculacionEgresosIngresos;
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
import spark.utils.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

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

    private Map<String, Object> parametros;
    private Usuario usuario;

    private ContextoDeUsuarioLogueado contextoDeUsuarioLogueado;

    //-------------------------------------------------------------------------
                            //CONTRUCTOR
    //-------------------------------------------------------------------------

    public EntidadesController(ContextoDeUsuarioLogueado contextoDeUsuarioLogueado){
        this.repoTipoMedioPago = FactoryRepositorio.get(TipoMedioDePago.class);
        this.repoTipoDocComercial = FactoryRepositorio.get(TipoDocumentoComercial.class);
        this.repoOperacionEgreso = FactoryRepositorio.get(OperacionDeEgreso.class);
        this.repoOperacionIngreso = FactoryRepositorio.get(OperacionDeIngreso.class);
        this.repoMonedas = FactoryRepositorio.get(Moneda.class);
        this.repoPresupuesto = FactoryRepositorio.get(Presupuesto.class);
        this.repoCategoriaCriterio = FactoryRepositorio.get(CategoriaCriterio.class);
        this.repoCriterio = FactoryRepositorio.get(Criterio.class);
        this.repoProveedor = FactoryRepositorio.get(Proveedor.class);

        this.parametros = new HashMap<>();
        this.usuario = new Usuario();

        this.contextoDeUsuarioLogueado = contextoDeUsuarioLogueado;
    }

    //-------------------------------------------------------------------------
                            //METODOS DE INICIO
    //-------------------------------------------------------------------------

    private ModelAndView siElUsuarioEstaLogueadoRealiza(Request request, Supplier<ModelAndView> bloque){

        if(!contextoDeUsuarioLogueado.esValidoElUsuarioLogueadoEn(request)){
            return new ModelAndView(null,"error404.hbs");
        }

        return bloque.get();
    }

    private void cargarParametosHashMap() throws Exception {
        usuario = contextoDeUsuarioLogueado.getUsuarioLogueado();
        parametros.put("nombre", usuario.getNombre());
        parametros.put("apellido", usuario.getApellido());
    }

    //-------------------------------------------------------------------------
                        //METODOS CARGA DE PAGINAS - GET
    //-------------------------------------------------------------------------

    public ModelAndView principal(Request request, Response response) throws Exception {

        cargarParametosHashMap();
        return siElUsuarioEstaLogueadoRealiza(request, () -> new ModelAndView(parametros, "inicioEstandar.hbs"));
    }

    private ModelAndView modalAndViewIngresos() {

        List<Moneda> monedas = this.repoMonedas.buscarTodos();
        parametros.put("monedas", monedas);

        return new ModelAndView(parametros, "ingresos.hbs");
    }

    public ModelAndView ingresos(Request request, Response response) throws Exception {

        cargarParametosHashMap();
        return siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewIngresos());
    }

    private ModelAndView modalAndViewEgresos(){

        List<TipoMedioDePago> tiposMediosPago = this.repoTipoMedioPago.buscarTodos();
        List<TipoDocumentoComercial> tiposDocumentoComercial = this.repoTipoDocComercial.buscarTodos();
        List<Proveedor> proveedores = this.repoProveedor.buscarTodos();
        List<Criterio> criterios = this.repoCriterio.buscarTodos();
        List<Criterio> criterios2 = quitarMitad(criterios);

        parametros.put("tiposMediosDePago", tiposMediosPago);
        parametros.put("tiposDocumentoComercial", tiposDocumentoComercial);
        parametros.put("proveedores", proveedores);
        parametros.put("criterios", criterios);
        parametros.put("criterios2", criterios2);
        return new ModelAndView(parametros, "egresos.hbs");
    }
    public ModelAndView egresos(Request request, Response response)throws Exception {

        cargarParametosHashMap();
        return siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewEgresos());

    }

    private ModelAndView modalAndViewPresupuestos(){

        List<OperacionDeEgreso> operacionesEgreso = this.repoOperacionEgreso.buscarTodos();
        List<TipoDocumentoComercial> tiposDocumentoComercial = this.repoTipoDocComercial.buscarTodos();
        List<Criterio> criterios = this.repoCriterio.buscarTodos();
        List<Criterio> criterios2 = quitarMitad(criterios);

        parametros.put("operacionesEgreso", operacionesEgreso);
        parametros.put("tiposDocumentoComercial", tiposDocumentoComercial);
        parametros.put("criterios", criterios);
        parametros.put("criterios2", criterios2);

        return new ModelAndView(parametros, "presupuestos.hbs");
    }

    public ModelAndView presupuestos(Request request, Response response)throws Exception {

        cargarParametosHashMap();
        return siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewPresupuestos());

    }

    private ModelAndView modalAndViewCriterios(){

        return new ModelAndView(parametros, "criterios.hbs");
    }

    public ModelAndView criterios(Request request, Response response)throws Exception {

        cargarParametosHashMap();
        return siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewCriterios());
    }

    private ModelAndView modalAndViewListadoOperaciones(){

        List<OperacionDeEgreso> operacionesEgreso = this.repoOperacionEgreso.buscarTodos();
        List<OperacionDeIngreso> operacionesIngreso = this.repoOperacionIngreso.buscarTodos();

        parametros.put("operacionesEgreso", operacionesEgreso);
        parametros.put("operacionesIngreso", operacionesIngreso);

        return new ModelAndView(parametros, "listadoOperaciones.hbs");
    }

    public ModelAndView listadoOperaciones(Request request, Response response)throws Exception {

        cargarParametosHashMap();
        return siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewListadoOperaciones());

    }

    private ModelAndView modalAndViewAsociarOperacion(){

        return new ModelAndView(parametros, "asociarOperacion.hbs");
    }

    public ModelAndView asociarOperacion(Request request, Response response)throws Exception {

        cargarParametosHashMap();
        return siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewAsociarOperacion());
    }

    private ModelAndView modalAndViewMensajes(){
        return new ModelAndView(parametros, "mensaje.hbs");
    }

    public ModelAndView mensajes(Request request, Response response)throws Exception {

        cargarParametosHashMap();
        return siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewMensajes());
    }

    //-------------------------------------------------------------------------
                            //PERSISTENCIA DE DATOS - POST
    //-------------------------------------------------------------------------

    public ModelAndView guardarOperacionDeIngreso(Request request, Response response){

        String fechaString = request.queryParams("fecha");
        String periodoDeAceptacion = request.queryParams("periodoDeAceptacion");
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

        //se instancia una operacion de ingreso a persistir
        OperacionDeIngreso operacionDeIngresoAGuardar = new OperacionDeIngreso(descripcion, monto, fecha, monedaElegida);
        operacionDeIngresoAGuardar.setEntidadJuridicaAsociada(usuario.getEntidadJuridica());

        if (!validarPersistencia(repoOperacionIngreso, operacionDeIngresoAGuardar)){
            model.put("mensaje", "No se guardaron los datos, intentelo nuevamente.");
            return new ModelAndView(model, "modalInformativo2.hbs");
        }

        model.put("mensaje","Los datos se guardaron correctamente.");
        return new ModelAndView(model,"modalInformativo2.hbs");
    }

    public ModelAndView guardarOperacionDeEgreso(Request request, Response response) throws Exception {
        // Leo los query params
        String fechaString = request.queryParams("fecha");
        String montoTotalString = request.queryParams("montoTotal");

        String tipoMedioDePagoString = request.queryParams("medioDePago");
        String numeroMedioDePagoString = request.queryParams("numeroMedioDePago");
        String tipoDocumentoComercialString = request.queryParams("documentoComercial");
        String numeroDocumentoComercialString = request.queryParams("numeroDocumentoComercial");

        String presupuestosRequeridosString = request.queryParams("presupuestosRequeridos");
        String razonSocialProveedor = request.queryParams("proveedor");

        if (noEligioMedioPago(tipoMedioDePagoString) || noEligioDocumentoComercial(tipoDocumentoComercialString)){
            // No se inserto metodo de pago o documento comercial
            parametros.put("mensaje","No se inserto metodo de pago o documento comercial");
            return new ModelAndView(parametros,"modalInformativo2.hbs");
        }

        // Leo lista de categorias
        List<CategoriaCriterio> categoriasCriterio = obtenerListaCategoriaCriterio(request);
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

        Usuario miUsuario = contextoDeUsuarioLogueado.getUsuarioLogueado();
        EntidadJuridica entidadJuridica = miUsuario.getEntidadJuridica();

        // Setters necesarios
        operacionAGuardar.setEntidadJuridicaAsociada(entidadJuridica);
        operacionAGuardar.setUsuario(miUsuario);
        operacionAGuardar.setMedioDePago(medioDePago);
        operacionAGuardar.setDocumentoComercial(documentoComercial);
        operacionAGuardar.setCantidadPresupuestosRequerida(presupuestosRequeridos);
        operacionAGuardar.setItems(listaItems);
        operacionAGuardar.setProveedorAsociado(proveedor);
        operacionAGuardar.setListaCategoriaCriterio(categoriasCriterio);

        if (!validarPersistencia(repoOperacionEgreso, operacionAGuardar)){
            parametros.put("mensaje", "Se produjo un erroe al gradar los datos.");
            return new ModelAndView(parametros,"modalInformativo2.hbs");
        }

        // Se persistio correctamente
        parametros.put("mensaje", "Se guardaron los datos correctamente");
        return new ModelAndView(parametros,"modalInformativo2.hbs");
    }

    public ModelAndView guardarPresupuesto(Request request, Response response){
        // Leo query params
        String montoTotalString = request.queryParams("montoTotal");
        String tipoDocumentoComercialString = request.queryParams("documentoComercial");
        String numeroDocumentoComercialString = request.queryParams("numeroDocumentoComercial");
        String operacionEgresoString = request.queryParams("operacionEgreso");

        if (noEligioOperacionEgreso(operacionEgresoString)){
            parametros.put("mensaje", "Se debe asociar con una operacion de egreso.");
            return new ModelAndView(parametros, "modalInformativo2.hbs");
        }

        if (noEligioDocumentoComercial(tipoDocumentoComercialString)){
            // No se inserto documento comercial
            parametros.put("mensaje", "No se inserto documento comercial.");
            return new ModelAndView(parametros, "modalInformativo2.hbs");
        }

        // Leo operacion de egreso asociada
        OperacionDeEgreso operacionEgresoAsociada = buscarOperacionEgreso(operacionEgresoString);

        // Leo todos los items
        List<Item> listaItems = obtenerListaItems(request);

        if (operacionEgresoAsociada == null){
            parametros.put("mensaje", "Error al leer la operacion de egreso.");
            return new ModelAndView(parametros, "modalInformativo2.hbs");
        }

        if (!listasDeItemsIguales(listaItems, operacionEgresoAsociada.getItems())){
            // No se inserto documento comercial
            parametros.put("mensaje", "Los items del presupuesto deben ser iguales a los del egreso.");
            return new ModelAndView(parametros, "modalInformativo2.hbs");
        }

        // TODO | Problema, se crea una copia de los items cuando realmente presupuesto
        //  y egreso deberian referenciar los mismositems
        // listaItems = operacionEgresoAsociada.getItems();

        // Leo las categorias
        List<CategoriaCriterio> categoriasCriterio = obtenerListaCategoriaCriterio(request);

        // Convierto los numeros
        float montoTotal = Float.parseFloat(montoTotalString);
        int numeroDocumentoComercial = Integer.parseInt(numeroDocumentoComercialString);

        // Genero tipoDocComercial
        TipoDocumentoComercial tipoDocComercial = buscarTipoDocComercial(tipoDocumentoComercialString);
        // Genero documento comercial
        DocumentoComercial documentoComercial = new DocumentoComercial(tipoDocComercial, numeroDocumentoComercial);

        Presupuesto presupuestoAGuardar = new Presupuesto();

        presupuestoAGuardar.setMontoTotal(montoTotal);
        presupuestoAGuardar.setDocumentoComercial(documentoComercial);
        presupuestoAGuardar.setListaCategoriaCriterio(categoriasCriterio);
        presupuestoAGuardar.setItems(listaItems);
        presupuestoAGuardar.setOperacionAsociada(operacionEgresoAsociada);

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

    public ModelAndView ejecutarVinculacion(Request request, Response response) throws IOException {

        List<String> criterios = obtenerListaCriteriosVinculacion(request);

        List<OperacionDeEgreso> operacionesEgreso = this.repoOperacionEgreso.buscarTodos();
        List<OperacionDeIngreso> operacionesIngreso = this.repoOperacionIngreso.buscarTodos();

        ServicioVinculacionEgresosIngresos servicioVinculacionEgresosIngresos = ServicioVinculacionEgresosIngresos.instancia();

        List<OperacionDeIngreso> ingresosVinculados;

        try{
            ingresosVinculados = servicioVinculacionEgresosIngresos.ejecutarVinculacion(operacionesEgreso, operacionesIngreso, criterios);
        }catch (Exception e) {
            String mensajeError = e.getMessage();
            System.out.println("EXCEPCION: " + mensajeError);
            // Hubo un error
            parametros.put("mensaje", "Se produjo un error al realizar la vinculacion.");
            return new ModelAndView(parametros,"modalInformativo2.hbs");
        }

        for (OperacionDeIngreso unaOperacion : ingresosVinculados){
            this.repoOperacionIngreso.modificar(unaOperacion);
        }

        // Se persistio correctamente
        parametros.put("mensaje", "Se ejecuto la vinculacion correctamente");
        return new ModelAndView(parametros,"modalInformativo2.hbs");
    }

    // --- FUNCIONES AUXILIARES ---

    private boolean listasDeItemsIguales(List<Item> itemsPresupuesto, List<Item> itemsEgreso){

        if (itemsPresupuesto.size() != itemsEgreso.size()){
            return false;
        }

        List<Item> copiaItemsEgreso = new ArrayList<>(itemsEgreso);

        for (Item unItemPresupuesto : itemsPresupuesto) {
            if (!encontrarYEliminarDeLista(unItemPresupuesto, copiaItemsEgreso)){
                return false;
            }
        }

        return true;
    }

    private boolean encontrarYEliminarDeLista(Item itemPresupuesto, List<Item> itemsEgreso){
        for (Item unItemEgreso : itemsEgreso) {
            if (unItemEgreso.soyIgualA(itemPresupuesto)){
                itemsEgreso.remove(unItemEgreso);
                return true;
            }
        }
        return false;
    }

    // Leer la lista de la ventana criterios
    private List<CategoriaCriterio> obtenerYGenerarListaCategoriasCriterio(Request request){
        List<CategoriaCriterio> categorias = new ArrayList<>();

        String nombreCategoria;

        for(int i = 0 ; i < 30;i++){

            if ((nombreCategoria = request.queryParams("nombre_I[" + i + "]") ) != null) {
                CategoriaCriterio categoria = new CategoriaCriterio(nombreCategoria, null);

                categorias.add(categoria);
            }
        }

        return categorias;
    }

    private boolean noEligioMedioPago(String medioPagoString){
        return medioPagoString.equals("Seleccionar medio de pago");
    }
    private boolean noEligioDocumentoComercial(String documentoComercialString){
        return documentoComercialString.equals("Seleccionar documento comercial");
    }

    private boolean noEligioOperacionEgreso(String operacionEgresoString){
        return operacionEgresoString.equals("-Seleccionar una operacion de egreso-");
    }

    // Obtener lista al insertar egreso,presupuesto
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

    private List<String> obtenerListaCriteriosVinculacion(Request request){

        List<String> listaADevolver = new ArrayList<>();

        for(int i = 0; i<3;i++){
            String criterioLeido = request.queryParams(Integer.toString(i));

            if (criterioLeido != null){
                listaADevolver.add(criterioLeido);
            }
        }

        if (listaADevolver.size() > 1){
            listaADevolver.add(0, "mix");
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

    private OperacionDeEgreso buscarOperacionEgreso(String idMasFecha){

        // Recorto el ID del string
        int indexDosPuntos = idMasFecha.indexOf(":");
        int indexPipe = idMasFecha.indexOf("|");
        String idString = idMasFecha.substring(indexDosPuntos + 2 , indexPipe - 1);

        int idOperacionEgresoBuscado = Integer.parseInt(idString);

        List<OperacionDeEgreso> operacionesEgreso = this.repoOperacionEgreso.buscarTodos();

        for ( OperacionDeEgreso unaOperacion : operacionesEgreso ) {
            if (unaOperacion.getIdOperacion() == idOperacionEgresoBuscado){
                return unaOperacion;
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
        return StringUtils.isEmpty(cadena);
    }

    private boolean validarPersistencia(Repositorio<?> objetoFactory, Object objetoClase){
        try {
            objetoFactory.agregar(objetoClase);
        }catch (Exception e){
            System.out.println("EXCEPCION: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Quita la mitad de la lista y genera otra
    private List<Criterio> quitarMitad(List<Criterio> lista){
        List<Criterio> listaADevolver = new ArrayList<>();

        int listaSize = lista.size();

        int limite;
        if (listaSize % 2 == 0){
            // Caso par: listaSize / 2 - 1
            limite = listaSize / 2 - 1;
        } else {
            // Caso impar: listaSize / 2
            limite = listaSize / 2;
        }

        // Recorro la lista
        for (int i = listaSize - 1; i > limite;i--){
            Criterio miCriterio = lista.remove(i);

            listaADevolver.add(miCriterio);
        }
        return listaADevolver;
    }
}
