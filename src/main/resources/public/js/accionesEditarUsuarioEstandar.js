//habilitar input para cambiar contraseña
var cambioContrasenia = document.getElementById('altaUsuarioPerfil-cambioContrasenia');
var no_cambioContrasenia = document.getElementById('altaUsuarioPerfil-noCambioContrasenia');
var contrasenia = document.getElementById('altaUsuarioPerfil-contrasenia');

function cambiarHabilitacionInputContrasenia() {

    console.log("Entre en cambiarHabilitacionInputContrasenia");

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