function esVacio(string){
    return string === '';
}

function mostrarModalGuardadoEgreso() {
    var datos = recuperarDatosFormularioEgresos();

    if (esVacio(datos.fecha) || esVacio(datos.numeroMedioDePago) ||
        esVacio(datos.numeroDocumentoComercial) || esVacio(datos.presupuestosRequeridos)){
        console.log("Falta completar algun campo");
        return;
    }

    /*if (datos.nombresItems === ''){
        alert("Se debe insertar al menos un item.");
        console.log("No habia items, cancelo post");
        return;
    }*/

    // Hago el post hacia la ruta de egresos
    var ruta = "/egresos";
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

function recuperarDatosFormularioEgresos(){
    return {
        fecha: valorDe("alta-fecha"),
        medioDePago: valorDe("alta-medioDePago"),
        numeroMedioDePago: valorDe("alta-numeroMedioDePago"),
        documentoComercial: valorDe("alta-documentoComercial"),
        numeroDocumentoComercial: valorDe("alta-numeroDocumentoComercial"),
        presupuestosRequeridos: valorDe("alta-presupuestosRequeridos"),
        proveedor: valorDe("alta-proveedor"),
        revisor: valorDe("alta-revisor"),
        preciosItems: datosDeTablaPorNombreDeClase(".precioItem"),
        nombresItems: datosDeTablaPorNombreDeClase(".nombreItem"),
        cantidadesItems: datosDeTablaPorNombreDeClase(".cantidadItem"),
        nombresCategorias: datosNombresCategorias()
    };
}

function mostrarFiltradoEgresos() {
    var datos = recuperarCategoria();

    //acá yo le digo che loco, hace un post sobre tal ruta!
    var ruta = "/egresos/nombreCategoria";
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

function recuperarCategoria(){
    return {
        nombreCategoria: valorDe("alta-nombreCategoria")
    };
}

function mostrarDetallesEgreso(id) {

    //acá yo le digo che loco, hace un post sobre tal ruta!
    var ruta = "/egresos/detalle/" + id;
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

function mostrarItemsEgreso(id) {

    //acá yo le digo che loco, hace un post sobre tal ruta!
    var ruta = "/egresos/items/" + id;
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

function mostrarCategoriasEgreso(id) {

    //acá yo le digo che loco, hace un post sobre tal ruta!
    var ruta = "/egresos/categorias/" + id;
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

function mostrarRevisoresEgreso(id) {

    //acá yo le digo che loco, hace un post sobre tal ruta!
    var ruta = "/egresos/revisores/" + id;
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

function mostrarPresupuestosEgreso(id) {

    //acá yo le digo che loco, hace un post sobre tal ruta!
    var ruta = "/egresos/presupuestos/" + id;
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

function adjuntarArchivoAEgreso(){
    console.log("Tratando de adjuntar archivo");
}