
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

function eliminarUsuario(id){
    console.log("Tengo que editar el usuario con id " + id);
    //var id = document.getElementById("userId").value;
    var mensaje = confirm("¿Está seguro que quiere habilitar a este usuario?");
    if (mensaje) {
        $.ajax({
            type: "POST",
            url: "/altaUsuario/alta/" + id,
            dataType: "html",
            success : function(result){
                showInModal("modal",result);

            }
        });
    }
}
