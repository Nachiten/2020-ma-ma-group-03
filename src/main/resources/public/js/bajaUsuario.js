
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

function darDeBajaUsuario(id){
    console.log("Tengo que borrar el usuario con id " + id);
    //var id = document.getElementById("userId").value;
    var mensaje = confirm("¿Está seguro que quiere dar de baja éste usuario?");
    if (mensaje) {
        $.ajax({
            type: "DELETE",
            url: "/bajaUsuario/eliminar/" + id,
            dataType: "html",
            success : function(result){
                showInModal("modal",result);

            }
        });
    }
}

