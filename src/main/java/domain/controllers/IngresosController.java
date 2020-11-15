package domain.controllers;

import domain.entities.apiMercadoLibre.Moneda;
import domain.entities.operaciones.OperacionDeIngreso;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.utils.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngresosController {

    private Repositorio<OperacionDeIngreso> repoOperacionIngreso;
    private Repositorio<Moneda> repoMonedas;
    private ModalAndViewController modalAndViewController;

    public IngresosController(ModalAndViewController modalAndViewController){

        this.repoOperacionIngreso = FactoryRepositorio.get(OperacionDeIngreso.class);
        this.repoMonedas = FactoryRepositorio.get(Moneda.class);
        this.modalAndViewController = modalAndViewController;
    }

    private ModelAndView modalAndViewIngresos() {

        List<Moneda> monedas = this.repoMonedas.buscarTodos();
        modalAndViewController.getParametros().put("monedas", monedas);

        return new ModelAndView(modalAndViewController.getParametros(), "ingresos.hbs");
    }

    public ModelAndView ingresos(Request request, Response response) throws Exception {

        modalAndViewController.cargarParametosHashMap();
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewIngresos);
    }


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
        operacionDeIngresoAGuardar.setEntidadJuridicaAsociada(modalAndViewController.getUsuario().getEntidadJuridica());

        if (!validarPersistencia(repoOperacionIngreso, operacionDeIngresoAGuardar)){
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

    private boolean validarPersistencia(Repositorio<?> objetoFactory, Object objetoClase){
        try {
            objetoFactory.agregar(objetoClase);
        }catch (Exception e){
            System.out.println("EXCEPCION: " + e.getMessage());
            return false;
        }
        return true;
    }

    private LocalDate convertirAFecha(String fechaString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(fechaString, formatter);
    }

}