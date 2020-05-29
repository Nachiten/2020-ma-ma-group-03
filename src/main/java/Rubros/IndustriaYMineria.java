package Rubros;

import Categoria.TipoEmpresa;

public class IndustriaYMineria extends Rubro{

    public TipoEmpresa clasificacion() {

        TipoEmpresa tipoEmpresa = null;

        if(cumpleCondicionCon(26540000, 15)){
            tipoEmpresa = TipoEmpresa.MICRO;
        }
        if(cumpleCondicionCon(190410000, 60)){
            tipoEmpresa = TipoEmpresa.PEQUENA;
        }
        if(cumpleCondicionCon(1190330000, 235)){
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO1;
        }
        if(cumpleCondicionCon(1739590000, 655)){
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO2;
        }
        return tipoEmpresa;
    }
}
