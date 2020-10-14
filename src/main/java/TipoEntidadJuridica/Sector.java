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

    //-------------------------------------------------------------------------
                        //CONTRUCTOR
    //-------------------------------------------------------------------------

    public Sector(String nombreSector){
        this.nombre = nombreSector;
        inicializar();
    }

    public Sector(){
        inicializar();
    }

    //-------------------------------------------------------------------------
                        //METODOS
    //-------------------------------------------------------------------------

    private void inicializar(){
        this.categorias = new ArrayList<>();
    }

    public Categoria categoria(Empresa unaEmpresa){
        long promedioVentasAnuales = unaEmpresa.getPromedioVentasAnuales();
        int cantidadPersonal = unaEmpresa.getCantidadPersonal();

        Optional<Categoria> categoriaQueCumpleCondicion = categorias.stream().filter(categoria -> categoria.cumploConCategoria(promedioVentasAnuales, cantidadPersonal)).findFirst();
        return categoriaQueCumpleCondicion.get();
        //ojo con esto, rompe si no hay categorias para hacer el get!
    }

    public void addCategorias(Categoria ... categorias) {
        Collections.addAll(this.categorias, categorias);

        for (Categoria unaCategoria: categorias){
            unaCategoria.setSectorAsociado(this);
        }
    }

    //-------------------------------------------------------------------------
                            //GETTERS
    //-------------------------------------------------------------------------


    public String getNombre() { return nombre; }
}