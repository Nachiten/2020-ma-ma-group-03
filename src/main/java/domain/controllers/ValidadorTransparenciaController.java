package domain.controllers;

import domain.entities.operaciones.OperacionDeEgreso;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import validadorTransparencia.*;

import java.util.*;

public class ValidadorTransparenciaController {

    private ModalAndViewController modalAndViewController;
    private Repositorio<OperacionDeEgreso> repoOperacionEgreso;

    public ValidadorTransparenciaController(ModalAndViewController modalAndViewController) {
        this.repoOperacionEgreso = FactoryRepositorio.get(OperacionDeEgreso.class);
        this.modalAndViewController = modalAndViewController;
    }

    public ModelAndView validadorTransparencia(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewValidadorTransparencia);
    }

    public ModelAndView modalAndViewValidadorTransparencia() {
        return new ModelAndView(modalAndViewController.getParametros(), "validadorTransparencia.hbs");
    }

    public ModelAndView programarValidadorDeTransparencia(Request request, Response response) {

        String lunes = request.queryParams("lunes");
        String martes = request.queryParams("martes");
        String miercoles = request.queryParams("miercoles");
        String jueves = request.queryParams("jueves");
        String viernes = request.queryParams("viernes");
        String sabado = request.queryParams("sabado");
        String domingo = request.queryParams("domingo");

        try{
            List<OperacionDeEgreso> operacionesDeEgreso = repoOperacionEgreso.buscarTodos();

            ValidarCantidadPresupuestos validarCantidadPresupuestos = new ValidarCantidadPresupuestos();
            ValidarPresupuestoAsociado validarPresupuestoAsociado = new ValidarPresupuestoAsociado();

            List<EstrategiaValidacion> validaciones = new ArrayList<>(Arrays.asList(validarCantidadPresupuestos, validarPresupuestoAsociado));

            ValidadorTransparencia validadorTransparencia = new ValidadorTransparencia(validaciones, operacionesDeEgreso, 1);

            ejecutarValidadorEnDia(Calendar.MONDAY, lunes, validadorTransparencia);
            ejecutarValidadorEnDia(Calendar.TUESDAY, martes, validadorTransparencia);
            ejecutarValidadorEnDia(Calendar.WEDNESDAY, miercoles, validadorTransparencia);
            ejecutarValidadorEnDia(Calendar.THURSDAY, jueves, validadorTransparencia);
            ejecutarValidadorEnDia(Calendar.FRIDAY, viernes, validadorTransparencia);
            ejecutarValidadorEnDia(Calendar.SATURDAY, sabado, validadorTransparencia);
            ejecutarValidadorEnDia(Calendar.SUNDAY, domingo, validadorTransparencia);
        }catch (Exception e){
            modalAndViewController.getParametros().put("mensaje","Hubo un error al programar el validador.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        modalAndViewController.getParametros().put("mensaje","Se programó el Validador de Transparencia correctamente.");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
    }

    public ModelAndView ejecutarValidadorDeTransparenciaAhora(Request request, Response response){
        try {
            List<OperacionDeEgreso> operacionesDeEgreso = repoOperacionEgreso.buscarTodos();

            ValidarCantidadPresupuestos validarCantidadPresupuestos = new ValidarCantidadPresupuestos();
            ValidarPresupuestoAsociado validarPresupuestoAsociado = new ValidarPresupuestoAsociado();

            List<EstrategiaValidacion> validaciones = new ArrayList<>(Arrays.asList(validarCantidadPresupuestos, validarPresupuestoAsociado));

            ValidadorTransparencia validadorTransparencia = new ValidadorTransparencia(validaciones, operacionesDeEgreso, 1);

            validadorTransparencia.ejecutarse();
        }catch (Exception e){
            modalAndViewController.getParametros().put("mensaje","Hubo un error al ejecutar el validador.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        modalAndViewController.getParametros().put("mensaje","Se ejecutó el Validador de Transparencia correctamente.");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
    }

    private void ejecutarValidadorEnDia(int dia, String horaString, ValidadorTransparencia validadorTransparencia){

        if (horaString.equals("-1") || horaString.equals("Seleccionar horario")){
            return;
        }

        // hora = recortar los primeros dos digitos de la hora que viene
        int hora = Integer.parseInt(horaString.substring(0,2));

        Scheduler.ejecutarEnDiaYHorario(validadorTransparencia, dia, hora); //lo pase a minutos.
    }

}
