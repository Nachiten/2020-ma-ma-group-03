package domain.controllers;

import domain.entities.apiMercadoLibre.Moneda;
import domain.entities.entidades.EntidadJuridica;
import domain.entities.operaciones.*;
import domain.entities.usuarios.Usuario;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import persistencia.db.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntidadesController {

    private Repositorio<OperacionDeEgreso> repoOperacionEgreso;
    private Repositorio<TipoMedioDePago> repoTipoMedioPago;
    private Repositorio<TipoDocumentoComercial> repoTipoDocComercial;
    private Repositorio<Moneda> repoMonedas;
    private Repositorio<OperacionDeIngreso> repoOperacionIngreso;

    public EntidadesController(){
        this.repoTipoMedioPago = FactoryRepositorio.get(TipoMedioDePago.class);
        this.repoTipoDocComercial = FactoryRepositorio.get(TipoDocumentoComercial.class);
        this.repoOperacionEgreso = FactoryRepositorio.get(OperacionDeEgreso.class);
        this.repoOperacionIngreso = FactoryRepositorio.get(OperacionDeIngreso.class);
        this.repoMonedas = FactoryRepositorio.get(Moneda.class);
    }

    // --- GETs ---

    public ModelAndView ingresos(Request request, Response response) {

        Map<String, Object> parametros = new HashMap<>();
        List<Moneda> monedas = this.repoMonedas.buscarTodos();
        parametros.put("monedas", monedas);

        return new ModelAndView(parametros, "ingresos.hbs");
    }

    public ModelAndView egresos(Request request, Response response) {

        Map<String, Object> parametros = new HashMap<>();

        List<TipoMedioDePago> tiposMediosPago = this.repoTipoMedioPago.buscarTodos();
        List<TipoDocumentoComercial> tiposDocumentoComercial = this.repoTipoDocComercial.buscarTodos();

        parametros.put("tiposMediosDePago", tiposMediosPago);
        parametros.put("tiposDocumentoComercial", tiposDocumentoComercial);

        return new ModelAndView(parametros, "egresos.hbs");
    }

    public ModelAndView presupuestos(Request request, Response response) {
        return new ModelAndView(null, "presupuestos.hbs");
    }

    public ModelAndView criterios(Request request, Response response) {
        return new ModelAndView(null, "criterios.hbs");
    }

    public ModelAndView listadoOperaciones(Request request, Response response) {
        return new ModelAndView(null, "listadoOperaciones.hbs");
    }

    public ModelAndView asociarOperacion(Request request, Response response) {
        return new ModelAndView(null, "asociarOperacion.hbs");
    }

    // --- POSTs ---

    public Response guardarOperacionDeEgreso(Request request, Response response){
        // Leo los query params
        String fechaString = request.queryParams("fecha");
        String montoTotalString = request.queryParams("montoTotal");

        String tipoMedioDePagoString = request.queryParams("medioDePago");
        String tipoDocumentoComercialString = request.queryParams("documentoComercial");
        String presupuestosRequeridosString = request.queryParams("presupuestosRequeridos");

        // TODO - Intento de leer los items pero no funciona
        String itemNombre = request.queryParams("nombre_I[0]");
        String itemCant = request.queryParams("precio_I[0]");

        // Convierto de string a LocalDate
        LocalDate fecha = convertirAFecha(fechaString);
        // Convierto de string a float
        float montoTotal = Float.parseFloat(montoTotalString);
        // Convierto de string a int
        int presupuestosRequeridos = Integer.parseInt(presupuestosRequeridosString);

        // Genero tipo medio pago y tipoDocComercial
        TipoMedioDePago tipoMedioPago = buscarTipoMedioPago(tipoMedioDePagoString);
        TipoDocumentoComercial tipoDocComercial = buscarTipoDocComercial(tipoDocumentoComercialString);

        // TODO - Necesito numero de documento comercial y numero de medio de pago
        int numeroMedioPago = 32124200;
        int numeroDocComercial = 553243212;

        // Genero medio pago y documento comercial
        MedioDePago medioDePago = new MedioDePago(tipoMedioPago, numeroMedioPago);
        DocumentoComercial documentoComercial = new DocumentoComercial(tipoDocComercial, numeroDocComercial);

        // Genero operacion de egreso
        OperacionDeEgreso operacionAGuardar = new OperacionDeEgreso(fecha, montoTotal);

        EntidadJuridica entidadJuridica = EntityManagerHelper.getEntityManager().find(EntidadJuridica.class, 1);
        Usuario miUsuario = EntityManagerHelper.getEntityManager().find(Usuario.class, 1);

        // Setters necesarios
        operacionAGuardar.setEntidadJuridicaAsociada(entidadJuridica);
        operacionAGuardar.setUsuario(miUsuario);
        operacionAGuardar.setMedioDePago(medioDePago);
        operacionAGuardar.setDocumentoComercial(documentoComercial);
        operacionAGuardar.setCantidadPresupuestosRequerida(presupuestosRequeridos);

        try{
            // Persistir operacion
            repoOperacionEgreso.agregar(operacionAGuardar);
        }catch (Exception e) {
            String mensajeError = e.getMessage();
            System.out.println("EXCEPCION: " + mensajeError);
            response.redirect("/error?codError=" + mensajeError);
            return response;
        }

        response.redirect("/egresos");
        return response;
    }

    public Response guardarOperacionDeIngreso(Request request, Response response){

        String fechaString = request.queryParams("fecha");
        String montoString = request.queryParams("monto");
        String monedaString = request.queryParams("moneda");
        String descripcion = request.queryParams("descripcion");

        LocalDate fecha = convertirAFecha(fechaString);
        float monto = Float.parseFloat(montoString);

        Moneda monedaElegida = buscarMoneda(monedaString);

        OperacionDeIngreso operacionAGuardar = new OperacionDeIngreso(descripcion, monto, fecha, monedaElegida);

        try{
            // Persistir operacion
            repoOperacionEgreso.agregar(operacionAGuardar);
        }catch (Exception e) {
            String mensajeError = e.getMessage();
            System.out.println("EXCEPCION: " + mensajeError);
            response.redirect("/error?codError=" + mensajeError);
            return response;
        }

        response.redirect("/ingresos");
        return response;
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
}
