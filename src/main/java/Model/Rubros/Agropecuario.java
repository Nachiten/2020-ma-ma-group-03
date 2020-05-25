package Model.Rubros;

import Model.Categoria.TipoEmpresa;

public class Agropecuario extends Rubro{

    public TipoEmpresa clasificacion() {

        TipoEmpresa tipoEmpresa = null;

        if(this.promedioVentasAnuales <= 12890000 && this.cantPersonal <= 5){
            tipoEmpresa = TipoEmpresa.MICRO;
        }
        if(this.promedioVentasAnuales <= 48480000 && this.cantPersonal <= 10){
            tipoEmpresa = TipoEmpresa.PEQUENA;
        }
        if(this.promedioVentasAnuales <= 345430000 && this.cantPersonal <= 50){
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO1;
        }
        if(this.promedioVentasAnuales <= 547890000 && this.cantPersonal <= 215){
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO2;
        }
        return tipoEmpresa;
    }
}