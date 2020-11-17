
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



function recuperarDatosFormularioAltaNuevaEntidadJuridica(){
    return {
        nombre : valorDe("altaEntidadJuridica-nombre"),
        nombreFicticio : valorDe("altaEntidadJuridica-nombreFicticio"),
        codigoInscripcionDefinitiva: valorDe("altaEntidadJuridica-codigo"),
        cuit: valorDe("altaEntidadJuridica-cuit"),
        razonSocial: valorDe("altaEntidadJuridica-razonSocial"),
        calle: valorDe("alta-calle"),
        altura: valorDe("alta-altura"),
        piso: valorDe("alta-piso")
        // TODO | Falta pais ciudad provincia
    };
}


function mostrarModalGuardadoAltaEntidadJuridica() {
    var datos = recuperarDatosFormularioAltaNuevaEntidadJuridica();
    var ruta = "/altaEntidadJuridica";
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

$('#altaProveedor-pais').change(function(){
    $('#altaProveedor-provincia').removeAttr('disabled');
    filterSelectOptions($("#altaProveedor-provincia"), "data-attribute", $(this).val());
    filterSelectOptions($("#altaProveedor-ciudad"), "data-attribute", $(this).val());
});

$('#altaProveedor-provincia').change(function(){
    $('#altaProveedor-ciudad').removeAttr('disabled');
    filterSelectOptions($("#altaProveedor-ciudad"), "data-attribute", $(this).val());
});

function filterSelectOptions(selectElement, attributeName, attributeValue) {
    if (selectElement.data("currentFilter") != attributeValue) {
        selectElement.data("currentFilter", attributeValue);
        var originalHTML = selectElement.data("originalHTML");
        if (originalHTML)
            selectElement.html(originalHTML)
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
