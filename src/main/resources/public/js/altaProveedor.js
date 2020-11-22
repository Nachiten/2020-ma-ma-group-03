
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

function recuperarDatosFormularioAltaNuevoUsuario(){
    return {
        nombre: valorDe("altaUsuario-nombre"),
        apellido: valorDe("altaUsuario-apellido"),
        nombreDeUsuario: valorDe("altaUsuario-nombreDeUsuario"),
        contrasenia: valorDe("altaUsuario-contrasenia"),
        tipoUsuario: valorDe("altaUsuario-tipoUsuario"),
        entidadJuridica: valorDe("altaUsuario-entidadJuridica")
    };
}

function recuperarDatosFormularioAltaProveedor(){
    return {
        nombre: valorDe("alta-nombre"),
        apellido: valorDe("alta-apellido"),
        razonSocial: valorDe("alta-razonSocial"),
        cuit_cuil: valorDe("alta-cuit_cuil"),
        calle: valorDe("alta-calle"),
        altura: valorDe("alta-altura"),
        piso: valorDe("alta-piso"),
        pais: valorDe("altaProveedor-pais"),
        provincia: valorDe("altaProveedor-provincia"),
        ciudad: valorDe("altaProveedor-ciudad")
    }
}

function mostrarModalGuardadoAltaNuevoProveedor() {

    console.log("Entre a la funcion de guardar proveedor");

    var datos = recuperarDatosFormularioAltaProveedor();

    var ruta = "/altaProveedor";
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

//funcion que habilita un select despues de seleccionar un option en otro select
$('#altaProveedor-pais').change(function(){
    $('#altaProveedor-provincia').removeAttr('disabled');
    filterSelectOptions($("#altaProveedor-provincia"), "data-attribute", $(this).val());
    filterSelectOptions($("#altaProveedor-ciudad"), "data-attribute", $(this).val());
});

//funcion que habilita un select despues de seleccionar un option en otro select
$('#altaProveedor-provincia').change(function(){
    $('#altaProveedor-ciudad').removeAttr('disabled');
    filterSelectOptions($("#altaProveedor-ciudad"), "data-attribute", $(this).val());
});

//funcion que oculta options del select
function filterSelectOptions(selectElement, attributeName, attributeValue) {
    if (selectElement.data("currentFilter") !== attributeValue) {
        selectElement.data("currentFilter", attributeValue);
        var originalHTML = selectElement.data("originalHTML");
        if (originalHTML)
            selectElement.html(originalHTML);
        else {
            var clone = selectElement.clone();
            clone.children("option[selected]").removeAttr("selected");
            selectElement.data("originalHTML", clone.html());
        }
        if (attributeValue) {
            selectElement.children("option:not([" + attributeName + "='" + attributeValue + "'],:not([" + attributeName + "]))").remove();
        }
    }
}
