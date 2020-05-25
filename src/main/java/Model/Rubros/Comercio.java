package Model.Rubros;

import Model.Categoria.TipoEmpresa;

public class Comercio extends Rubro{

    public TipoEmpresa clasificacion() {

        TipoEmpresa tipoEmpresa = null;

        if(this.promedioVentasAnuales <= 29740000 && this.cantPersonal <= 7){
            tipoEmpresa = TipoEmpresa.MICRO;
        }
        if(this.promedioVentasAnuales <= 178860000 && this.cantPersonal <= 35){
            tipoEmpresa = TipoEmpresa.PEQUENA;
        }
        if(this.promedioVentasAnuales <= 1502750000 && this.cantPersonal <= 125){
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO1;
        }
        if(this.promedioVentasAnuales <= 2146810000 && this.cantPersonal <= 345){
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO2;
        }
        return tipoEmpresa;
    }
}
