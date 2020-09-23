package TipoEntidadJuridica;

import Persistencia.EntidadPersistente;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "sector")
public class Sector extends EntidadPersistente {
    @OneToMany(mappedBy = "sectorAsociado", cascade = {CascadeType.ALL})
    private List<Categoria> categorias;

    @Column (name = "nombre")
    private String nombre;

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

        for (Categoria unaCategoria: categorias){
            unaCategoria.setSectorAsociado(this);
        }
    }

    public Sector(String nombreSector){
        this.categorias = new ArrayList<>();
        this.nombre = nombreSector;
    }

    public Sector(){
        this.categorias = new ArrayList<>();
    }


}