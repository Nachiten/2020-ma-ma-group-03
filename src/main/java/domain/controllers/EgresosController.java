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
import java.util.List;

public class EgresosController {

    private Repositorio<OperacionDeEgreso> repoOperacionEgreso;
    private Repositorio<TipoMedioDePago> repoTipoMedioPago;
    private Repositorio<TipoDocumentoComercial> repoTipoDocComercial;
    private Repositorio<Criterio> repoCriterio;
    private Repositorio<Proveedor> repoProveedor;
    private ModalAndViewController modalAndViewController;
    private OperadorController operadorController;
    private Repositorio<EntidadJuridica> repoEntidadJuridica;

    public EgresosController(ModalAndViewController modalAndViewController, OperadorController operadorController){
        this.repoTipoMedioPago = FactoryRepositorio.get(TipoMedioDePago.class);
        this.repoTipoDocComercial = FactoryRepositorio.get(TipoDocumentoComercial.class);
        this.repoOperacionEgreso = FactoryRepositorio.get(OperacionDeEgreso.class);
        this.repoProveedor = FactoryRepositorio.get(Proveedor.class);
        this.repoCriterio = FactoryRepositorio.get(Criterio.class);
        this.repoEntidadJuridica = FactoryRepositorio.get(EntidadJuridica.class);
        this.modalAndViewController = modalAndViewController;
        this.operadorController = operadorController;
    }

    private ModelAndView modalAndViewEgresos(){

        List<TipoMedioDePago> tiposMediosPago = this.repoTipoMedioPago.buscarTodos();
        List<TipoDocumentoComercial> tiposDocumentoComercial = this.repoTipoDocComercial.buscarTodos();
        List<Proveedor> proveedores = this.repoProveedor.buscarTodos();
        List<Criterio> criterios = this.repoCriterio.buscarTodos();
        List<Criterio> criterios2 = operadorController.quitarMitad(criterios);

        modalAndViewController.getParametros().put("tiposMediosDePago", tiposMediosPago);
        modalAndViewController.getParametros().put("tiposDocumentoComercial", tiposDocumentoComercial);
        modalAndViewController.getParametros().put("proveedores", proveedores);
        modalAndViewController.getParametros().put("criterios", criterios);
        modalAndViewController.getParametros().put("criterios2", criterios2);
        return new ModelAndView(modalAndViewController.getParametros(), "egresos.hbs");
    }
    public ModelAndView egresos(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewEgresos);
    }

    public ModelAndView verDetalleProveedor(Request request, Response response){

        String proveedorString = request.queryParams("proveedor");

        if(proveedorString.equals("Seleccionar proveedor")){
            modalAndViewController.getParametros().put("mensaje", "Seleccione un proveedor.");
            return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");
        }

        Proveedor proveedor = buscarProveedor(proveedorString);

        modalAndViewController.getParametros().put("nombre", proveedor.getNombreProveedor());
        modalAndViewController.getParametros().put("apellido", proveedor.getApellidoProveedor());
        modalAndViewController.getParametros().put("dni", proveedor.getDniProveedor());
        modalAndViewController.getParametros().put("cuit", proveedor.getCuit());
        modalAndViewController.getParametros().put("razonSocial", proveedor.getRazonSocialProveedor());

        if(proveedor.getDireccionPostal() == null){
            return new ModelAndView(modalAndViewController.getParametros(),"modalDetalleProveedor.hbs");
        }

        modalAndViewController.getParametros().put("pais", proveedor.getDireccionPostal().getPais());
        modalAndViewController.getParametros().put("provincia", proveedor.getDireccionPostal().getProvincia());

        return new ModelAndView(modalAndViewController.getParametros(),"modalDetalleProveedor.hbs");
    }

    public void cargarCategorias(Request request, Response response){
        String criterioString = request.queryParams("criteriosCategoria");

        Criterio criterio = buscarCriterio(criterioString);

        List<CategoriaCriterio> categoriasAsociadas = criterio.getListaCategoriaCriterio();

        modalAndViewController.getParametros().put("categorias", categoriasAsociadas);

    }


    public ModelAndView verDetalleEgreso(Request request, Response response){

        int idOperacion = Integer.parseInt(request.params("id"));

        OperacionDeEgreso operacionDeEgreso = buscarOperacionDeEgreso(idOperacion);

        modalAndViewController.getParametros().put("id", operacionDeEgreso.getIdOperacion());
        modalAndViewController.getParametros().put("usuario", operacionDeEgreso.getUsuario().getNombreUsuario());
        modalAndViewController.getParametros().put("fecha", operacionDeEgreso.getFecha());
        modalAndViewController.getParametros().put("montoTotal", operacionDeEgreso.getMontoTotal());
        modalAndViewController.getParametros().put("items", operacionDeEgreso.getItems());
        modalAndViewController.getParametros().put("documentoComercial", operacionDeEgreso.getDocumentoComercial().getTipo().getNombre());
        modalAndViewController.getParametros().put("medioDePago", operacionDeEgreso.getMedioDePago().getTipo().getTipoPago());

        if(operacionDeEgreso.getProveedorAsociado().getRazonSocialProveedor() == null){
            modalAndViewController.getParametros().put("proveedor", operacionDeEgreso.getProveedorAsociado().getNombreProveedor());
        } else {
            modalAndViewController.getParametros().put("proveedor", operacionDeEgreso.getProveedorAsociado().getRazonSocialProveedor());
        }
        modalAndViewController.getParametros().put("operacionDeIngreso", operacionDeEgreso.getOperacionDeIngreso());
        modalAndViewController.getParametros().put("cantidadPresupuestos", operacionDeEgreso.getCantidadPresupuestosRequerida());
        modalAndViewController.getParametros().put("categorias", operacionDeEgreso.getListaCategoriaCriterio());
        modalAndViewController.getParametros().put("revisores", operacionDeEgreso.getRevisores());

        return new ModelAndView(modalAndViewController.getParametros(),"modalDetalleEgreso.hbs");
    }

    public ModelAndView verItemsEgreso(Request request, Response response){

        int idOperacion = Integer.parseInt(request.params("id"));

        OperacionDeEgreso operacionDeEgreso = buscarOperacionDeEgreso(idOperacion);

        modalAndViewController.getParametros().put("items", operacionDeEgreso.getItems());

        return new ModelAndView(modalAndViewController.getParametros(),"modalItemsEgreso.hbs");
    }

    public ModelAndView verCategoriasEgreso(Request request, Response response){

        int idOperacion = Integer.parseInt(request.params("id"));

        OperacionDeEgreso operacionDeEgreso = buscarOperacionDeEgreso(idOperacion);

        modalAndViewController.getParametros().put("categorias", operacionDeEgreso.getListaCategoriaCriterio());

        if(operacionDeEgreso.getListaCategoriaCriterio().isEmpty()){
            modalAndViewController.getParametros().put("mensaje", "El egreso no tiene categorías asociadas.");
            return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");
        }

        return new ModelAndView(modalAndViewController.getParametros(),"modalCategoriasEgreso.hbs");
    }

    public ModelAndView verRevisores(Request request, Response response){

        int idOperacion = Integer.parseInt(request.params("id"));

        OperacionDeEgreso operacionDeEgreso = buscarOperacionDeEgreso(idOperacion);

        modalAndViewController.getParametros().put("revisores", operacionDeEgreso.getRevisores());

        if(operacionDeEgreso.getRevisores().isEmpty()){
            modalAndViewController.getParametros().put("mensaje", "El egreso no tiene revisores asociados.");
            return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");
        }

        return new ModelAndView(modalAndViewController.getParametros(),"modalRevisoresEgreso.hbs");
    }


    public ModelAndView guardarOperacionDeEgreso(Request request, Response response) {
        // Leo los query params
        String fechaString = request.queryParams("fecha");
        String tipoMedioDePagoString = request.queryParams("medioDePago");
        String numeroMedioDePagoString = request.queryParams("numeroMedioDePago");
        String tipoDocumentoComercialString = request.queryParams("documentoComercial");
        String numeroDocumentoComercialString = request.queryParams("numeroDocumentoComercial");
        String revisor = request.queryParams("revisor");
        String presupuestosRequeridosString = request.queryParams("presupuestosRequeridos");
        String razonSocialProveedor = request.queryParams("proveedor");

        if (noEligioMedioPago(tipoMedioDePagoString) || operadorController.noEligioDocumentoComercial(tipoDocumentoComercialString)){
            // No se inserto metodo de pago o documento comercial
            modalAndViewController.getParametros().put("mensaje","No se inserto metodo de pago o documento comercial");
            return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");
        }

        // Leo lista de categorias
        List<CategoriaCriterio> categoriasCriterio = operadorController.obtenerListaCategoriaCriterio(request);
        // Leo todos los items
        List<Item> listaItems = operadorController.obtenerListaItems(request);

        if(listaItems.size() == 0){
            modalAndViewController.getParametros().put("mensaje","No se ingresaron items.");
            return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");
        }

        // Convierto de string a LocalDate
        LocalDate fecha = operadorController.convertirAFecha(fechaString);
        // Convierto de string a float
        float montoTotal = operadorController.calcularMontoTotalDeItems(listaItems);
        // Convierto de string a int
        int presupuestosRequeridos = Integer.parseInt(presupuestosRequeridosString);
        int numeroDocumentoComercial = Integer.parseInt(numeroDocumentoComercialString);

        // Busco proveedor
        Proveedor proveedor = buscarProveedor(razonSocialProveedor);
        // Genero tipo medio pago y tipoDocComercial
        TipoMedioDePago tipoMedioPago = buscarTipoMedioPago(tipoMedioDePagoString);
        TipoDocumentoComercial tipoDocComercial = operadorController.buscarTipoDocComercial(tipoDocumentoComercialString);

        // Genero medio pago y documento comercial
        MedioDePago medioDePago = new MedioDePago(tipoMedioPago, numeroMedioDePagoString);
        DocumentoComercial documentoComercial = new DocumentoComercial(tipoDocComercial, numeroDocumentoComercial);

        // Genero operacion de egreso
        OperacionDeEgreso operacionAGuardar = new OperacionDeEgreso(fecha, montoTotal);

        //Usuario miUsuario = contextoDeUsuarioLogueado.getUsuarioLogueado();
        Usuario miUsuario = modalAndViewController.getUsuario();
        EntidadJuridica entidadJuridicaDeUsuario = miUsuario.getEntidadJuridica();
        EntidadJuridica entidadJuridica = repoEntidadJuridica.buscar(entidadJuridicaDeUsuario.getId());

        // Setters necesarios
        operacionAGuardar.setEntidadJuridicaAsociada(entidadJuridica);
        operacionAGuardar.setUsuario(miUsuario);
        operacionAGuardar.setMedioDePago(medioDePago);
        operacionAGuardar.setDocumentoComercial(documentoComercial);
        operacionAGuardar.setCantidadPresupuestosRequerida(presupuestosRequeridos);
        operacionAGuardar.setItems(listaItems);
        operacionAGuardar.setProveedorAsociado(proveedor);
        operacionAGuardar.setListaCategoriaCriterio(categoriasCriterio);

        if(revisor.equals("Si")){
            operacionAGuardar.agregarRevisor(miUsuario);
            miUsuario.agregarOperacionDeEgreso(operacionAGuardar);
        }

        if (operadorController.persistenciaNoValida(repoOperacionEgreso, operacionAGuardar)){
            modalAndViewController.getParametros().put("mensaje", "Se produjo un error al guardar la operación de egreso.");
            return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");
        }

        // Se persistio correctamente
        modalAndViewController.getParametros().put("mensaje", "La operación de egreso se guardó correctamente");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");
    }

    private boolean noEligioMedioPago(String medioPagoString){
        return medioPagoString.equals("Seleccionar medio de pago");
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
            if (unProveedor.getRazonSocialProveedor().equals(razonSocial)){
                return unProveedor;
            }
        }
        return null;
    }

    private Criterio buscarCriterio(String criterio){
        List<Criterio> criterios = this.repoCriterio.buscarTodos();

        for(Criterio unCriterio : criterios){
            if(unCriterio.getNombre().equals(criterio)){
                return unCriterio;
            }
        }
        return null;
    }

    private OperacionDeEgreso buscarOperacionDeEgreso(int id){
        List<OperacionDeEgreso> operacionDeEgresos = this.repoOperacionEgreso.buscarTodos();

        for(OperacionDeEgreso unaOperacion : operacionDeEgresos){
            if(unaOperacion.getIdOperacion() == id){
                return unaOperacion;
            }
        }
        return null;
    }
}