package domain.controllers;

import domain.entities.apiMercadoLibre.Moneda;
import domain.entities.entidades.EntidadJuridica;
import domain.entities.operaciones.OperacionDeEgreso;
import domain.entities.operaciones.OperacionDeIngreso;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngresosController {

    private Repositorio<OperacionDeIngreso> repoOperacionIngreso;
    private Repositorio<Moneda> repoMonedas;
    private Repositorio<EntidadJuridica> repoEntidadJuridica;
    private ModalAndViewController modalAndViewController;
    private OperadorController operadorController;

    public IngresosController(ModalAndViewController modalAndViewController, OperadorController operadorController){

        this.repoOperacionIngreso = FactoryRepositorio.get(OperacionDeIngreso.class);
        this.repoMonedas = FactoryRepositorio.get(Moneda.class);
        this.repoEntidadJuridica = FactoryRepositorio.get(EntidadJuridica.class);
        this.modalAndViewController = modalAndViewController;
        this.operadorController = operadorController;
    }

    private ModelAndView modalAndViewIngresos() {

        List<Moneda> monedas = this.repoMonedas.buscarTodos();
        modalAndViewController.getParametros().put("monedas", monedas);

        return new ModelAndView(modalAndViewController.getParametros(), "ingresos.hbs");
    }

    public ModelAndView ingresos(Request request, Response response) throws Exception {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewIngresos);
    }

    public ModelAndView guardarOperacionDeIngreso(Request request, Response response){

        String fechaString = request.queryParams("fecha");
        String periodoDeAceptacionString = request.queryParams("periodoDeAceptacion");
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
        LocalDate fecha = operadorController.convertirAFecha(fechaString);
        LocalDate fechaPeriodoAceptacion = operadorController.convertirAFecha(periodoDeAceptacionString);
        float monto = Float.parseFloat(montoString);

        //se convierte el string moneda a tipo moneda
        Moneda monedaElegida = buscarMoneda(monedaString);

        //se instancia una operacion de ingreso a persistir
        OperacionDeIngreso operacionDeIngresoAGuardar = new OperacionDeIngreso(descripcion, monto, fecha, monedaElegida);
        int entidadJuridica_id = modalAndViewController.getUsuario().getEntidadJuridica().getId();
        EntidadJuridica entidadJuridica = repoEntidadJuridica.buscar(entidadJuridica_id);
        operacionDeIngresoAGuardar.setEntidadJuridicaAsociada(entidadJuridica);
        operacionDeIngresoAGuardar.setPeriodoAceptacion(fechaPeriodoAceptacion);

        if (operadorController.persistenciaNoValida(repoOperacionIngreso, operacionDeIngresoAGuardar)){
            model.put("mensaje", "No se guardaron los datos, intentelo nuevamente.");
            return new ModelAndView(model, "modalInformativo2.hbs");
        }

        model.put("mensaje","Los datos se guardaron correctamente.");
        return new ModelAndView(model,"modalInformativo2.hbs");
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

    private boolean validarVacio(String cadena){
        return StringUtils.isEmpty(cadena);
    }

    public ModelAndView verDetalleIngreso(Request request, Response response){

        int idOperacion = Integer.parseInt(request.params("id"));

        OperacionDeIngreso operacionDeIngreso = buscarOperacionDeIngreso(idOperacion);

        modalAndViewController.getParametros().put("id", operacionDeIngreso.getId());
        modalAndViewController.getParametros().put("descripcion", operacionDeIngreso.getDescripcion());
        modalAndViewController.getParametros().put("fecha", operacionDeIngreso.getFecha());
        modalAndViewController.getParametros().put("montoTotal", operacionDeIngreso.getMontoTotal());
        modalAndViewController.getParametros().put("moneda", operacionDeIngreso.getMoneda());
        modalAndViewController.getParametros().put("operacionesVinculadas", operacionDeIngreso.getOperacionesDeEgresoVinculadas());
        modalAndViewController.getParametros().put("montoSinVincular", operacionDeIngreso.getMontoSinVincular());
        modalAndViewController.getParametros().put("periodoAceptacion", operacionDeIngreso.getPeriodoAceptacion());

        return new ModelAndView(modalAndViewController.getParametros(),"modalDetalleIngreso.hbs");
    }

    public ModelAndView verOperacionesVinculadas(Request request, Response response){

        int idOperacion = Integer.parseInt(request.params("id"));

        OperacionDeIngreso operacionDeIngreso = buscarOperacionDeIngreso(idOperacion);

        modalAndViewController.getParametros().put("operacionesVinculadas", operacionDeIngreso.getOperacionesDeEgresoVinculadas());

        if(operacionDeIngreso.getOperacionesDeEgresoVinculadas().isEmpty()){
            modalAndViewController.getParametros().put("mensaje", "No se guardaron los datos, intentelo nuevamente.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        return new ModelAndView(modalAndViewController.getParametros(),"modalOperacionesVinculadasAIngreso.hbs");
    }


    private OperacionDeIngreso buscarOperacionDeIngreso(int id){
        List<OperacionDeIngreso> operacionDeIngresos = this.repoOperacionIngreso.buscarTodos();

        for(OperacionDeIngreso unaOperacion : operacionDeIngresos){
            if(unaOperacion.getId() == id){
                return unaOperacion;
            }
        }
        return null;
    }
}

