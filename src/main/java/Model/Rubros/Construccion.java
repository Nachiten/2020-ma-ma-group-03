package Model.Rubros;

import Model.Categoria.TipoEmpresa;

public class Construccion extends Rubro{

    public TipoEmpresa clasificacion() {

        TipoEmpresa tipoEmpresa = null;

        if(this.promedioVentasAnuales <= 15230000 && this.cantPersonal <= 12){
            tipoEmpresa = TipoEmpresa.MICRO;
        }
        if(this.promedioVentasAnuales <= 90310000 && this.cantPersonal <= 45){
            tipoEmpresa = TipoEmpresa.PEQUENA;
        }
        if(this.promedioVentasAnuales <= 503880000 && this.cantPersonal <= 200){
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO1;
        }
        if(this.promedioVentasAnuales <= 755740000 && this.cantPersonal <= 590){
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO2;
        }
        return tipoEmpresa;
    }
}
