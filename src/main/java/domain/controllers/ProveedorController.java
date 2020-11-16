package domain.controllers;

import domain.entities.apiMercadoLibre.Direccion;
import domain.entities.apiMercadoLibre.DireccionPostal;
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
    private OperadorController operadorController;

    public ProveedorController(ModalAndViewController modalAndViewController, OperadorController operadorController) {

        this.repoProveedor = FactoryRepositorio.get(Proveedor.class);
        this.modalAndViewController = modalAndViewController;
        this.operadorController = operadorController;

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

    public ModelAndView guardarProveedor(Request request, Response response){

        String nombre = request.queryParams("nombre");
        String apellido = request.queryParams("apellido");
        String razonSocial = request.queryParams("razonSocial");
        String cuit_cuilString = request.queryParams("cuit_cuil");
        String calle = request.queryParams("calle");
        String alturaString = request.queryParams("altura");
        String pisoString = request.queryParams("piso");

        int cuit_cuil = Integer.parseInt(cuit_cuilString);
        int altura = Integer.parseInt(alturaString);
        int piso = Integer.parseInt(pisoString);

        DireccionPostal direccionPostal = new DireccionPostal();

        Direccion direccion = new Direccion(calle, altura, piso, null);
        direccionPostal.setDireccion(direccion);

        Proveedor proveedorAGuardar = new Proveedor(nombre, apellido, cuit_cuil, direccionPostal, razonSocial);

        if (operadorController.persistenciaNoValida(repoProveedor, proveedorAGuardar)){
            modalAndViewController.getParametros().put("mensaje", "No se guardaron los datos, intentelo nuevamente.");
            return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
        }

        modalAndViewController.getParametros().put("mensaje", "Se persistio correctamente el proveedor.");
        return new ModelAndView(modalAndViewController.getParametros(), "modalInformativo2.hbs");
    }

}
