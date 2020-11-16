
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
    var datos = {
        nombre            : valorDe("altaUsuario-nombre"),
        apellido          : valorDe("altaUsuario-apellido"),
        nombreDeUsuario   : valorDe("altaUsuario-nombreDeUsuario"),
        contrasenia       : valorDe("altaUsuario-contrasenia"),
        tipoUsuario       : valorDe("altaUsuario-tipoUsuario"),
        entidadJuridica  : valorDe("altaUsuario-entidadJuridica")
    };
    return datos;
}

function mostrarModalGuardadoAltaNuevoProveedor() {
    var datos = recuperarDatosFormularioAltaNuevoProveedor();
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
