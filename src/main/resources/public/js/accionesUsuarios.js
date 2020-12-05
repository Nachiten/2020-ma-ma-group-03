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
        type: metodo,
        url: ruta,
        dataType: "html",
        success: function (result) {
            showInModal("modal2", result);
        }
    });
}

function habilitarUsuario(id) {
    var metodo = "POST";
    var ruta = "/habilitarUsuario/"+id;
    $.ajax({
        type: metodo,
        url: ruta,
        dataType: "html",
        success: function (result) {
            showInModal("modal3", result);
            mostrarModalHabilitarUsuarios();
        }
    });
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

function recuperarDatosFormularioNuevoUsuario(){
    return {
        nombre              : valorDe("altaUsuario-nombre"),
        apellido            : valorDe("altaUsuario-apellido"),
        nombreDeUsuario     : valorDe("altaUsuario-nombreDeUsuario"),
        contrasenia         : valorDe("altaUsuario-contrasenia"),
        tipoUsuario         : valorDe("altaUsuario-tipoUsuario"),
        entidadJuridica     : valorDe("altaUsuario-entidadJuridica")
    };
}

function mostrarModalConfirmacionNuevoUsuario() {
    var datos = recuperarDatosFormularioNuevoUsuario();

    var mensajeDeError = validarDatos(datos);

    if (!esVacio(mensajeDeError)){
        alert(mensajeDeError);
        return;
    }

    var ruta = "/guardarUsuario";
    var metodo = "POST";
    $.ajax({
        type: metodo,
        url: ruta,
        datatype: "html",
        data: datos,
        success: function (result) {
            showInModal("modal2", result);
            mostrarModalNuevoUsuario();
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
            showInModal("modal2", result);
        }
    });
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
        type: metodo,
        url: ruta,
        dataType: "html",
        success : function(result){
            showInModal("modal3",result);
            mostrarModalEditarUsuarios();
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
        type: metodo,
        url: ruta,
        dataType: "html",
        success : function(result){
            showInModal("modal3",result);
        }
    });
}

function mostrarModalParaConfirmarCambiosRealizadosEnUsuario(id) {
    var ruta = "/editarUsuario/modificar/" + id + "/confirmarModificar/" + id;
    var metodo = "POST";
    $.ajax({
        type: metodo,
        url: ruta,
        dataType: "html",
        success : function(result){
            showInModal("modal4",result);
        }
    });
}

function mostrarModalConfirmacionCambiosEnUsuario(id) {
    var datos = recuperarDatosFormularioUsuarioEditado();
    var ruta = "/editarUsuario/modificar/" + id + "/guardar/" + id;
    var metodo = "POST";
    $.ajax({
        type: metodo,
        url: ruta,
        dataType: "html",
        data: datos,
        success : function(result){
            showInModal("modal5",result);
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
        miEntidadJuridica : valorDe("altaUsuario-miEntidadJuridica"),
        entidadJuridica   : valorDe("altaUsuario-entidadJuridica"),
        soyRevisor        : $('input:radio[name=soyRevisor]:checked').val()
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