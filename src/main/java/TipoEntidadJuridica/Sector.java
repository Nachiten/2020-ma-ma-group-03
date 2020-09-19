package TipoEntidadJuridica;

import Persistencia.EntidadPersistente;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.*;

@Entity
@Table(name = "sector")
public class Sector extends EntidadPersistente {
    @Transient
    private Collection<Categoria> categorias;

    public Categoria categoria(Empresa unaEmpresa){
        int promedioVentasAnuales = unaEmpresa.getPromedioVentasAnuales();
        int cantidadPersonal = unaEmpresa.getCantidadPersonal();

        Optional<Categoria> categoriaQueCumpleCondicion = categorias.stream().filter(categoria -> categoria.cumploConCategoria(promedioVentasAnuales, cantidadPersonal)).findFirst();
        return categoriaQueCumpleCondicion.get();
        //ojo con esto, rompe si no hay categorias para hacer el get!
    }

    //Agregué método
    public void addCategorias(Categoria ... categorias) {
        Collections.addAll(this.categorias, categorias);
    }

    public Sector(){
        this.categorias = new ArrayList<>(Collections.emptyList());
    }


}