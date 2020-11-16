
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

function habilitarUsuario(id) {
    console.log("Tengo que editar el usuario con id " + id);
    //var id = document.getElementById("userId").value;
    var mensaje = confirm("¿Está seguro que quiere habilitar a este usuario?");
    if (mensaje) {
        $.ajax({
            type: "POST",
            url: "/altaUsuario/alta/" + id,
            dataType: "html",
            success: function (result) {
                showInModal("modal", result);

            }
        });
    }
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

function mostrarModalGuardadoAltaNuevoUsuario() {
    var datos = recuperarDatosFormularioAltaNuevoUsuario();
    var ruta = "/altaUsuario";
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
