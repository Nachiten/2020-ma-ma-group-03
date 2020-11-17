
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
