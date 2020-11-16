package domain.controllers;

import criterioOperacion.CategoriaCriterio;
import criterioOperacion.Criterio;
import domain.entities.operaciones.*;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

public class PresupuestosController {

    private Repositorio<OperacionDeEgreso> repoOperacionEgreso;
    private Repositorio<TipoDocumentoComercial> repoTipoDocComercial;
    private Repositorio<Criterio> repoCriterio;
    private ModalAndViewController modalAndViewController;
    private Repositorio<Presupuesto> repoPresupuesto;
    private OperadorController operadorController;

    public PresupuestosController(ModalAndViewController modalAndViewController, OperadorController operadorController){
        this.repoTipoDocComercial = FactoryRepositorio.get(TipoDocumentoComercial.class);
        this.repoOperacionEgreso = FactoryRepositorio.get(OperacionDeEgreso.class);
        this.repoPresupuesto = FactoryRepositorio.get(Presupuesto.class);
        this.repoCriterio = FactoryRepositorio.get(Criterio.class);
        this.modalAndViewController = modalAndViewController;
        this.operadorController = operadorController;
    }

    private ModelAndView modalAndViewPresupuestos(){

        List<OperacionDeEgreso> operacionesEgreso = this.repoOperacionEgreso.buscarTodos();
        List<TipoDocumentoComercial> tiposDocumentoComercial = this.repoTipoDocComercial.buscarTodos();
        List<Criterio> criterios = this.repoCriterio.buscarTodos();
        List<Criterio> criterios2 = operadorController.quitarMitad(criterios);

        modalAndViewController.getParametros().put("operacionesEgreso", operacionesEgreso);
        modalAndViewController.getParametros().put("tiposDocumentoComercial", tiposDocumentoComercial);
        modalAndViewController.getParametros().put("criterios", criterios);
        modalAndViewController.getParametros().put("criterios2", criterios2);

        return new ModelAndView(modalAndViewController.getParametros(), "presupuestos.hbs");
    }

    public ModelAndView presupuestos(Request request, Response response) {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewPresupuestos);
    }

    public ModelAndView guardarPresupuesto(Request request, Response response){
        // Leo query params
        String montoTotalString = request.queryParams("montoTotal");
        String tipoDocumentoComercialString = request.queryParams("documentoComercial");
        String numeroDocumentoComercialString = request.queryParams("numeroDocumentoComercial");
        String operacionEgresoString = request.queryParams("operacionEgreso");

        if (noEligioOperacionEgreso(operacionEgresoString)){
            modalAndViewController.getParametros().put("mensaje", "Se debe asociar con una operacion de egreso.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        if (operadorController.noEligioDocumentoComercial(tipoDocumentoComercialString)){
            // No se inserto documento comercial
            modalAndViewController.getParametros().put("mensaje", "No se inserto documento comercial.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        // Leo operacion de egreso asociada
        OperacionDeEgreso operacionEgresoAsociada = buscarOperacionEgreso(operacionEgresoString);

        // Leo todos los items
        List<Item> listaItems = operadorController.obtenerListaItems(request);

        if (operacionEgresoAsociada == null){
            modalAndViewController.getParametros().put("mensaje", "Error al leer la operacion de egreso.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        // TODO | No va comentado
        if (!listasDeItemsIguales(listaItems, operacionEgresoAsociada.getItems())){
            // No se inserto documento comercial
            modalAndViewController.getParametros().put("mensaje", "Los items del presupuesto deben ser iguales a los del egreso.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        // TODO | Problema, se crea una copia de los items cuando realmente presupuesto
        //  y egreso deberian referenciar los mismositems
        // listaItems = operacionEgresoAsociada.getItems();

        // Leo las categorias
        List<CategoriaCriterio> categoriasCriterio = operadorController.obtenerListaCategoriaCriterio(request);

        // Convierto los numeros
        float montoTotal = Float.parseFloat(montoTotalString);
        int numeroDocumentoComercial = Integer.parseInt(numeroDocumentoComercialString);

        // Genero tipoDocComercial
        TipoDocumentoComercial tipoDocComercial = operadorController.buscarTipoDocComercial(tipoDocumentoComercialString);
        // Genero documento comercial
        DocumentoComercial documentoComercial = new DocumentoComercial(tipoDocComercial, numeroDocumentoComercial);

        Presupuesto presupuestoAGuardar = new Presupuesto();

        presupuestoAGuardar.setMontoTotal(montoTotal);
        presupuestoAGuardar.setDocumentoComercial(documentoComercial);
        presupuestoAGuardar.setListaCategoriaCriterio(categoriasCriterio);
        presupuestoAGuardar.setItems(listaItems);
        presupuestoAGuardar.setOperacionAsociada(operacionEgresoAsociada);

        if (operadorController.persistenciaNoValida(repoPresupuesto, presupuestoAGuardar)){
            modalAndViewController.getParametros().put("mensaje", "No se guardaron los datos correctamente, intentelo nuevamente.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        // Se persisitio correctamente
        modalAndViewController.getParametros().put("mensaje","Los datos se guardaron correctamente.");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");
    }

    private boolean noEligioOperacionEgreso(String operacionEgresoString){
        return operacionEgresoString.equals("-Seleccionar una operacion de egreso-");
    }

    private boolean listasDeItemsIguales(List<Item> itemsPresupuesto, List<Item> itemsEgreso){

        if (itemsPresupuesto.size() != itemsEgreso.size()){
            return false;
        }

        List<Item> copiaItemsEgreso = new ArrayList<>(itemsEgreso);

        for (Item unItemPresupuesto : itemsPresupuesto) {
            if (!encontrarYEliminarDeLista(unItemPresupuesto, copiaItemsEgreso)){
                return false;
            }
        }

        return true;
    }

    private boolean encontrarYEliminarDeLista(Item itemPresupuesto, List<Item> itemsEgreso){
        for (Item unItemEgreso : itemsEgreso) {
            if (unItemEgreso.soyIgualA(itemPresupuesto)){
                itemsEgreso.remove(unItemEgreso);
                return true;
            }
        }
        return false;
    }

    private OperacionDeEgreso buscarOperacionEgreso(String idMasFecha){

        // Recorto el ID del string
        int indexDosPuntos = idMasFecha.indexOf(":");
        int indexPipe = idMasFecha.indexOf("|");
        String idString = idMasFecha.substring(indexDosPuntos + 2 , indexPipe - 1);

        int idOperacionEgresoBuscado = Integer.parseInt(idString);
        List<OperacionDeEgreso> operacionesEgreso = this.repoOperacionEgreso.buscarTodos();

        for ( OperacionDeEgreso unaOperacion : operacionesEgreso ) {
            if (unaOperacion.getIdOperacion() == idOperacionEgresoBuscado){
                return unaOperacion;
            }
        }

        return null;
    }

}
