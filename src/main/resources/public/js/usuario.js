function valorDe(unaVariableDelDocumento){
    return $("#"+unaVariableDelDocumento+"").val();
}

function modal_show(unModal){
    $("#"+unModal+"").modal('show');
}

function agregarA(unaVariableDelDocumento, unValor){
    $("#"+unaVariableDelDocumento+"").append(unValor);
}

function vaciar(unaVariableDelDocumento){
    $("#"+unaVariableDelDocumento+"").empty();
}

function showInModal(unModal, unContenido){
    vaciar(unModal);
    agregarA(unModal,unContenido);
    modal_show(unModal);
}

function cerrarSesion() {
    var ruta = "/";
    var metodo = "POST";
    $.ajax({
        type	: metodo,
        url 	: ruta,
        dataType: "html",
        success : function(result){
            showInModal("modal", result);
        }
    });
}

function verificarSesion() {
    var ruta = '/';
    var metodo = 'POST';
    $.ajax({
        type : metodo,
        url : ruta,
        datatype : "html",
        success : function (result) {
            showInModal("modal", result);

        }
    });
}

function recuperarDatosFormularioIngresos(){
    var datos = {
        fecha               : valorDe("alta-fecha"),
        periodoDeAceptacion : valorDe("alta-periodoDeAceptacion"),
        monto               : valorDe("alta-monto"),
        moneda              : valorDe("alta-moneda"),
        descripcion         : valorDe("alta-descripcion")
    };
    return datos;
}

function recuperarDatosFormularioEgresos(){
    var datos = {
        fecha                    : valorDe("alta-fecha"),
        montoTotal               : valorDe("alta-montoTotal"),
        medioDePago              : valorDe("alta-medioDePago"),
        numeroMedioDePago        : valorDe("alta-numeroMedioDePago"),
        documentoComercial       : valorDe("alta-documentoComercial"),
        numeroDocumentoComercial : valorDe("alta-numeroDocumentoComercial"),
        presupuestosRequeridos   : valorDe("alta-presupuestosRequeridos"),
        proveedor                : valorDe("alta-proveedor"),
        preciosItems : datosPreciosItems(),
        nombresItems : datosNombresItems(),
        nombresCategorias : datosNombresCategorias()
    };

    return datos;
}

function datosNombresCategorias(){
    var categorias = document.querySelectorAll(".categoria");

    var categoriasString = '';
    for (var x = 0; x < categorias.length; x++) {
        if (categorias[x].checked){
            categoriasString += categorias[x].name;
            categoriasString += '=';
        }

    }

    var cadenaADevolver = categoriasString.substring(0, categoriasString.length - 1);

    console.log("Categorias: " + cadenaADevolver);

    return cadenaADevolver;
}

function datosPreciosItems(){
    var precios = document.querySelectorAll(".precioItem");

    if (precios.length === 0){
        return 'noHayPrecios';
    }

    var preciosItemsValores = '';
    for (var x = 0; x < precios.length; x++) {
        preciosItemsValores += precios[x].value;
        if (x !== precios.length - 1){
            preciosItemsValores += '=';
        }
    }

    console.log("Precios: " + preciosItemsValores);

    return preciosItemsValores;
}

function datosNombresItems() {

    var nombres = document.querySelectorAll(".nombreItem");

    var nombresItemsValores = '';
    for (var x = 0; x < nombres.length; x++) {
        nombresItemsValores += nombres[x].value;
        if (x !== nombres.length - 1){
            nombresItemsValores += '=';
        }
    }

    console.log("Nombres: " + nombresItemsValores);

    return nombresItemsValores;
}

/*


String fechaString = request.queryParams("fecha");
String montoTotalString = request.queryParams("montoTotal");

String tipoMedioDePagoString = request.queryParams("medioDePago");
String numeroMedioDePagoString = request.queryParams("numeroMedioDePago");
String tipoDocumentoComercialString = request.queryParams("documentoComercial");
String numeroDocumentoComercialString = request.queryParams("numeroDocumentoComercial");

String presupuestosRequeridosString = request.queryParams("presupuestosRequeridos");
String razonSocialProveedor = request.queryParams("proveedor");

 */

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

function mostrarModalGuardadoEgreso() {
    var datos = recuperarDatosFormularioEgresos();

    if (datos.preciosItems === 'noHayPrecios'){
        alert("Se debe insertar al menos un item");
        console.log("No habia items, cancelo post");
        return;
    }

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

function mostrarModalGuardadoPresupuestos(id) {
    var ruta = "/usuario/presupuestos/"+id;
    var metodo = "Post";
    $.ajax({
        type : metodo,
        url : ruta,
        datatype : "html",
        success : function (result) {
            showInModal("modal", result);

        }
    });
}

function mostrarModalGuardadocriterios(id) {
    var ruta = "/usuario/criterios/"+id;
    var metodo = "Post";
    $.ajax({
        type : metodo,
        url : ruta,
        datatype : "html",
        success : function (result) {
            showInModal("modal", result);

        }
    });
}
/*
$(document).ready(function(){
    var $form = $('form');
    $form.submit(function(){
        $.post($(this).attr('action'),
            $(this).serialize(),
            function(response){
            // do something here on success
            },'json');
        return false;
    });
});
*/