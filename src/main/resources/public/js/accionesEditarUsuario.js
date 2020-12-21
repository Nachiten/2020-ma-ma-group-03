// Cosas al editar usuario
var cambioContraseniaEditarUsuario = document.getElementById('altaUsuario-cambioContrasenia');
var no_cambioContraseniaEditarUsuario = document.getElementById('altaUsuario-noCambioContrasenia');
var contraseniaEditarUsuario = document.getElementById('altaUsuario-contrasenia');

function cambiarHabilitacionInputContraseniaEditarUsuario() {
    console.log("Entre en cambiarHabilitacionInputContraseniaEditarUsuario");

    contraseniaEditarUsuario.disabled = !cambioContraseniaEditarUsuario.checked;
}

cambioContraseniaEditarUsuario.addEventListener('change', cambiarHabilitacionInputContraseniaEditarUsuario);
no_cambioContraseniaEditarUsuario.addEventListener('change', cambiarHabilitacionInputContraseniaEditarUsuario);

// Cosas al editar usuario
var cambioEntidadJuridica = document.getElementById('altaUsuario-cambioEntidadJuridica');
var no_cambioEntidadJuridica = document.getElementById('altaUsuario-noCambioEntidadJuridica');
var entidadJuridica = document.getElementById('altaUsuario-entidad');

function cambiarHabilitacionInputEntidadJuridicaEditarUsuario() {
    console.log("Entre en cambiarHabilitacionInputEntidadJuridicaEditarUsuario");

    entidadJuridica.disabled = !cambioEntidadJuridica.checked;
}

cambioEntidadJuridica.addEventListener('change', cambiarHabilitacionInputEntidadJuridicaEditarUsuario);
no_cambioEntidadJuridica.addEventListener('change', cambiarHabilitacionInputEntidadJuridicaEditarUsuario);

