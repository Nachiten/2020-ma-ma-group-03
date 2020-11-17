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
    return {
        fecha: valorDe("alta-fecha"),
        medioDePago: valorDe("alta-medioDePago"),
        numeroMedioDePago: valorDe("alta-numeroMedioDePago"),
        documentoComercial: valorDe("alta-documentoComercial"),
        numeroDocumentoComercial: valorDe("alta-numeroDocumentoComercial"),
        presupuestosRequeridos: valorDe("alta-presupuestosRequeridos"),
        proveedor: valorDe("alta-proveedor"),
        preciosItems: datosDeTablaPorNombreDeClase(".precioItem"),
        nombresItems: datosDeTablaPorNombreDeClase(".nombreItem"),
        nombresCategorias: datosNombresCategorias()
    };
}

function recuperarDatosFormularioPresupuesto(){
    return {
        documentoComercial: valorDe("alta-tipoDocumentoComercial"),
        numeroDocumentoComercial: valorDe("alta-numeroDocumentoComercial"),
        operacionEgreso: valorDe("alta-operacionEgreso"),
        preciosItems: datosDeTablaPorNombreDeClase(".precioItem"),
        nombresItems: datosDeTablaPorNombreDeClase(".nombreItem"),
        nombresCategorias: datosNombresCategorias()
    };
}

function recuperarDatosFormularioCriterio (){
    return {
        nombreCriterio: valorDe("alta-nombreCriterio"),
        categoriasCriterio: datosDeTablaPorNombreDeClase(".categoriaCriterio"),
    }
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

/*
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
}*/

function datosDeTablaPorNombreDeClase(nombreClase) {

    var nombres = document.querySelectorAll(nombreClase);

    var nombresItemsValores = '';
    for (var x = 0; x < nombres.length; x++) {
        nombresItemsValores += nombres[x].value;
        if (x !== nombres.length - 1){
            nombresItemsValores += '=';
        }
    }

    console.log("Clase: " + nombreClase + ". Valores: " + nombresItemsValores);

    return nombresItemsValores;
}



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

function mostrarModalGuardadocriterios() {
    var datos = recuperarDatosFormularioCriterio();

    var ruta = "/criterios";
    var metodo = "POST";
    $.ajax({
        type : metodo,
        url : ruta,
        datatype : "html",
        data : datos,
        success : function (result) {
            showInModal("modal", result);
        }
    });
}

function mostrarModalEjecucionValidador() {

    var ruta = "/validadorDeTransparencia";
    var datos = {minutos: valorDe("minutos-ok")}
    var metodo = "POST";
    $.ajax({
        type : metodo,
        url : ruta,
        datatype : "html",
        data: datos,
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