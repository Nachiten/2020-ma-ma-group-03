/////////////////////////////////////
//Nuevo proveedor
/////////////////////////////////////
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

function mostrarModalConfirmarNuevoProveedor() {
    var ruta = "/confirmarNuevoProveedor";
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal2", result);
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

function mostrarModalConfirmacionNuevoProveedor() {
    var datos = recuperarDatosFormularioNuevoProveedor();
    var ruta = "/guardarNuevoProveedor";
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        data     : datos,
        success  : function (result) {
            showInModal("modal3", result);
        }
    });
}

//HabilitarProveedor
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

function mostrarModalParaConfirmarHabilitarUnProveedor(id) {
    var ruta = "/habilitarProveedor/confirmarHabilitar/"+id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal2", result);
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
            showInModal("modal3", result);
            mostrarModalHabilitarProveedores();
        }
    });
}

/////////////////////////////////////
//Editar Proveedor
/////////////////////////////////////
//paso 1- muestro la lista de proveedores activos que se dara de baja o actualizará datos
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

//muestra el modal para confirmar si se quiere editar los datos a un proveedor
function mostrarModalParaConfirmarEditarUnProveedor(id) {
    var ruta = "/editarProveedor/confirmarEditarProveedor/"+id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal2", result);
        }
    });
}

//muestra el formulario con los datos de un proveedor al cual se le editará
function mostrarModalParaEditarUnProveedor(id) {
    var ruta = "/editarProveedor/modificar/" + id;
    var metodo = "GET";
    $.ajax({
        type      : metodo,
        url       : ruta,
        dataType  : "html",
        success   : function(result){
            showInModal("modal3",result);
        }
    });
}

//muestra modal para confirmar si quiero guardar los cambios realizados en los datos del proveedo
function mostrarModalParaConfirmarCambiosEnDatosDelProveedor(id) {
    var ruta = "/editarProveedor/modificar/confirmarCambiosDatosProveedor/"+id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal4", result);
        }
    });
}

function recuperarDatosParaActualizarProveedor() {
    return{
        nombre          : valorDe("altaProveedor-nombre"),
        apellido        : valorDe("altaProveedor-apellido"),
        razonSocial     : valorDe("altaProveedor-razonSocial"),
        cuit_cuil       : valorDe("altaProveedor-cuit_cuil")
    }
}
//muestra el modal que se realizó la actualización de datos del proveedor
function mostrarModalConfirmacionDatosActualizadosEnProveedor(id) {
    var datosProveedor = recuperarDatosParaActualizarProveedor();
    var ruta = "/editarProveedor/modificar/confirmacionCambiosDatosProveedor/" + id;
    var metodo = "POST";
    $.ajax({
        type      : metodo,
        url       : ruta,
        dataType  : "html",
        data      : datosProveedor,
        success   : function(result){
            showInModal("modal5",result);
            mostrarModalEditarProveedores();
        }
    });
}

//muestra un modal para confirmar si quiero editar la direccion del proveedor
function mostrarModalParaConfirmarEditarDireccionDelProveedor(id) {
    var ruta = "/editarProveedor/modificar/confirmarEditarDireccionProveedor/"+id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal4", result);
        }
    });
}

//muestra el formulario con los datos de la direccion al cual se le editará
function mostrarModalCambiarDireccionDelProveedor(id) {
    var ruta = "/editarProveedor/modificar/actualizarDireccion/" + id;
    var metodo = "GET";
    $.ajax({
        type     : metodo,
        url      : ruta,
        dataType : "html",
        success  : function(result){
            showInModal("modal5",result);
        }
    });
}

//muestra modal para confirmar si quiero actualizar los datos de la direccion del proveedor
function mostrarModalConfirmarCambiosEnDireccionProveedor(id) {
    var ruta = "/editarProveedor/modificar/confirmarActualizarDireccion/" + id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        dataType : "html",
        success  : function(result){
            showInModal("modal6",result);
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

//muestra un modal que confirma que los datos actualizados de la direccion del proveedor se guardaron correctamente
function mostrarModalConfirmacionCambioDireccionProveedor(id) {
    var datosDireccion = recuperarDatosDireccion();
    var ruta = "/editarProveedor/modificar/actualizacionDireccion/" + id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        dataType : "html",
        data     : datosDireccion,
        success  : function(result){
            showInModal("modal7",result);
        }
    });
}

/////////////////////////////////////
//Dar de baja proveedor
/////////////////////////////////////
function mostrarModalParaConfirmarBajaDeUnProveedor(id) {
    var ruta = "/editarProveedor/confirmarBajaProveedor/"+id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal2", result);
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
            showInModal("modal3", result);
            mostrarModalEditarProveedores();
        }
    });
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//funcion que habilita un select despues de seleccionar un option en otro select
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
