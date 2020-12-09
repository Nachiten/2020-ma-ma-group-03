function mostrarModalContenidoMensaje(id){
    var ruta = "/mensajes/"+id;
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