function mostrarModalGuardadoIngreso() {
    var datos = recuperarDatosFormularioIngresos();
    var ruta = "/ingresos";
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

function recuperarDatosFormularioIngresos(){
    return {
        fecha: valorDe("alta-fecha"),
        periodoDeAceptacion: valorDe("alta-periodoDeAceptacion"),
        monto: valorDe("alta-monto"),
        moneda: valorDe("alta-moneda"),
        descripcion: valorDe("alta-descripcion")
    };
}

function mostrarDetallesIngreso(id) {

    //acá yo le digo che loco, hace un post sobre tal ruta!
    var ruta = "/ingresos/detalle/" + id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal", result);
        }
    });
}

function mostrarOperacionesVinculadas(id) {

    //acá yo le digo che loco, hace un post sobre tal ruta!
    var ruta = "/ingresos/egresos/" + id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal", result);
        }
    });
}
