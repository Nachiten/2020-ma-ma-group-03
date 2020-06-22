package TipoEntidadJuridica;

public class Categoria {
    String nombre;
    int ventasAnualesMaximas;
    int cantidadPersonalMaximo;

    //Agregué el constructor
    public Categoria(String nombre, int ventasAnualesMaximas, int cantidadPersonalMaximo) {
        this.nombre = nombre;
        this.ventasAnualesMaximas = ventasAnualesMaximas;
        this.cantidadPersonalMaximo = cantidadPersonalMaximo;
    }

    public boolean cumploConCategoria(int ventasAnuales, int cantidadPersonal){
        return ventasAnuales <= ventasAnualesMaximas && cantidadPersonal <= cantidadPersonalMaximo;
    }

    public String getNombre(){
        return nombre;
    }
}
