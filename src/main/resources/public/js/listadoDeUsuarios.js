/*$(function () {

    $(document).on('click', '.borrar', function (event) {
        console.log("Entre en la funcion borrar");
        event.preventDefault();
        $(this).closest('tr').remove();
    });
});*/

function eliminarUsuario(id){
    console.log("Tengo que borrar el usuario con id " + id);
    //var id = document.getElementById("userId").value;
    $.ajax({
        type: "DELETE",
        url: "usuario/"+id,
        success: function(result){
            location.reload(true);
        }
    });
}

