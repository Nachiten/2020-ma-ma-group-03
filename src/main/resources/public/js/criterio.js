function mostrarModalGuardadocriterios() {
    var datos = recuperarDatosFormularioCriterio();

    var ruta = "/accionesCriterios";
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

function recuperarDatosFormularioCriterio(){
    return {
        nombreCriterio: valorDe("alta-nombreCriterio"),
        categoriasCriterio: datosDeTablaPorNombreDeClase(".categoriaCriterio"),
    }
}