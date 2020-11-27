function mostrarDetallesProveedor() {
    var datos = recuperarProveedor();

    //acá yo le digo che loco, hace un post sobre tal ruta!
    var ruta = "/egresos/proveedor";
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        data     : datos,
        success  : function (result) {
            showInModal("modal", result);
        }
    });
}

function recuperarProveedor(){
    return {
        proveedor: valorDe("alta-proveedor")
    };
}