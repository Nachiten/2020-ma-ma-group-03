package TipoEntidadJuridica;

import java.util.List;
import java.util.Optional;

public class Sector {
    int promedioVentasAnuales;
    int cantidadPersonal;
    List<Categoria> categorias;

    public String categoria(){
        Optional<Categoria> categoriaQueCumpleCondicion = categorias.stream().filter(categoria -> categoria.cumploConCategoria(promedioVentasAnuales, cantidadPersonal)).findFirst();
        return categoriaQueCumpleCondicion.get().getNombre();
    }
}