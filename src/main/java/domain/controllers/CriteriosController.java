package domain.controllers;

import criterioOperacion.CategoriaCriterio;
import criterioOperacion.Criterio;
import domain.entities.operaciones.Item;
import domain.entities.tipoEntidadJuridica.Categoria;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

public class CriteriosController {

    private ModalAndViewController modalAndViewController;
    private Repositorio<Criterio> repoCriterio;
    private OperadorController operadorController;

    public CriteriosController(ModalAndViewController modalAndViewController, OperadorController operadorController){
        this.repoCriterio = FactoryRepositorio.get(Criterio.class);
        this.modalAndViewController = modalAndViewController;
        this.operadorController = operadorController;
    }

    private ModelAndView modalAndViewCriterios(){

        return new ModelAndView(modalAndViewController.getParametros(), "criterios.hbs");
    }

    public ModelAndView criterios(Request request, Response response)throws Exception {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewCriterios());
    }

    public ModelAndView guardarCriterio(Request request, Response response){
        String nombreCriterio = request.queryParams("nombreCriterio");

        List<CategoriaCriterio> listaCategoriasCriterio = obtenerYGenerarListaCategoriasCriterio(request);

        Criterio criterioAGuardar = new Criterio(nombreCriterio, listaCategoriasCriterio);

        if (operadorController.persistenciaNoValida(repoCriterio, criterioAGuardar)){
            modalAndViewController.getParametros().put("mensaje", "No se guardaron los datos correctamente, intentelo nuevamente.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        // Se persisitio correctamente
        modalAndViewController.getParametros().put("mensaje","Los datos se guardaron correctamente.");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");
    }

    // Leer la lista de la ventana criterios
    private List<CategoriaCriterio> obtenerYGenerarListaCategoriasCriterio(Request request){
        List<CategoriaCriterio> categoriasLista = new ArrayList<>();

        String categoriasString = request.queryParams("categoriasCriterio");

        String[] categorias;

        try{
            categorias = categoriasString.split("=");
        } catch (Exception e){
            System.out.println(e.getMessage());
            return categoriasLista;
        }

        for (String unaCategoria : categorias) {
            categoriasLista.add(new CategoriaCriterio(unaCategoria, null));
        }

        return categoriasLista;
    }
}
