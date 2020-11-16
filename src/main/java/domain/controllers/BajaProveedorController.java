package domain.controllers;

import domain.entities.usuarios.Usuario;
import domain.entities.vendedor.Proveedor;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BajaProveedorController {
    private Repositorio<Proveedor> repoProveedor;
    private Proveedor proveedor;
    private Map<String, Object> parametros;
    private Usuario usuario;
    private ContextoDeUsuarioLogueado contextoDeUsuarioLogueado;


    public BajaProveedorController(ContextoDeUsuarioLogueado contextoDeUsuarioLogueado) {

        this.repoProveedor = FactoryRepositorio.get(Proveedor.class);
        this.proveedor = new Proveedor();
        this.parametros = new HashMap<>();
        this.contextoDeUsuarioLogueado = contextoDeUsuarioLogueado;

    }

    private ModelAndView siElUsuarioEstaLogueadoRealiza(Request request, Supplier<ModelAndView> bloque){

        if(!contextoDeUsuarioLogueado.esValidoElUsuarioLogueadoEn(request)){
            return new ModelAndView(null,"error404.hbs");
        }

        return bloque.get();
    }


    private void cargarParametosHashMap() throws Exception {

        usuario = contextoDeUsuarioLogueado.getUsuarioLogueado();
        parametros.put("nombre", usuario.getNombre());
        parametros.put("apellido", usuario.getApellido());
    }

    public ModelAndView modalAndViewListarProveedores(){

        List<Proveedor> proveedores = this.repoProveedor.buscarTodos();
        List<Proveedor> proveedoresHabilitados = proveedores.stream().filter(unProveedor-> unProveedor.getEstoyHabilitado()).collect(Collectors.toList());
        parametros.put("listadoProveedores", proveedoresHabilitados);
        return new ModelAndView(parametros, "bajaProveedor.hbs");

    }

    public ModelAndView listarProveedores(Request request, Response response) throws Exception {
        //problema que me carga los datos del proveedor en nombre y apellido
        cargarParametosHashMap();
        return siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewListarProveedores());
    }
    public ModelAndView eliminar(Request request, Response response) {
        int idBuscado = Integer.parseInt(request.params("id"));
        Proveedor proveedorBuscado = this.repoProveedor.buscar(idBuscado);

        proveedorBuscado.cambiarAInhabilitado();

        this.repoProveedor.modificar(proveedorBuscado);

        parametros.put("mensaje","El proveedor se eliminó correctamente");
        return new ModelAndView(parametros,"modalInformativo2.hbs") ;
    }

}
