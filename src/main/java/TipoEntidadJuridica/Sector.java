package TipoEntidadJuridica;

import java.util.List;
import java.util.Optional;

public class Sector {
    int promedioVentasAnuales;
    int cantidadPersonal;
    List<Categoria> categorias;

    public Categoria categoria(){
        Optional<Categoria> categoriaQueCumpleCondicion = categorias.stream().filter(categoria -> categoria.cumploConCategoria(promedioVentasAnuales, cantidadPersonal)).findFirst();
        return categoriaQueCumpleCondicion.get();
    }

    public Sector(int promedioVentasAnuales, int cantidadPersonal) {
        this.promedioVentasAnuales = promedioVentasAnuales;
        this.cantidadPersonal = cantidadPersonal;
    }

    //Agregué método
    public void addCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}