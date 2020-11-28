
function mostrarModalNuevaEntidadJurdica() {
    var ruta = "/nuevaEntidadJuridica";
    var metodo = "GET";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal", result);
        }
    });
}

function mostrarModalHabilitarEntidadesJuridicas() {
    var ruta = "/habilitarEntidadesJuridicas";
    var metodo = "GET";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal", result);
        }
    });
}

function mostrarModalEditarEntidadesJuridicas() {
    var ruta = "/editarEntidadesJuridicas";
    var metodo = "GET";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal", result);
        }
    });
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

$('#altaEntidadJuridica-pais').change(function(){
    $('#altaEntidadJuridica-provincia').removeAttr('disabled');
    filterSelectOptions($("#altaEntidadJuridica-provincia"), "data-attribute", $(this).val());
    filterSelectOptions($("#altaEntidadJuridica-ciudad"), "data-attribute", $(this).val());
});

$('#altaEntidadJuridica-provincia').change(function(){
    $('#altaEntidadJuridica-ciudad').removeAttr('disabled');
    filterSelectOptions($("#altaEntidadJuridica-ciudad"), "data-attribute", $(this).val());
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
