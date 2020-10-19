package domain.entities.entidades;

import domain.entities.tipoEntidadJuridica.Categoria;
import domain.entities.tipoEntidadJuridica.Empresa;

public class Afip {

    public static Categoria clasificacion(Empresa empresa){
        return empresa.getSector().categoria(empresa);
    }
}