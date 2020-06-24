package TipoEntidadJuridica;

import Entidades.Afip;

public class Empresa extends TipoEntidadJuridica {

    private int promedioVentasAnuales;
    private int cantidadPersonal;
    private Categoria categoria;

    private Sector sector;

    //Agregué el constructor
    public Empresa(Sector sector, int promedioVentasAnuales, int cantidadPersonal) {
        this.sector = sector;
        this.promedioVentasAnuales = promedioVentasAnuales;
        this.cantidadPersonal = cantidadPersonal;
        calcularCategoria();
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