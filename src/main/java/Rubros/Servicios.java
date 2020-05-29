package Rubros;

import Categoria.TipoEmpresa;

public class Servicios extends Rubro{

    public TipoEmpresa clasificacion() {

        TipoEmpresa tipoEmpresa = null;

        if(cumpleCondicionCon(8500000, 7)){
            tipoEmpresa = TipoEmpresa.MICRO;
        }
        if(cumpleCondicionCon(50950000, 30)){
            tipoEmpresa = TipoEmpresa.PEQUENA;
        }
        if(cumpleCondicionCon(425170000, 165)){
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO1;
        }
        if(cumpleCondicionCon(607210000, 535)){
            tipoEmpresa = TipoEmpresa.MEDIANATRAMO2;
        }
        return tipoEmpresa;
    }
}