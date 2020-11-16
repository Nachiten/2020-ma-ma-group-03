package domain.controllers;

import domain.entities.operaciones.OperacionDeEgreso;
import domain.entities.operaciones.OperacionDeIngreso;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

public class AsociacionOperacionesController {

    private ModalAndViewController modalAndViewController;
    private Repositorio<OperacionDeEgreso> repoOperacionEgreso;
    private Repositorio<OperacionDeIngreso> repoOperacionIngreso;

    public AsociacionOperacionesController(ModalAndViewController modalAndViewController){
        this.repoOperacionEgreso = FactoryRepositorio.get(OperacionDeEgreso.class);
        this.repoOperacionIngreso = FactoryRepositorio.get(OperacionDeIngreso.class);
        this.modalAndViewController = modalAndViewController;
    }

    private ModelAndView modalAndViewListadoOperaciones(){

        List<OperacionDeEgreso> operacionesEgreso = this.repoOperacionEgreso.buscarTodos();
        List<OperacionDeIngreso> operacionesIngreso = this.repoOperacionIngreso.buscarTodos();

        modalAndViewController.getParametros().put("operacionesEgreso", operacionesEgreso);
        modalAndViewController.getParametros().put("operacionesIngreso", operacionesIngreso);

        return new ModelAndView(modalAndViewController.getParametros(), "listadoOperaciones.hbs");
    }

    public ModelAndView listadoOperaciones(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewListadoOperaciones());
    }

    private ModelAndView modalAndViewAsociarOperacion(){
        return new ModelAndView(modalAndViewController.getParametros(), "asociarOperacion.hbs");
    }

    public ModelAndView asociarOperacion(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewAsociarOperacion());
    }

    public ModelAndView ejecutarVinculacion(Request request, Response response) {

        List<String> criterios = obtenerListaCriteriosVinculacion(request);

        List<OperacionDeEgreso> operacionesEgreso = this.repoOperacionEgreso.buscarTodos();
        List<OperacionDeIngreso> operacionesIngreso = this.repoOperacionIngreso.buscarTodos();

        ApiEgresoIngreso.ServicioVinculacionEgresosIngresos servicioVinculacionEgresosIngresos = ApiEgresoIngreso.ServicioVinculacionEgresosIngresos.instancia();

        List<OperacionDeIngreso> ingresosVinculados;

        try{
            ingresosVinculados = servicioVinculacionEgresosIngresos.ejecutarVinculacion(operacionesEgreso, operacionesIngreso, criterios);

            for (OperacionDeIngreso unaOperacion : ingresosVinculados){
                this.repoOperacionIngreso.modificar(unaOperacion);
            }
        }catch (Exception e) {
            String mensajeError = e.getMessage();
            System.out.println("EXCEPCION: " + mensajeError);
            // Hubo un error
            modalAndViewController.getParametros().put("mensaje", "Se produjo un error al realizar la vinculacion.");
            return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");
        }

        // Se persistio correctamente
        modalAndViewController.getParametros().put("mensaje", "Se ejecuto la vinculacion correctamente");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");
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
}
