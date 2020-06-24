package TipoEntidadJuridica;

public class Categoria {
    private String nombre;
    private int ventasAnualesMaximas;
    private int cantidadPersonalMaximo;


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
