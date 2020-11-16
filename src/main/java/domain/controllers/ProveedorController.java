package domain.controllers;

import domain.entities.usuarios.Usuario;
import domain.entities.vendedor.Proveedor;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.stream.Collectors;

public class ProveedorController {
    private Repositorio<Proveedor> repoProveedor;
    private ModalAndViewController modalAndViewController;


    public ProveedorController(ModalAndViewController modalAndViewController) {

        this.repoProveedor = FactoryRepositorio.get(Proveedor.class);
        this.modalAndViewController = modalAndViewController;

    }

    private ModelAndView modalAndViewListarProveedores(){

        List<Proveedor> proveedores = this.repoProveedor.buscarTodos();
        List<Proveedor> proveedoresHabilitados = proveedores.stream().filter(Proveedor::getEstoyHabilitado).collect(Collectors.toList());
        modalAndViewController.getParametros().put("listadoProveedores", proveedoresHabilitados);
        return new ModelAndView(modalAndViewController.getParametros(), "bajaProveedor.hbs");

    }

    public ModelAndView listarProveedores(Request request, Response response) throws Exception {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modalAndViewListarProveedores);
    }

    public ModelAndView eliminar(Request request, Response response) {
        int idBuscado = Integer.parseInt(request.params("id"));
        Proveedor proveedorBuscado = this.repoProveedor.buscar(idBuscado);
        proveedorBuscado.cambiarAInhabilitado();
        this.repoProveedor.modificar(proveedorBuscado);
        modalAndViewController.getParametros().put("mensaje","El proveedor se dio de baja correctamente");
        return new ModelAndView(modalAndViewController.getParametros(),"modalInformativo2.hbs") ;
    }

    private ModelAndView modelAndViewAltaProveedor(){
        return new ModelAndView(modalAndViewController.getParametros(),"altaProveedor.hbs");
    }
    public ModelAndView altaProveedor(Request request, Response response) throws Exception {
        return modalAndViewController.siElUsuarioEstaLogueadoRealiza(request, this::modelAndViewAltaProveedor);
    }
}
