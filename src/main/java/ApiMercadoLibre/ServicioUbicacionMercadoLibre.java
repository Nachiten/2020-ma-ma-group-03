package ApiMercadoLibre;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ServicioUbicacionMercadoLibre {

    private static ServicioUbicacionMercadoLibre instancia = null;
    private Retrofit retrofit;

    private ServicioUbicacionMercadoLibre() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mercadolibre.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioUbicacionMercadoLibre instancia(){
        if(instancia== null){
            instancia = new ServicioUbicacionMercadoLibre();
        }
        return instancia;
    }

    // Listar todos los paises
    public List<Pais> listadoDePaises() throws IOException {
        UbicacionService ubicacionService = this.retrofit.create(UbicacionService.class);
        Call<List<Pais>> pedidoPaises = ubicacionService.paises();
        Response<List<Pais>> responseProvinciasArgentinas = pedidoPaises.execute();
        return responseProvinciasArgentinas.body();
    }

    // Listar los estados a partir de un pais
    public InfoPais listadoEstadosDePais(String nombrePais) throws IOException {
        UbicacionService ubicacionService = this.retrofit.create(UbicacionService.class);
        Call<InfoPais> pedidoUnPais = ubicacionService.provincias( nombrePais, "id, name, locale, states");
        Response<InfoPais> respuestaPedidoPais = pedidoUnPais.execute();
        return respuestaPedidoPais.body();
    }

    // Listar todas las ciudades a partir de un estado
    public InfoEstado listadoCiudadesDeEstado(String nombreEstado) throws IOException {
        UbicacionService ubicacionService = this.retrofit.create(UbicacionService.class);
        Call<InfoEstado> pedidoMonedas = ubicacionService.estados(nombreEstado, "id, name, cities");
        Response<InfoEstado> respuestaMonedas = pedidoMonedas.execute();
        return respuestaMonedas.body();
    }

    // Listar todas las monedas
    public List<InfoMoneda> listadoMonedas() throws IOException {
        UbicacionService ubicacionService = this.retrofit.create(UbicacionService.class);
        Call<List<InfoMoneda>> pedidoMonedas = ubicacionService.monedas();
        Response<List<InfoMoneda>> respuestaMonedas = pedidoMonedas.execute();
        return respuestaMonedas.body();
    }


}
