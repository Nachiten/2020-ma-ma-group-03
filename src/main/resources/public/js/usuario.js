function modal_show(unModal, resultado){

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
function cerrarSesion() {
    var ruta = "/";
    var metodo = "POST";
    $.ajax({
        type	: metodo,
        url 	: ruta,
        dataType: "html",
        success : function(result){
            showInModal("modal", result);
        }
    });
}
/*
$(document).ready(
    function () {
        modal_show("#modal2");
    }
);

function confirmarCierre() {
    alertify.confirm(
        '¡Atención!',
        '¿Estas seguro de cerrar sesión?',
        function(){window.location = "/";},
        function(){ }
    );
}*/

