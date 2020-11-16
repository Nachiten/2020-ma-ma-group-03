
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
    var datos = {
        codigoInscripcionDefinitiva       : valorDe("altaEntidadJuridica-codigo"),
        cuit         : valorDe("altaEntidadJuridica-cuit"),
       razonSocial : valorDe("altaEntidadJuridica-razonSocial"),


    };
    return datos;
}

function mostrarModalGuardadoAltaNuevoUsuario() {
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
