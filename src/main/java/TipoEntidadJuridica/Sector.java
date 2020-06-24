package TipoEntidadJuridica;

import sun.util.resources.cldr.teo.CalendarData_teo_KE;

import java.util.*;

public class Sector {
    private List<Categoria> categorias;

    public Categoria categoria(Empresa unaEmpresa){
        int promedioVentasAnuales = unaEmpresa.getPromedioVentasAnuales();
        int cantidadPersonal = unaEmpresa.getCantidadPersonal();

        Optional<Categoria> categoriaQueCumpleCondicion = categorias.stream().filter(categoria -> categoria.cumploConCategoria(promedioVentasAnuales, cantidadPersonal)).findFirst();
        return categoriaQueCumpleCondicion.get();
    }

    //Agregué método
    public void addCategorias(Categoria ... categorias) {
        this.categorias.addAll(Arrays.asList(categorias));
    }
    // USO: addCategorias(cat1, cat2, cat3);

    public Sector(){
        this.categorias = new ArrayList<>(Collections.emptyList());
    }


}