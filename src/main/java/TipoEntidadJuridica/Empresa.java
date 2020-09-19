package TipoEntidadJuridica;

import Entidades.Afip;

import javax.persistence.*;

@Entity
@Table(name = "empresa")
public class Empresa extends TipoEntidadJuridica {

    @Column(name = "promedioVentasAnuales")
    private int promedioVentasAnuales;

    @Column(name = "cantidadPersonal")
    private int cantidadPersonal;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Categoria categoria;

    @ManyToOne(cascade = {CascadeType.ALL})
    private final Sector sector;

    //Agregué el constructor

    public Empresa() {
    }

    public Empresa(Sector sector, int promedioVentasAnuales, int cantidadPersonal) {
        this.sector = sector;
        this.promedioVentasAnuales = promedioVentasAnuales;
        this.cantidadPersonal = cantidadPersonal;
    }

    private void calcularCategoria(){
        this.categoria = Afip.clasificacion(this);
    }

    public Sector getSector() {
        return sector;
    }

    public Categoria getCategoria() { return categoria; }

    public void setCantidadPersonal(int cantidadPersonal) {
        this.cantidadPersonal = cantidadPersonal;
        calcularCategoria();
    }

    public void setPromedioVentasAnuales(int promedioVentasAnuales) {
        this.promedioVentasAnuales = promedioVentasAnuales;
        calcularCategoria();
    }

    public int getCantidadPersonal() {
        return cantidadPersonal;
    }

    public int getPromedioVentasAnuales() {
        return promedioVentasAnuales;
    }


}