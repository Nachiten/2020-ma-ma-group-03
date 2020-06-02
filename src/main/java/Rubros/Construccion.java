package Rubros;

import Categoria.TipoEmpresa;

public class Construccion extends EstrategiaDeRubro {

        @Override
        public TipoEmpresa clasificacion() {
            return tipoDeEmpresa(this.promedioVentasAnuales, this.cantPersonal);
        }

        @Override
        public int tipoDeEmpresaPorVentasAnuales(int promedioVentasAnuales){

            TipoEmpresa tipoEmpresa = null;

            if(promedioVentasAnuales <= 15230000)
                tipoEmpresa = TipoEmpresa.MICRO;
            if(promedioVentasAnuales <= 90310000)
                tipoEmpresa = TipoEmpresa.PEQUENA;
            if(promedioVentasAnuales <= 503880000)
                tipoEmpresa = TipoEmpresa.MEDIANATRAMO1;
            if(promedioVentasAnuales <= 755740000)
                tipoEmpresa = TipoEmpresa.MEDIANATRAMO2;

            return tipoEmpresa.ordinal();
        }


        @Override
        public int tipoDeEmpresaPorCantidadPersonal(int cantidadPersonal){

            TipoEmpresa tipoEmpresa = null;

            if(promedioVentasAnuales <= 12)
                tipoEmpresa = TipoEmpresa.MICRO;
            if(promedioVentasAnuales <= 45)
                tipoEmpresa = TipoEmpresa.PEQUENA;
            if(promedioVentasAnuales <= 200)
                tipoEmpresa = TipoEmpresa.MEDIANATRAMO1;
            if(promedioVentasAnuales <= 590)
                tipoEmpresa = TipoEmpresa.MEDIANATRAMO2;

            return tipoEmpresa.ordinal();
        }
}
