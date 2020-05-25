package Model.Rubros;

import Model.Categoria.TipoEmpresa;

public class IndustriaYMineria extends Rubro{

    public TipoEmpresa clasificacion() {

        TipoEmpresa tipoEmpresa = null;

        if(this.promedioVentasAnuales <= 26540000 && this.cantPersonal <= 15){
            tipoEmpresa = TipoEmpresa.MICRO;
        }
        if(this.promedioVentasAnuales <= 190410000 && this.cantPersonal <= 60){
            tipoEmpresa = TipoEmpresa.PEQUENA;
        }
        if(this.promedioVentasAnuales <= 1190330000 && this.cantPersonal <= 235){
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO1;
        }
        if(this.promedioVentasAnuales <= 1739590000 && this.cantPersonal <= 655){
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO2;
        }
        return tipoEmpresa;
    }
}
