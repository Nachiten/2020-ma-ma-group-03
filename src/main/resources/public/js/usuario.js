function cerrarSesion() {
    const ruta = "/";
    const metodo = "POST";
    $.ajax({
        type	: metodo,
        url 	: ruta,
        dataType: "html",
        success : function(result){
            showInModal("modal", result);
        }
    });
}

function verificarSesion() {
    const ruta = '/';
    const metodo = 'POST';
    $.ajax({
        type : metodo,
        url : ruta,
        datatype : "html",
        success : function (result) {
            showInModal("modal", result);

        }
    });
}

function mostrarModalGuardadocriterios() {
    var datos = recuperarDatosFormularioCriterio();

    var ruta = "/criterios";
    var metodo = "POST";
    $.ajax({
        type : metodo,
        url : ruta,
        datatype : "html",
        data : datos,
        success : function (result) {
            showInModal("modal", result);
        }
    });
}

function recuperarDatosFormularioCriterio (){
    return {
        nombreCriterio: valorDe("alta-nombreCriterio"),
        categoriasCriterio: datosDeTablaPorNombreDeClase(".categoriaCriterio"),
    }
}

function mostrarModalEjecucionValidador() {

    var ruta = "/validadorDeTransparencia";
    var datos = {minutos: valorDe("minutos-ok")}
    var metodo = "POST";
    $.ajax({
        type : metodo,
        url : ruta,
        datatype : "html",
        data: datos,
        success : function (result) {
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
}
cambioContrasenia.addEventListener('change', cambiarHabilitacionInputContrasenia);
no_cambioContrasenia.addEventListener('change', cambiarHabilitacionInputContrasenia);


function recuperarDatosFormularioPerfil(){
    return {
        nombre: valorDe("altaUsuarioPerfil-nombre"),
        apellido: valorDe("altaUsuarioPerfil-apellido")
    };
}
function recuperarContraseniaFormularioPerfil(){
    return {
        contrasenia: valorDe("altaUsuarioPerfil-contrasenia")

    };
}

function mostrarModalConfirmacionActualizacionDeDatos(){
    var datos = recuperarDatosFormularioPerfil();
    var ruta = "/actualizarDatosPerfil" ;
    var metodo = "POST";
    var mensaje = confirm("¿Está seguro de actualizar los datos de éste usuario?");
    if (mensaje) {
        $.ajax({
            type: metodo,
            url: ruta,
            dataType: "html",
            data: datos,
            success : function(result){
                showInModal("modal",result);
            }
        });
    }
}

function mostrarModalConfirmacionCambioDeContrasenia(){
    var datos =recuperarContraseniaFormularioPerfil();
    var ruta = "/actualizarContrasenia";
    var metodo = "POST";
    var mensaje = confirm("¿Estás seguro de cambiar tu contraseña?");
    if (mensaje) {
        $.ajax({
            type: metodo,
            url: ruta,
            dataType: "html",
            data: datos,
            success : function(result){
                showInModal("modal",result);
            }
        });
    }
}