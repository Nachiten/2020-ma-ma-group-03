//Nuevo usuario
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

function recuperarDatosFormularioNuevoUsuario(){
    return {
        nombre           : valorDe("altaUsuario-nombre"),
        apellido         : valorDe("altaUsuario-apellido"),
        nombreDeUsuario  : valorDe("altaUsuario-nombreDeUsuario"),
        contrasenia      : valorDe("altaUsuario-contrasenia"),
        tipoUsuario      : $("#altaUsuario-tipoUsuario option:selected").val(),
        entidad          : $("#altaUsuario-entidad option:selected").val()
    };
}

function mostrarModalConfirmacionNuevoUsuario() {
    var datos = recuperarDatosFormularioNuevoUsuario();
    var ruta = "/guardarUsuario";
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        data     : datos,
        success: function (result) {
            showInModal("modal2", result);
        }
    });
}

function mostrarModalConfirmarNuevousuario() {
    var ruta = "/confirmarNuevoUsuario";
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal3", result);
        }
    });
}

//Dar de baja usuario
function mostrarModalConfirmarBajaUsuario(id) {
    var ruta = "/editarUsuario/confirmarBajaUsuario/"+id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal2", result);
        }
    });
}

function darDeBajaUsuario(id){
    var ruta = "/editarUsuario/darDeBaja/" + id;
    var metodo = "DELETE";
    $.ajax({
        type     : metodo,
        url      : ruta,
        dataType : "html",
        success  : function(result){
            showInModal("modal3",result);
            mostrarModalEditarUsuarios();
        }
    });
}

//Habilitar usuarios
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

function mostrarModalConfirmarHabilitarUsuario(id) {
    var ruta = "/confirmarHabilitarUsuario/"+id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        dataType : "html",
        success  : function (result) {
            showInModal("modal2", result);
        }
    });
}

function habilitarUsuario(id) {
    var metodo = "POST";
    var ruta = "/habilitarUsuario/"+id;
    $.ajax({
        type     : metodo,
        url      : ruta,
        dataType : "html",
        success  : function (result) {
            showInModal("modal3", result);
            mostrarModalHabilitarUsuarios();
        }
    });
}

//EditarUsuarios
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

function mostrarModalConfirmarEditarUnUsuario(id) {
    var ruta = "/editarUsuario/confirmarEditarUsuario/"+id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal2", result);
        }
    });
}

function mostrarModalParaEditarUnUsuario(id) {
    var ruta = "/editarUsuario/modificar/" + id;
    var metodo = "GET";
    $.ajax({
        type     : metodo,
        url      : ruta,
        dataType : "html",
        success  : function(result){
            showInModal("modal3",result);
        }
    });
}

function mostrarModalParaConfirmarCambiosRealizadosEnUsuario(id) {
    var ruta = "/editarUsuario/modificar/confirmarModificar/" + id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        dataType : "html",
        success  : function(result){
            showInModal("modal4",result);
        }
    });
}

function mostrarModalConfirmacionCambiosEnUsuario(id) {
    var datos = recuperarDatosFormularioUsuarioEditado();
    var ruta = "/editarUsuario/modificar/guardar/" + id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        dataType : "html",
        data     : datos,
        success  : function(result){
            showInModal("modal5",result);
            mostrarModalEditarUsuarios();
            mostrarModalParaEditarUnUsuario(id);
        }
    });
}

function recuperarDatosFormularioUsuarioEditado(){
    var datos = {
        nombre            : valorDe("altaUsuario-nombre"),
        apellido          : valorDe("altaUsuario-apellido"),
        nombreDeUsuario   : valorDe("altaUsuario-nombreDeUsuario"),
        contrasenia       : valorDe("altaUsuario-contrasenia"),
        miEntidad         : valorDe("altaUsuario-miEntidad"),
        entidad           : $("#altaUsuario-entidad option:selected").val()
    };
    return datos;
}


function mostrarDetalleEntidadJuridica() {

    //acá yo le digo che loco, hace un post sobre tal ruta!
    var ruta = "/editarPerfil/detalleEntidadJuridica";
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal", result);
        }
    });
}

//habilitar input para cambiar contraseña
var cambioContrasenia = document.getElementById('altaUsuarioPerfil-cambioContrasenia');
var no_cambioContrasenia = document.getElementById('altaUsuarioPerfil-noCambioContrasenia');
var contrasenia = document.getElementById('altaUsuarioPerfil-contrasenia');

function cambiarHabilitacionInputContrasenia() {
    contrasenia.disabled = !cambioContrasenia.checked;

    var objetoBotonCambiarContra = document.getElementById("botonCambiarContrasenia");

    if (cambioContrasenia.checked){
        objetoBotonCambiarContra.removeAttribute('disabled');
    }else {
        objetoBotonCambiarContra.setAttribute("disabled", "");
    }
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