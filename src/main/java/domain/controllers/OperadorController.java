package domain.controllers;

import criterioOperacion.CategoriaCriterio;
import criterioOperacion.Criterio;
import domain.entities.apiMercadoLibre.*;
import domain.entities.operaciones.Item;
import domain.entities.operaciones.TipoDocumentoComercial;
import domain.entities.operaciones.TipoItem;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OperadorController {

    private Repositorio<TipoDocumentoComercial> repoTipoDocComercial;
    private Repositorio<CategoriaCriterio> repoCategoriaCriterio;
    private Repositorio<Pais> repoPais;
    private Repositorio<Estado> repoEstado;
    private Repositorio<Ciudad> repoCiudad;


    public OperadorController(){
        this.repoTipoDocComercial = FactoryRepositorio.get(TipoDocumentoComercial.class);
        this.repoCategoriaCriterio = FactoryRepositorio.get(CategoriaCriterio.class);
        this.repoPais = FactoryRepositorio.get(Pais.class);
        this.repoEstado = FactoryRepositorio.get(Estado.class);
        this.repoCiudad = FactoryRepositorio.get(Ciudad.class);
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
        String cantidadesItemsString = request.queryParams("cantidadesItems");

        String[] precios;
        String[] nombres;
        String[] cantidades;

        try {
            precios = preciosItemsString.split("=");
            nombres = nombresItemsString.split("=");
            cantidades = cantidadesItemsString.split("=");
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return items;
        }

        if(precios[0].equals("")){
            return items;
        }

        for (int i = 0; i < precios.length; i++){
            String unPrecioString = precios[i];
            String unNombre = nombres[i];
            String unaCantidadString = cantidades[i];

            float unPrecio = Float.parseFloat(unPrecioString);
            int cantidad = Integer.parseInt(unaCantidadString);

            Item miItem = new Item(TipoItem.PRODUCTO, unNombre, unPrecio, cantidad);

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

    public boolean noEligioDocumentoComercial(String documentoComercialString){
        return documentoComercialString.equals("Seleccionar documento comercial");
    }

    public float calcularMontoTotalDeItems(List<Item> items){

        float montoTotal = 0;

        for (Item unItem : items){
            montoTotal += unItem.getValor();
        }

        return montoTotal;
    }

    public DireccionPostal generarDireccionPostal(Request request) {
        String barrio = request.queryParams("barrio");
        String calle = request.queryParams("calle");
        String alturaString = request.queryParams("altura");
        String pisoString = request.queryParams("piso");
        String departamento = request.queryParams("departamento");

        int altura = Integer.parseInt(alturaString);
        int piso = -1;//Integer.parseInt(null);



        String idPais = request.queryParams("pais");
        String idProvincia = request.queryParams("provincia");
        String idCiudad = request.queryParams("ciudad");

        Pais paisElegido = repoPais.buscar(idPais);
        Estado provinciaElegida = repoEstado.buscar(idProvincia);
        Ciudad ciudadElegida = repoCiudad.buscar(idCiudad);

        Direccion direccion = new Direccion(calle, altura);

        if (!pisoString.equals("")){
            piso = Integer.parseInt(pisoString);
            direccion.setPiso(piso);
        }

        if (!departamento.equals("")){
            direccion.setDpto(departamento);
        }
        DireccionPostal direccionPostal = new DireccionPostal(direccion);
        direccionPostal.setPais(paisElegido);
        direccionPostal.setProvincia(provinciaElegida);
        direccionPostal.setCiudad(ciudadElegida);

        return direccionPostal;
    }
}