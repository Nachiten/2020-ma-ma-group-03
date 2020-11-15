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
    private Repositorio<TipoMedioDePago> repoTipoMedioPago;
    private Repositorio<TipoDocumentoComercial> repoTipoDocComercial;
    private Repositorio<Criterio> repoCriterio;
    private Repositorio<CategoriaCriterio> repoCategoriaCriterio;
    private ModalAndViewController modalAndViewController;
    private Repositorio<Presupuesto> repoPresupuesto;

    public PresupuestosController(ModalAndViewController modalAndViewController){

        this.repoTipoMedioPago = FactoryRepositorio.get(TipoMedioDePago.class);
        this.repoTipoDocComercial = FactoryRepositorio.get(TipoDocumentoComercial.class);
        this.repoOperacionEgreso = FactoryRepositorio.get(OperacionDeEgreso.class);
        this.repoCategoriaCriterio = FactoryRepositorio.get(CategoriaCriterio.class);
        this.repoPresupuesto = FactoryRepositorio.get(Presupuesto.class);
        this.repoCriterio = FactoryRepositorio.get(Criterio.class);
        this.modalAndViewController = modalAndViewController;
    }

    private ModelAndView modalAndViewPresupuestos(){

        List<OperacionDeEgreso> operacionesEgreso = this.repoOperacionEgreso.buscarTodos();
        List<TipoDocumentoComercial> tiposDocumentoComercial = this.repoTipoDocComercial.buscarTodos();
        List<Criterio> criterios = this.repoCriterio.buscarTodos();
        List<Criterio> criterios2 = quitarMitad(criterios);

        modalAndViewController.getParametros().put("operacionesEgreso", operacionesEgreso);
        modalAndViewController.getParametros().put("tiposDocumentoComercial", tiposDocumentoComercial);
        modalAndViewController.getParametros().put("criterios", criterios);
        modalAndViewController.getParametros().put("criterios2", criterios2);

        return new ModelAndView(modalAndViewController.getParametros(), "presupuestos.hbs");
    }

    public ModelAndView presupuestos(Request request, Response response)throws Exception {

        modalAndViewController.cargarParametosHashMap();
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewPresupuestos());

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

        if (noEligioDocumentoComercial(tipoDocumentoComercialString)){
            // No se inserto documento comercial
            modalAndViewController.getParametros().put("mensaje", "No se inserto documento comercial.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        // Leo operacion de egreso asociada
        OperacionDeEgreso operacionEgresoAsociada = buscarOperacionEgreso(operacionEgresoString);

        // Leo todos los items
        List<Item> listaItems = obtenerListaItems(request);

        if (operacionEgresoAsociada == null){
            modalAndViewController.getParametros().put("mensaje", "Error al leer la operacion de egreso.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        if (!listasDeItemsIguales(listaItems, operacionEgresoAsociada.getItems())){
            // No se inserto documento comercial
            modalAndViewController.getParametros().put("mensaje", "Los items del presupuesto deben ser iguales a los del egreso.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        // TODO | Problema, se crea una copia de los items cuando realmente presupuesto
        //  y egreso deberian referenciar los mismositems
        // listaItems = operacionEgresoAsociada.getItems();

        // Leo las categorias
        List<CategoriaCriterio> categoriasCriterio = obtenerListaCategoriaCriterio(request);

        // Convierto los numeros
        float montoTotal = Float.parseFloat(montoTotalString);
        int numeroDocumentoComercial = Integer.parseInt(numeroDocumentoComercialString);

        // Genero tipoDocComercial
        TipoDocumentoComercial tipoDocComercial = buscarTipoDocComercial(tipoDocumentoComercialString);
        // Genero documento comercial
        DocumentoComercial documentoComercial = new DocumentoComercial(tipoDocComercial, numeroDocumentoComercial);

        Presupuesto presupuestoAGuardar = new Presupuesto();

        presupuestoAGuardar.setMontoTotal(montoTotal);
        presupuestoAGuardar.setDocumentoComercial(documentoComercial);
        presupuestoAGuardar.setListaCategoriaCriterio(categoriasCriterio);
        presupuestoAGuardar.setItems(listaItems);
        presupuestoAGuardar.setOperacionAsociada(operacionEgresoAsociada);

        if (!validarPersistencia(repoPresupuesto,presupuestoAGuardar)){
            modalAndViewController.getParametros().put("mensaje", "No se guardaron los datos correctamente, intentelo nuevamente.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        // Se persisitio correctamente
        modalAndViewController.getParametros().put("mensaje","Los datos se guardaron correctamente.");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs");
    }


    private List<Criterio> quitarMitad(List<Criterio> lista){
        List<Criterio> listaADevolver = new ArrayList<>();

        int listaSize = lista.size();

        int limite;
        if (listaSize % 2 == 0){
            // Caso par: listaSize / 2 - 1
            limite = listaSize / 2 - 1;
        } else {
            // Caso impar: listaSize / 2
            limite = listaSize / 2;
        }

        // Recorro la lista
        for (int i = listaSize - 1; i > limite;i--){
            Criterio miCriterio = lista.remove(i);

            listaADevolver.add(miCriterio);
        }
        return listaADevolver;
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

    private boolean noEligioOperacionEgreso(String operacionEgresoString){
        return operacionEgresoString.equals("-Seleccionar una operacion de egreso-");
    }

    private boolean noEligioDocumentoComercial(String documentoComercialString){
        return documentoComercialString.equals("Seleccionar documento comercial");
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

    // Obtener lista al insertar egreso,presupuesto
    private List<CategoriaCriterio> obtenerListaCategoriaCriterio(Request request){

        String categoriasLeidas = request.queryParams("nombresCategorias");

        String[] listaNombresCategorias = categoriasLeidas.split("=");

        List<CategoriaCriterio> listaADevolver = new ArrayList<>();
        List<CategoriaCriterio> categoriasCriterioTotales = this.repoCategoriaCriterio.buscarTodos();

        for(String unNombreCategoria : listaNombresCategorias){
            CategoriaCriterio categoriaEncontrada = encontrarCategoria(unNombreCategoria, categoriasCriterioTotales);
            listaADevolver.add(categoriaEncontrada);
        }

        return listaADevolver;
    }

    private CategoriaCriterio encontrarCategoria(String nombreCategoria, List<CategoriaCriterio> categorias){
        for(CategoriaCriterio unaCategoria : categorias){
            if (unaCategoria.getNombreCategoria().equals(nombreCategoria)){
                return unaCategoria;
            }
        }
        return null;
    }

    private List<Item> obtenerListaItems(Request request){
        List<Item> items = new ArrayList<>();

        String itemNombre;
        String itemPrecioString;

        for (int i = 0; i <30; i++){

            if ((itemNombre = request.queryParams("nombre_I[" + i + "]") ) != null){
                itemPrecioString = request.queryParams("precio_I[" + i + "]");
                float itemPrecio = Float.parseFloat(itemPrecioString);

                Item miItem = new Item(itemNombre, itemPrecio);

                items.add(miItem);
            }

        }

        return items;
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
