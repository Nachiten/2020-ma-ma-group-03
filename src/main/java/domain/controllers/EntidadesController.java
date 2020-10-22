package domain.controllers;

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

    public EntidadesController(){
        this.repoTipoMedioPago = FactoryRepositorio.get(TipoMedioDePago.class);
        this.repoTipoDocComercial = FactoryRepositorio.get(TipoDocumentoComercial.class);
        this.repoOperacionEgreso = FactoryRepositorio.get(OperacionDeEgreso.class);
    }

    // --- GETs ---

    public ModelAndView ingresos(Request request, Response response) {
        return new ModelAndView(null, "ingresos.hbs");
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaString = request.queryParams("fecha");
        String montoTotalString = request.queryParams("montoTotal");

        String tipoMedioDePagoString = request.queryParams("medioDePago");
        String tipoDocumentoComercialString = request.queryParams("documentoComercial");

        // TODO - Falta obtener los items y sus valores
        String item1 = request.queryParams("numero_P[]");

        // Convierto de string a localDate
        LocalDate fecha = LocalDate.parse(fechaString, formatter);
        // Convierto de string a float
        float montoTotal = Float.parseFloat(montoTotalString);

        TipoMedioDePago tipoMedioPago = buscarTipoMedioPago(tipoMedioDePagoString);
        TipoDocumentoComercial tipoDocComercial = buscarTipoDocComercial(tipoDocumentoComercialString);

        int numeroMedioPago = 32124200;
        int numeroDocComercial = 553243212;
        // TODO - Necesito numero de documento comercial y numero de medio de pago

        MedioDePago medioDePago = new MedioDePago(tipoMedioPago, numeroMedioPago);
        DocumentoComercial documentoComercial = new DocumentoComercial(tipoDocComercial, numeroDocComercial);

        OperacionDeEgreso operacionAGuardar = new OperacionDeEgreso(fecha, montoTotal);

        EntidadJuridica entidadJuridica = EntityManagerHelper.getEntityManager().find(EntidadJuridica.class, 1);
        Usuario miUsuario = EntityManagerHelper.getEntityManager().find(Usuario.class, 1);

        operacionAGuardar.setEntidadJuridicaAsociada(entidadJuridica);
        operacionAGuardar.setUsuario(miUsuario);
        operacionAGuardar.setMedioDePago(medioDePago);
        operacionAGuardar.setDocumentoComercial(documentoComercial);

        try{
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

    private TipoMedioDePago buscarTipoMedioPago(String nombre){
        List<TipoMedioDePago> tiposMediosPago = this.repoTipoMedioPago.buscarTodos();

        for ( TipoMedioDePago unTipoMedioPago : tiposMediosPago ) {
            if (unTipoMedioPago.getTipoPago().equals(nombre)){
                return unTipoMedioPago;
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
