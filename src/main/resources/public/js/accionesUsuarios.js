
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

function mostrarModalHabilitarUsuarios() {
    var ruta = "/habilitarUsuario";
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
function habilitarUsuario(id) {
    var metodo = "POST";
    var ruta = "/habilitarUsuario/"+id;
    var mensaje = confirm("¿Está seguro que quiere habilitar a este usuario?");
    if (mensaje) {
        $.ajax({
            type: metodo,
            url: ruta,
            dataType: "html",
            success: function (result) {
                showInModal("modal", result);
            }
        });
    }
}

function recuperarDatosFormularioNuevoUsuario(){
    var datos = {
        nombre            : valorDe("altaUsuario-nombre"),
        apellido          : valorDe("altaUsuario-apellido"),
        nombreDeUsuario   : valorDe("altaUsuario-nombreDeUsuario"),
        contrasenia       : valorDe("altaUsuario-contrasenia"),
        tipoUsuario       : valorDe("altaUsuario-tipoUsuario"),
        entidadJuridica   : valorDe("altaUsuario-entidadJuridica")
    };
    return datos;
}

function esVacio(string){
    return string === '';
}

function validarDatos(datos){
    if (esVacio(datos.nombre)){
        return 'Se debe insertar un nombre';
    }

    if (esVacio(datos.apellido)){
        return 'Se debe insertar un apellido';
    }

    if (esVacio(datos.nombreDeUsuario)){
        return 'Se debe insertar un nombre de usuario';
    }

    if (esVacio(datos.contrasenia)){
        return 'Se debe insertar una contraseña';
    }
    if (datos.tipoUsuario === 'Elegir tipo de usuario'){
        return 'Se debe seleccionar un tipo de usuario';
    }
    if (datos.tipoUsuario !== 'ADMIN' && datos.entidadJuridica === 'Elegir una entidad jurídica'){
        return 'Se debe seleccionar una entidad juridica asociada';
    }

    // No falta ningun dato
    return '';
}

function mostrarModalConfirmacionNuevoUsuario() {
    var datos = recuperarDatosFormularioNuevoUsuario();

    var mensajeDeError = validarDatos(datos);

    if (mensajeDeError !== ''){
        alert(mensajeDeError);
        return;
    }

    var ruta = "/guardarUsuario";
    var metodo = "POST";
    var mensaje = confirm("¿Está seguro que quiere crear a este nuevo usuario?");
    if (mensaje) {
        $.ajax({
            type: metodo,
            url: ruta,
            datatype: "html",
            data: datos,
            success: function (result) {
                showInModal("modal", result);
            }
        });
    }
}

function mostrarModalNuevoUsuario(){
    var ruta = "/nuevoUsuario";
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

function mostrarModalEditarUsuarios() {
    var ruta = "/editarUsuario";
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

function darDeBajaUsuario(id){
    var ruta = "/editarUsuario/darDeBaja/" + id;
    var metodo = "DELETE";
    var mensaje = confirm("¿Está seguro que quiere dar de baja éste usuario?");
    if (mensaje) {
        $.ajax({
            type: metodo,
            url: ruta,
            dataType: "html",
            success : function(result){
                showInModal("modal",result);
            }
        });
    }
}

function mostrarModalParaEditarUnUsuario(id) {
    var ruta = "/editarUsuario/modificar/" + id;
    var metodo = "GET";
    var mensaje = confirm("¿Está seguro que quiere editar éste usuario?");
    if (mensaje) {
        $.ajax({
            type: metodo,
            url: ruta,
            dataType: "html",
            success : function(result){
                showInModal("modal",result);
            }
        });
    }
}

//habilitar input para cambiar contraseña
var cambioContrasenia = document.getElementById('altaUsuario-cambioContrasenia');
var no_cambioContrasenia = document.getElementById('altaUsuario-noCambioContrasenia');
var contrasenia = document.getElementById('altaUsuario-contrasenia');

function cambiarHabilitacionInputContrasenia() {
    contrasenia.disabled = !cambioContrasenia.checked;
}
cambioContrasenia.addEventListener('change', cambiarHabilitacionInputContrasenia);
no_cambioContrasenia.addEventListener('change', cambiarHabilitacionInputContrasenia);

//Habilitar select para elegir una entidad jurídica
var cambioEntidadJuridica = document.getElementById('altaUsuario-cambioEntidadJuridica');
var no_cambioEntidadJuridica = document.getElementById('altaUsuario-noCambioEntidadJuridica');
var entidadJuridica = document.getElementById('altaUsuario-entidadJuridica');

function cambiarHabilitacionInputEntidadJuridica() {
    entidadJuridica.disabled = !cambioEntidadJuridica.checked;
}
cambioEntidadJuridica.addEventListener('change', cambiarHabilitacionInputEntidadJuridica);
no_cambioEntidadJuridica.addEventListener('change', cambiarHabilitacionInputEntidadJuridica);

