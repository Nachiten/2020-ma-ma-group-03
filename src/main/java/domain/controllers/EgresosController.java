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

    public EgresosController(ModalAndViewController modalAndViewController, OperadorController operadorController){

        this.repoTipoMedioPago = FactoryRepositorio.get(TipoMedioDePago.class);
        this.repoTipoDocComercial = FactoryRepositorio.get(TipoDocumentoComercial.class);
        this.repoOperacionEgreso = FactoryRepositorio.get(OperacionDeEgreso.class);
        this.repoProveedor = FactoryRepositorio.get(Proveedor.class);
        this.repoCriterio = FactoryRepositorio.get(Criterio.class);
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
    public ModelAndView egresos(Request request, Response response)throws Exception {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewEgresos);
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

        if (noEligioMedioPago(tipoMedioDePagoString) || operadorController.noEligioDocumentoComercial(tipoDocumentoComercialString)){
            // No se inserto metodo de pago o documento comercial
            modalAndViewController.getParametros().put("mensaje","No se inserto metodo de pago o documento comercial");
            return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");
        }

        // Leo lista de categorias
        List<CategoriaCriterio> categoriasCriterio = operadorController.obtenerListaCategoriaCriterio(request);
        // Leo todos los items
        List<Item> listaItems = operadorController.obtenerListaItems(request);

        // Convierto de string a LocalDate
        LocalDate fecha = operadorController.convertirAFecha(fechaString);
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
        TipoDocumentoComercial tipoDocComercial = operadorController.buscarTipoDocComercial(tipoDocumentoComercialString);

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

        if (operadorController.persistenciaNoValida(repoOperacionEgreso, operacionAGuardar)){
            modalAndViewController.getParametros().put("mensaje", "Se produjo un erroe al gradar los datos.");
            return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");
        }

        // Se persistio correctamente
        modalAndViewController.getParametros().put("mensaje", "Se guardaron los datos correctamente");
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
}
