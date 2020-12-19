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

function recuperarDatosFormularioPresupuesto(){
    return {
        fecha: valorDe("alta-fecha"),
        documentoComercial: valorDe("alta-tipoDocumentoComercial"),
        numeroDocumentoComercial: valorDe("alta-numeroDocumentoComercial"),
        proveedor: valorDe("alta-proveedor"),
        operacionEgreso: valorDe("alta-operacionEgreso"),
        preciosItems: datosDeTablaPorNombreDeClase(".precioItem"),
        nombresItems: datosDeTablaPorNombreDeClase(".nombreItem"),
        cantidadesItems: datosDeTablaPorNombreDeClase(".cantidadItem"),
        nombresCategorias: datosNombresCategorias()
    };
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

function mostrarModalEjecucionVinculacion(){
    var datos = recuperarCriterios();
    var ruta = "/asociarOperacion/vinculacion";
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        data : datos,
        success  : function (result) {
            showInModal("modal", result);
        }
    });
}


function datosNombresCriterios() {
    var criterios = document.querySelectorAll(".criterio");

    var criteriosString = '';
    for (var x = 0; x < criterios.length; x++) {
        if (criterios[x].checked) {
            criteriosString += criterios[x].name;
            criteriosString += '=';
        }

    }

    var cadenaADevolver = criteriosString.substring(0, criteriosString.length - 1);

    console.log("criterios: " + cadenaADevolver);

    return cadenaADevolver;
}

function recuperarCriterios(){
    return {
        criterios: datosNombresCriterios()
    };
}