$('input[type="checkbox"]').change(function(){

    var objetoListaDesplegable = document.getElementById("horario" + this.name);

    if (this.checked){
        objetoListaDesplegable.removeAttribute('disabled');
    }else {
        objetoListaDesplegable.setAttribute("disabled", "");
    }
});

function devolverSiSeleccionado(id){

    var elemento = document.getElementById(id);

    if (elemento.checked){
        var hora = document.getElementById("horario" + elemento.name);

        return hora.value;
    } else {
        return -1;
    }

}

function recuperarDatosFormularioValidador(){
    return {
        lunes     : devolverSiSeleccionado("lunes"),
        martes    : devolverSiSeleccionado("martes"),
        miercoles : devolverSiSeleccionado("miercoles"),
        jueves    : devolverSiSeleccionado("jueves"),
        viernes   : devolverSiSeleccionado("viernes"),
        sabado    : devolverSiSeleccionado("sabado"),
        domingo   : devolverSiSeleccionado("domingo"),
    };
}

function mostrarModalEjecucionValidador() {

    var datos = recuperarDatosFormularioValidador();

    var ruta = "/validadorDeTransparencia";
    var metodo = "POST";
    $.ajax({
        type : metodo,
        url : ruta,
        datatype : "html",
        data: datos,
        success : function (result) {
            showInModal("modal", result);
        }
    });
}

function mostrarModalEjecutarValidadorAhora(){
    var ruta = "/validadorDeTransparenciaAhora";
    var metodo = "POST";
    $.ajax({
        type : metodo,
        url : ruta,
        datatype : "html",
        success : function (result) {
            showInModal("modal", result);
        }
    });
}