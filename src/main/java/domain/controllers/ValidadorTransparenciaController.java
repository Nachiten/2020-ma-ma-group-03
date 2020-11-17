package domain.controllers;

import criterioOperacion.Criterio;
import criterioSeleccionProveedor.CriterioProveedorMenorValor;
import domain.entities.operaciones.OperacionDeEgreso;
import domain.entities.operaciones.OperacionDeIngreso;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import validadorTransparencia.*;
import validadoresContrasenia.Validador;

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

    public ModelAndView ejecutarValidadorDeTransparencia(Request request, Response response) {

        String minutosEnString = request.queryParams("minutos");
        List<OperacionDeEgreso> operacionesDeEgreso = repoOperacionEgreso.buscarTodos();

        Map<String, Object> model = new HashMap<>();

        int minutos = Integer.parseInt(minutosEnString);

        ValidarCantidadPresupuestos validarCantidadPresupuestos = new ValidarCantidadPresupuestos();
        ValidarPresupuestoAsociado validarPresupuestoAsociado = new ValidarPresupuestoAsociado();

        List<EstrategiaValidacion> validaciones = new ArrayList<>(Arrays.asList(validarCantidadPresupuestos, validarPresupuestoAsociado));

        ValidadorTransparencia validadorTransparencia = new ValidadorTransparencia(validaciones, operacionesDeEgreso, 1);
        Scheduler.ejecutarCadaCiertoTiempo(validadorTransparencia, minutos * 60000); //lo pase a minutos.

        model.put("mensaje","Se ejecutó el Validador de Transparencia correctamente.");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
    }

}
