package domain.controllers;

import domain.entities.vendedor.Proveedor;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProveedorController {
    private Repositorio<Proveedor> repoProveedor;
    private Map<String, Object> parametros;
    private ModalAndViewController modalAndViewController;


    public ProveedorController(ModalAndViewController modalAndViewController) {

        this.repoProveedor = FactoryRepositorio.get(Proveedor.class);
        this.parametros = new HashMap<>();
        this.modalAndViewController = modalAndViewController;

    }

    public ModelAndView modalAndViewListarProveedores(){

        List<Proveedor> proveedores = this.repoProveedor.buscarTodos();
        List<Proveedor> proveedoresHabilitados = proveedores.stream().filter(unProveedor-> unProveedor.getEstoyHabilitado()).collect(Collectors.toList());
        parametros.put("listadoProveedores", proveedoresHabilitados);
        return new ModelAndView(parametros, "bajaProveedor.hbs");

    }

    public ModelAndView listarProveedores(Request request, Response response) {
        //problema que me carga los datos del proveedor en nombre y apellido
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, () -> modalAndViewListarProveedores());
    }

    public ModelAndView eliminar(Request request, Response response) {
        int idBuscado = Integer.parseInt(request.params("id"));
        Proveedor proveedorBuscado = this.repoProveedor.buscar(idBuscado);

        proveedorBuscado.cambiarAInhabilitado();

        this.repoProveedor.modificar(proveedorBuscado);

        parametros.put("mensaje","El proveedor se eliminó correctamente");
        return new ModelAndView(parametros,"modalInformativo2.hbs") ;
    }

    public ModelAndView altaProveedor(Request request, Response response){
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modelAndViewAltaProveedor);
    }

    public ModelAndView modelAndViewAltaProveedor(){
        return new ModelAndView(parametros,"altaProveedor.hbs");
    }

}
