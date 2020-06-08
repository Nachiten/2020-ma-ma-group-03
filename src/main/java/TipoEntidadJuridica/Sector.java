package TipoEntidadJuridica;

import TipoEntidadJuridica.Categoria;

import java.util.List;
import java.util.Optional;

public class Sector {
    int promedioVentasAnuales;
    int cantidadPersonal;
    List<Categoria> categorias;

    public String categoria(){
    Optional<Categoria> categoriaQueCumpleCondicion = categorias.stream().filter(categoria -> categoria.cumploConCategoria(cantidadPersonal, cantidadPersonal)).findFirst();
    return categoriaQueCumpleCondicion.get().getNombre();
    }
}