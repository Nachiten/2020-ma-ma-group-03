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

function verificarSesion() {
    var ruta = '/';
    var metodo = 'POST';
    $.ajax({
        type : metodo,
        url : ruta,
        datatype : "html",
        success : function (result) {
            showInModal("modal", result);

        }
    });
}

function recuperarDatosFormularioIngresos(){
    return {
        fecha: valorDe("alta-fecha"),
        periodoDeAceptacion: valorDe("alta-periodoDeAceptacion"),
        monto: valorDe("alta-monto"),
        moneda: valorDe("alta-moneda"),
        descripcion: valorDe("alta-descripcion")
    };
}

function recuperarDatosFormularioEgresos(){
    return {
        fecha: valorDe("alta-fecha"),
        medioDePago: valorDe("alta-medioDePago"),
        numeroMedioDePago: valorDe("alta-numeroMedioDePago"),
        documentoComercial: valorDe("alta-documentoComercial"),
        numeroDocumentoComercial: valorDe("alta-numeroDocumentoComercial"),
        presupuestosRequeridos: valorDe("alta-presupuestosRequeridos"),
        proveedor: valorDe("alta-proveedor"),
        revisor: valorDe("alta-revisor"),
        preciosItems: datosDeTablaPorNombreDeClase(".precioItem"),
        nombresItems: datosDeTablaPorNombreDeClase(".nombreItem"),
        nombresCategorias: datosNombresCategorias()
    };
}

function recuperarDatosFormularioPresupuesto(){
    return {
        documentoComercial: valorDe("alta-tipoDocumentoComercial"),
        numeroDocumentoComercial: valorDe("alta-numeroDocumentoComercial"),
        operacionEgreso: valorDe("alta-operacionEgreso"),
        preciosItems: datosDeTablaPorNombreDeClase(".precioItem"),
        nombresItems: datosDeTablaPorNombreDeClase(".nombreItem"),
        nombresCategorias: datosNombresCategorias()
    };
}

function recuperarDatosFormularioCriterio (){
    return {
        nombreCriterio: valorDe("alta-nombreCriterio"),
        categoriasCriterio: datosDeTablaPorNombreDeClase(".categoriaCriterio"),
    }
}


function datosNombresCategorias(){
    var categorias = document.querySelectorAll(".categoria");

    var categoriasString = '';
    for (var x = 0; x < categorias.length; x++) {
        if (categorias[x].checked){
            categoriasString += categorias[x].name;
            categoriasString += '=';
        }

    }

    var cadenaADevolver = categoriasString.substring(0, categoriasString.length - 1);

    console.log("Categorias: " + cadenaADevolver);

    return cadenaADevolver;
}



function datosDeTablaPorNombreDeClase(nombreClase) {

    var nombres = document.querySelectorAll(nombreClase);

    var nombresItemsValores = '';
    for (var x = 0; x < nombres.length; x++) {
        nombresItemsValores += nombres[x].value;
        if (x !== nombres.length - 1){
            nombresItemsValores += '=';
        }
    }

    console.log("Clase: " + nombreClase + ". Valores: " + nombresItemsValores);

    return nombresItemsValores;
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

function mostrarModalGuardadoEgreso() {
    var datos = recuperarDatosFormularioEgresos();

    if (datos.preciosItems === 'noHayPrecios'){
        alert("Se debe insertar al menos un item");
        console.log("No habia items, cancelo post");
        return;
    }

    //acá yo le digo che loco, hace un post sobre tal ruta!
    var ruta = "/egresos";
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

function mostrarModalGuardadoPresupuestos() {
    var datos = recuperarDatosFormularioPresupuesto();

    if (datos.preciosItems === 'noHayPrecios'){
        alert("Se debe insertar al menos un item");
        console.log("No habia items, cancelo post");
        return;
    }

    var ruta = "/presupuestos";
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        data     : datos,
        success : function (result) {
            showInModal("modal", result);
        }
    });
}

function mostrarModalGuardadocriterios() {
    var datos = recuperarDatosFormularioCriterio();

    var ruta = "/criterios";
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

function mostrarModalEjecucionValidador() {

    var ruta = "/validadorDeTransparencia";
    var datos = {minutos: valorDe("minutos-ok")}
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

//habilitar input para cambiar contraseña
var cambioContrasenia = document.getElementById('altaUsuarioPerfil-cambioContrasenia');
var no_cambioContrasenia = document.getElementById('altaUsuarioPerfil-noCambioContrasenia');
var contrasenia = document.getElementById('altaUsuarioPerfil-contrasenia');

function cambiarHabilitacionInputContrasenia() {
    contrasenia.disabled = !cambioContrasenia.checked;
}
cambioContrasenia.addEventListener('change', cambiarHabilitacionInputContrasenia);
no_cambioContrasenia.addEventListener('change', cambiarHabilitacionInputContrasenia);


function recuperarDatosFormularioPerfil(){
    return {
        nombre: valorDe("altaUsuarioPerfil-nombre"),
        apellido: valorDe("altaUsuarioPerfil-apellido")
    };
}
function recuperarContraseniaFormularioPerfil(){
    return {
        contrasenia: valorDe("altaUsuarioPerfil-contrasenia")

    };
}

function mostrarModalConfirmacionActualizacionDeDatos(){
    var datos = recuperarDatosFormularioPerfil();
    var ruta = "/actualizarDatosPerfil" ;
    var metodo = "POST";
    var mensaje = confirm("¿Está seguro de actualizar los datos de éste usuario?");
    if (mensaje) {
        $.ajax({
            type: metodo,
            url: ruta,
            dataType: "html",
            data: datos,
            success : function(result){
                showInModal("modal",result);
            }
        });
    }
}

function mostrarModalConfirmacionCambioDeContrasenia(){
    var datos =recuperarContraseniaFormularioPerfil();
    var ruta = "/actualizarContrasenia";
    var metodo = "POST";
    var mensaje = confirm("¿Estás seguro de cambiar tu contraseña?");
    if (mensaje) {
        $.ajax({
            type: metodo,
            url: ruta,
            dataType: "html",
            data: datos,
            success : function(result){
                showInModal("modal",result);
            }
        });
    }
}

function mostrarDetallesProveedor() {
    var datos = recuperarProveedor();

    //acá yo le digo che loco, hace un post sobre tal ruta!
    var ruta = "/egresos/proveedor";
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

function recuperarProveedor(){
    return {
        proveedor: valorDe("alta-proveedor")
    };
}


function mostrarCategoriasCriterio() {
    var datos = recuperarCriterio();

    //acá yo le digo che loco, hace un post sobre tal ruta!
    var ruta = "/egresos/criterio";
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

function recuperarCriterio(){
    return {
        criteriosCategoria: valorDe("seleccionar-criterio")
    };
}


function mostrarDetallesEgreso(id) {

    //acá yo le digo che loco, hace un post sobre tal ruta!
    var ruta = "/egresos/detalle/" + id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal", result);
        }
    });
}
