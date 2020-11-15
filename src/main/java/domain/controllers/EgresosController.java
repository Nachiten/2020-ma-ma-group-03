package domain.controllers;

import criterioOperacion.CategoriaCriterio;
import criterioOperacion.Criterio;
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
import java.util.List;

public class EgresosController {

    private Repositorio<OperacionDeEgreso> repoOperacionEgreso;
    private Repositorio<TipoMedioDePago> repoTipoMedioPago;
    private Repositorio<TipoDocumentoComercial> repoTipoDocComercial;
    private Repositorio<CategoriaCriterio> repoCategoriaCriterio;
    private Repositorio<Criterio> repoCriterio;
    private Repositorio<Proveedor> repoProveedor;
    private ModalAndViewController modalAndViewController;

    public EgresosController(ModalAndViewController modalAndViewController){

        this.repoTipoMedioPago = FactoryRepositorio.get(TipoMedioDePago.class);
        this.repoTipoDocComercial = FactoryRepositorio.get(TipoDocumentoComercial.class);
        this.repoOperacionEgreso = FactoryRepositorio.get(OperacionDeEgreso.class);
        this.repoCategoriaCriterio = FactoryRepositorio.get(CategoriaCriterio.class);
        this.repoProveedor = FactoryRepositorio.get(Proveedor.class);
        this.repoCriterio = FactoryRepositorio.get(Criterio.class);
        this.modalAndViewController = modalAndViewController;
    }

    private ModelAndView modalAndViewEgresos(){

        List<TipoMedioDePago> tiposMediosPago = this.repoTipoMedioPago.buscarTodos();
        List<TipoDocumentoComercial> tiposDocumentoComercial = this.repoTipoDocComercial.buscarTodos();
        List<Proveedor> proveedores = this.repoProveedor.buscarTodos();
        List<Criterio> criterios = this.repoCriterio.buscarTodos();
        List<Criterio> criterios2 = quitarMitad(criterios);

        modalAndViewController.getParametros().put("tiposMediosDePago", tiposMediosPago);
        modalAndViewController.getParametros().put("tiposDocumentoComercial", tiposDocumentoComercial);
        modalAndViewController.getParametros().put("proveedores", proveedores);
        modalAndViewController.getParametros().put("criterios", criterios);
        modalAndViewController.getParametros().put("criterios2", criterios2);
        return new ModelAndView(modalAndViewController.getParametros(), "egresos.hbs");
    }
    public ModelAndView egresos(Request request, Response response)throws Exception {

        modalAndViewController.cargarParametosHashMap();
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewEgresos());
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
            modalAndViewController.getParametros().put("mensaje","No se inserto metodo de pago o documento comercial");
            return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");
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

        //Usuario miUsuario = contextoDeUsuarioLogueado.getUsuarioLogueado();
        Usuario miUsuario = modalAndViewController.getUsuario();
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
            modalAndViewController.getParametros().put("mensaje", "Se produjo un erroe al gradar los datos.");
            return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");
        }

        // Se persistio correctamente
        modalAndViewController.getParametros().put("mensaje", "Se guardaron los datos correctamente");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");
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

    private boolean noEligioMedioPago(String medioPagoString){
        return medioPagoString.equals("Seleccionar medio de pago");
    }
    private boolean noEligioDocumentoComercial(String documentoComercialString){
        return documentoComercialString.equals("Seleccionar documento comercial");
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

    private LocalDate convertirAFecha(String fechaString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(fechaString, formatter);
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


    // Obtener lista al insertar egreso,presupuesto
    private List<CategoriaCriterio> obtenerListaCategoriaCriterio(Request request){

        String categoriasLeidas = request.queryParams("nombresCategorias");

        String[] listaNombresCategorias = categoriasLeidas.split("=");

        List<CategoriaCriterio> listaADevolver = new ArrayList<>();
        List<CategoriaCriterio> categoriasCriterioTotales = this.repoCategoriaCriterio.buscarTodos();

        for(String unNombreCategoria : listaNombresCategorias){
            CategoriaCriterio categoriaEncontrada = encontrarCategoria(unNombreCategoria, categoriasCriterioTotales);
            listaADevolver.add(categoriaEncontrada);
        }

        return listaADevolver;
    }

    private CategoriaCriterio encontrarCategoria(String nombreCategoria, List<CategoriaCriterio> categorias){
        for(CategoriaCriterio unaCategoria : categorias){
            if (unaCategoria.getNombreCategoria().equals(nombreCategoria)){
                return unaCategoria;
            }
        }
        return null;
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

    private TipoDocumentoComercial buscarTipoDocComercial(String nombre){
        List<TipoDocumentoComercial> tiposDocumentoComercial = this.repoTipoDocComercial.buscarTodos();

        for ( TipoDocumentoComercial unTipoDocComercial : tiposDocumentoComercial ) {
            if (unTipoDocComercial.getNombre().equals(nombre)){
                return unTipoDocComercial;
            }
        }
        return null;
    }
}
