function esVacio(string){
    return string === '';
}

function mostrarModalGuardadoEgreso() {
    //var datos = recuperarDatosFormularioEgresos();

    let datos = new FormData($("#formularioEgresos")[0]);

    datos.append( 'preciosItems', datosDeTablaPorNombreDeClase(".precioItem") );
    datos.append( 'nombresItems', datosDeTablaPorNombreDeClase(".nombreItem") );
    datos.append( 'cantidadesItems', datosDeTablaPorNombreDeClase(".cantidadItem") );
    datos.append( 'nombresCatego rias', datosNombresCategorias() );

    /*
    if (esVacio(datos.fecha) || esVacio(datos.numeroMedioDePago) ||
        esVacio(datos.numeroDocumentoComercial) || esVacio(datos.presupuestosRequeridos)){
        console.log("Falta completar algun campo.");
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
        contentType: false,
        processData: false,
        success  : function (result) {
            showInModal("modal", result);
        }
    });
}



/*
function recuperarDatosFormularioEgresos(){
    var datos = {
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
        //archivo : $('input[name^="documentoSubido"]')[0].files[0]
    };

    var datos = new FormData();

    $.each($('input[name^="documentoSubido"]')[0].files, function(i, file) {
        datos.append('file-' + i, file);
    });

    return datos;
}
*/

function mostrarFiltradoEgresos() {
    var datos = recuperarCategoria();

    //acá yo le digo che loco, hace un post sobre tal ruta!
    var ruta = "/egresos/nombreCategoria";
    var metodo = "POST";
    $.ajax({
        type       : metodo,
        url        : ruta,
        datatype   : "html",
        data       : datos,
        success    : function (result) {
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

function subirDocumento(idOperacion){
    let datos = new FormData($("#formularioArchivo" + idOperacion)[0]);

    var ruta = "/guardarDocumentoEgreso";
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        data     : datos,
        contentType: false,
        processData: false,
        success  : function (result) {
            showInModal("modal", result);
            // TODO | Se debe actualizar la pagina
            //recargarPaginaLuegoDeSubidoDocumento();
        }
    });
}

function recargarPaginaLuegoDeSubidoDocumento() {
    var ruta = "/hola";
    var metodo = "GET";
    $.ajax({
        type: metodo,
        url: ruta,
        dataType: "html",
        success : function(result){
            console.log("hola");
            showInModal("modal3", result);
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