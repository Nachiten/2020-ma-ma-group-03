package domain.controllers;

import criterioOperacion.CategoriaCriterio;
import criterioOperacion.Criterio;
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

    public CriteriosController(ModalAndViewController modalAndViewController){
        this.repoCriterio = FactoryRepositorio.get(Criterio.class);
        this.modalAndViewController = modalAndViewController;
    }

    private ModelAndView modalAndViewCriterios(){

        return new ModelAndView(modalAndViewController.getParametros(), "criterios.hbs");
    }

    public ModelAndView criterios(Request request, Response response)throws Exception {

        modalAndViewController.cargarParametosHashMap();
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewCriterios());
    }

    public ModelAndView guardarCriterio(Request request, Response response){
        String nombreCriterio = request.queryParams("nombreCriterio");

        List<CategoriaCriterio> listaCategoriasCriterio = obtenerYGenerarListaCategoriasCriterio(request);

        Criterio criterioAGuardar = new Criterio(nombreCriterio, listaCategoriasCriterio);

        if (!validarPersistencia(repoCriterio,criterioAGuardar)){
            modalAndViewController.getParametros().put("mensaje", "No se guardaron los datos correctamente, intentelo nuevamente.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        // Se persisitio correctamente
        modalAndViewController.getParametros().put("mensaje","Los datos se guardaron correctamente.");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");
    }

    // Leer la lista de la ventana criterios
    private List<CategoriaCriterio> obtenerYGenerarListaCategoriasCriterio(Request request){
        List<CategoriaCriterio> categorias = new ArrayList<>();

        String nombreCategoria;

        for(int i = 0 ; i < 30;i++){

            if ((nombreCategoria = request.queryParams("nombre_I[" + i + "]") ) != null) {
                CategoriaCriterio categoria = new CategoriaCriterio(nombreCategoria, null);

                categorias.add(categoria);
            }
        }

        return categorias;
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
}
