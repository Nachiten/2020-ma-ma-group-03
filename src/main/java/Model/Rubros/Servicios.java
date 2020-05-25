package Model.Rubros;

import Model.Categoria.TipoEmpresa;

public class Servicios extends Rubro{

    public TipoEmpresa clasificacion() {

        TipoEmpresa tipoEmpresa = null;

        if(this.promedioVentasAnuales <= 8500000 && this.cantPersonal <= 7){
            tipoEmpresa = TipoEmpresa.MICRO;
        }
        if(this.promedioVentasAnuales <= 50950000 && this.cantPersonal <= 30){
            tipoEmpresa = TipoEmpresa.PEQUENA;
        }
        if(this.promedioVentasAnuales <= 425170000 && this.cantPersonal <= 165){
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO1;
        }
        if(this.promedioVentasAnuales <= 607210000 && this.cantPersonal <= 535){
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO2;
        }
        return tipoEmpresa;
    }
}