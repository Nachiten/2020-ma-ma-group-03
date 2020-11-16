package domain.controllers;

import domain.entities.operaciones.TipoMedioDePago;
import domain.entities.vendedor.Proveedor;
import domain.repositories.Repositorio;
import domain.repositories.factories.FactoryRepositorio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.List;

public class ProveedoresController {

    private ModalAndViewController modalAndViewController;
    private Repositorio<Proveedor> repoProveedor;

    public ProveedoresController(ModalAndViewController modalAndViewController){
        this.modalAndViewController = modalAndViewController;
        this.repoProveedor = FactoryRepositorio.get(Proveedor.class);;
    }

    public ModelAndView listarProveedores(Request request, Response response){
        List<Proveedor> proveedores = repoProveedor.buscarTodos();
        modalAndViewController.cargarParametosHashMap();
        modalAndViewController.getParametros().put("proveedores", proveedores);
        return new ModelAndView(modalAndViewController.getParametros(),"listadoDeProveedores.hbs");
    }

    public ModelAndView altaProveedor(Request request, Response response){
        return new ModelAndView(modalAndViewController.getParametros(),"altaProveedor.hbs");
    }
}
