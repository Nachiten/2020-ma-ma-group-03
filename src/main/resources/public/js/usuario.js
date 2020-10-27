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

function verificarSesion() {
    var ruta = '/';
    var metodo = 'POST';
    $.ajax({
            type : metodo,
            url : ruta,
            datatype : "html",
            succes : function (result) {
                showInModal("modal", result);

            }
        });
}

function mostrarModalGuardadoIngreso(id) {
    var ruta = "/usuario/ingresos/"+id;
    var metodo = "Post";
    $.ajax({
        type : metodo,
        url : ruta,
        datatype : "html",
        succes : function (result) {
            showInModal("modal", result);

        }
    });
}

function mostrarModalGuardadoEgreso(id) {
    var ruta = "/usuario/egresos/"+id;
    var metodo = "Post";
    $.ajax({
        type : metodo,
        url : ruta,
        datatype : "html",
        succes : function (result) {
            showInModal("modal", result);

        }
    });
}

function mostrarModalGuardadoPresupuestos(id) {
    var ruta = "/usuario/presupuestos/"+id;
    var metodo = "Post";
    $.ajax({
        type : metodo,
        url : ruta,
        datatype : "html",
        succes : function (result) {
            showInModal("modal", result);

        }
    });
}

function mostrarModalGuardadocriterios(id) {
    var ruta = "/usuario/criterios/"+id;
    var metodo = "Post";
    $.ajax({
        type : metodo,
        url : ruta,
        datatype : "html",
        succes : function (result) {
            showInModal("modal", result);

        }
    });
}
/*
$(document).ready(function(){
    var $form = $('form');
    $form.submit(function(){
        $.post($(this).attr('action'),
            $(this).serialize(),
            function(response){
            // do something here on success
            },'json');
        return false;
    });
});
*/
