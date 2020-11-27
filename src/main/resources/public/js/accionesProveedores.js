function mostrarModalNuevoProveedor() {
    var ruta = "/nuevoProveedor";
    var metodo = "GET";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal", result);
        }
    });
}

function recuperarDatosFormularioNuevoProveedor(){
    return {
        nombre          : valorDe("altaProveedor-nombre"),
        apellido        : valorDe("altaProveedor-apellido"),
        razonSocial     : valorDe("altaProveedor-razonSocial"),
        cuit_cuil       : valorDe("altaProveedor-cuit_cuil"),
        // Direccion
        barrio          : valorDe("altaProveedor-barrio"),
        calle           : valorDe("altaProveedor-calle"),
        altura          : valorDe("altaProveedor-altura"),
        departamento    : valorDe("altaProveedor-departamento"),
        piso            : valorDe("altaProveedor-piso"),
        // Datos api
        pais            : $("#altaProveedor-pais option:selected").val(),
        provincia       : $("#altaProveedor-provincia option:selected").val(),
        ciudad          : $("#altaProveedor-ciudad option:selected").val()
    }
}

function esVacio(string){
    return string === '';
}

function validarDatosDelProveedorIngresado(datos){
    if (esVacio(datos.nombre)){
        return 'Ingrese su nombre';
    }

    if (esVacio(datos.apellido)){
        return 'Ingrese su apellido';
    }

    if (esVacio(datos.razonSocial)){
        return 'Ingrese su razón social';
    }

    if (esVacio(datos.cuit_cuil)){
        return 'Ingrese su Cuit/Cuil';
    }

    if (esVacio(datos.calle)){
        return 'Ingrese el nombre de su calle';
    }

    if (esVacio(datos.altura)){
        return 'Ingrese la altura de la calle';
    }

    if (datos.pais === 'Seleccionar país'){
        return 'Se debe seleccionar un país';
    }
    if (datos.provincia === 'Seleccionar provincia'){
        return 'Se debe seleccionar una provincia';
    }
    if (datos.ciudad === 'Seleccionar Ciudad'){
        return 'Se debe seleccionar una ciudad';
    }

    // No falta ningun dato
    return '';
}

function mostrarModalConfirmacionNuevoProveedor() {
    var datos = recuperarDatosFormularioNuevoProveedor();
    var mensajeDeError = validarDatosDelProveedorIngresado(datos);

    if (!esVacio(mensajeDeError)){
        alert(mensajeDeError);
        return;
    }
    var ruta = "/guardarNuevoProveedor";
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        data     : datos,
        success  : function (result) {
            showInModal("modal2", result);
        }
    });
}

function mostrarModalHabilitarProveedores(){
    var ruta = "/habilitarProveedor";
    var metodo = "GET";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal", result);
        }
    });
}

function habilitarProveedor(id) {
    var ruta = "/habilitarProveedor/"+id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal2", result);
            mostrarModalHabilitarProveedores();
        }
    });
}

function mostrarModalEditarProveedores(){
    var ruta = "/editarProveedor";
    var metodo = "GET";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal", result);
        }
    });
}

function mostrarModalParaEditarUnProveedor(id) {
    var ruta = "/editarProveedor/modificar/" + id;
    var metodo = "GET";
    $.ajax({
        type: metodo,
        url: ruta,
        dataType: "html",
        success : function(result){
            showInModal("modal2",result);//verificar si funciona con modal2
        }
    });
}

function mostrarModalCambiarDireccionDelProveedor(id) {
    var ruta = "/editarProveedor/actualizarDireccion/" + id;
    var metodo = "GET";
    $.ajax({
        type: metodo,
        url: ruta,
        dataType: "html",
        success : function(result){
            showInModal("modal3",result);//verificar si funciona con modal2
        }
    });
}

function recuperarDatosDireccion() {
    return{
        // Direccion
        barrio          : valorDe("altaProveedor-barrio"),
        calle           : valorDe("altaProveedor-calle"),
        altura          : valorDe("altaProveedor-altura"),
        departamento    : valorDe("altaProveedor-departamento"),
        piso            : valorDe("altaProveedor-piso"),
        // Datos api
        pais            : $("#altaProveedor-pais option:selected").val(),
        provincia       : $("#altaProveedor-provincia option:selected").val(),
        ciudad          : $("#altaProveedor-ciudad option:selected").val()
    }
}
function mostrarModalConfirmacionCambioDireccionProveedor(id) {
    var datosDireccion = recuperarDatosDireccion();
    var ruta = "/editarProveedor/actualizarDireccion/" + id;
    var metodo = "POST";
    $.ajax({
        type: metodo,
        url: ruta,
        dataType: "html",
        data     : datosDireccion,
        success : function(result){
            showInModal("modal3",result);//verificar si funciona con modal2
            mostrarModalCambiarDireccionDelProveedor(id);
        }
    });
}

function darDeBajaProveedor(id) {
    var ruta = "/editarproveedor/darDeBaja/"+id;
    var metodo = "DELETE";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal2", result);
            mostrarModalEditarProveedores();
        }
    });
}

//funcion que habilita un select despues de seleccionar un option en otro select
$('#altaProveedor-pais').change(function(){
    $('#altaProveedor-provincia').removeAttr('disabled');
    filterSelectOptions($("#altaProveedor-provincia"), "data-attribute", $(this).val());
    filterSelectOptions($("#altaProveedor-ciudad"), "data-attribute", $(this).val());
});

//funcion que habilita un select despues de seleccionar un option en otro select
$('#altaProveedor-provincia').change(function(){
    $('#altaProveedor-ciudad').removeAttr('disabled');
    filterSelectOptions($("#altaProveedor-ciudad"), "data-attribute", $(this).val());
});

//funcion que oculta options del select
function filterSelectOptions(selectElement, attributeName, attributeValue) {
    if (selectElement.data("currentFilter") !== attributeValue) {
        selectElement.data("currentFilter", attributeValue);
        var originalHTML = selectElement.data("originalHTML");
        if (originalHTML)
            selectElement.html(originalHTML);
        else {
            var clone = selectElement.clone();
            clone.children("option[selected]").removeAttr("selected");
            selectElement.data("originalHTML", clone.html());
        }
        if (attributeValue) {
            selectElement.children("option:not([" + attributeName + "='" + attributeValue + "'],:not([" + attributeName + "]))").remove();
        }
    }
}
