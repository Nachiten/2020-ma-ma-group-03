package domain.controllers;

import criterioOperacion.CategoriaCriterio;
import criterioOperacion.Criterio;
import domain.entities.operaciones.Item;
import domain.entities.operaciones.TipoDocumentoComercial;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.Request;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OperadorController {

    private Repositorio<TipoDocumentoComercial> repoTipoDocComercial;
    private Repositorio<CategoriaCriterio> repoCategoriaCriterio;

    public OperadorController(){
        this.repoTipoDocComercial = FactoryRepositorio.get(TipoDocumentoComercial.class);
        this.repoCategoriaCriterio = FactoryRepositorio.get(CategoriaCriterio.class);
    }

    boolean persistenciaNoValida(Repositorio<?> objetoFactory, Object objetoClase){
        try {
            objetoFactory.agregar(objetoClase);
        }catch (Exception e){
            System.out.println("EXCEPCION: " + e.getMessage());
            return true;
        }
        return false;
    }

    LocalDate convertirAFecha(String fechaString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(fechaString, formatter);
    }

    public List<Item> obtenerListaItems(Request request){

        List<Item> items = new ArrayList<>();

        String preciosItemsString = request.queryParams("preciosItems");
        String nombresItemsString = request.queryParams("nombresItems");

        String[] precios = preciosItemsString.split("=");
        String[] nombres = nombresItemsString.split("=");

        for (int i = 0; i < precios.length; i++){
            String unPrecioString = precios[i];
            String unNombre = nombres[i];

            float unPrecio = Float.parseFloat(unPrecioString);

            Item miItem = new Item(unNombre, unPrecio);

            items.add(miItem);
        }

        return items;
    }

    private CategoriaCriterio encontrarCategoria(String nombreCategoria, List<CategoriaCriterio> categorias){
        for(CategoriaCriterio unaCategoria : categorias){
            if (unaCategoria.getNombreCategoria().equals(nombreCategoria)){
                return unaCategoria;
            }
        }
        return null;
    }

    // Obtener lista al insertar egreso,presupuesto
    List<CategoriaCriterio> obtenerListaCategoriaCriterio(Request request){

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

    TipoDocumentoComercial buscarTipoDocComercial(String nombre){
        List<TipoDocumentoComercial> tiposDocumentoComercial = this.repoTipoDocComercial.buscarTodos();

        for ( TipoDocumentoComercial unTipoDocComercial : tiposDocumentoComercial ) {
            if (unTipoDocComercial.getNombre().equals(nombre)){
                return unTipoDocComercial;
            }
        }
        return null;
    }

    // Quita la mitad de la lista y genera otra
    List<Criterio> quitarMitad(List<Criterio> lista){
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

    boolean noEligioDocumentoComercial(String documentoComercialString){
        return documentoComercialString.equals("Seleccionar documento comercial");
    }
}