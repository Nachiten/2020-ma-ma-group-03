function mostrarModalGuardadoPresupuestos() {
    var datos = recuperarDatosFormularioPresupuesto();

    if (datos.preciosItems === 'noHayPrecios'){
        alert("Se debe insertar al menos un item");
        console.log("No habia items, cancelo post");
        return;
    }

    var ruta = "/presupuestos";
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        data     : datos,
        success : function (result) {
            showInModal("modal", result);
        }
    });
}

function mostrarDetallesEgresoEnPresupuesto() {
    var datos = recuperarEgreso();

    //acá yo le digo che loco, hace un post sobre tal ruta!
    var ruta = "/presupuestos/egreso";
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

function mostrarModalPrespuestoConEgreso() {
    var datos = recuperarEgreso();
    //acá yo le digo che loco, hace un post sobre tal ruta!
    var ruta = "/presupuestos/operacionDeEgreso";
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

function recuperarEgreso(){
    return {
        operacionEgreso: valorDe("alta-operacionEgreso")
    };
}

function mostrarItemsPresupuesto(id) {

    //acá yo le digo che loco, hace un post sobre tal ruta!
    var ruta = "/presupuestos/items/" + id;
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

function mostrarCategoriaPresupuesto(id) {

    //acá yo le digo che loco, hace un post sobre tal ruta!
    var ruta = "/presupuestos/categorias/" + id;
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

function mostrarDetallesProveedorPresupuesto(id) {

    //acá yo le digo che loco, hace un post sobre tal ruta!
    var ruta = "/presupuestos/proveedor/" + id;
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
