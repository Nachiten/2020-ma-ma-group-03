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

function recuperarDatosUsuario(){
    var datos = {
        nombreDeUsuario     : valorDe("login-usuario"),
        contrasenia         : valorDe("login-contrasenia")
    };
    return datos;
}

function verificarSesion() {
    var datos = recuperarDatosUsuario();
    var ruta = '/inicio';
    var metodo = 'POST';
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

function recuperarDatosFormularioIngresos(){
    var datos = {
        fecha               : valorDe("alta-fecha"),
        periodoDeAceptacion : valorDe("alta-periodoDeAceptacion"),
        monto               : valorDe("alta-monto"),
        moneda              : valorDe("alta-moneda"),
        descripcion         : valorDe("alta-descripcion")
    };
    return datos;
}

function mostrarModalGuardadoIngreso() {
    var datos = recuperarDatosFormularioIngresos();
    var ruta = "/ingresos";
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

function mostrarModalGuardadoEgreso(id) {
    var ruta = "/usuario/egresos/"+id;
    var metodo = "Post";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
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
        success : function (result) {
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
        success : function (result) {
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
