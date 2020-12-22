/*Nueva entidad jurídica*/
function mostrarModalNuevaEntidadJurdica() {
    var ruta = "/nuevaEntidadJuridica";
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

function mostrarModalConfirmarGuardarNuevaEntidadJuridica() {
    var ruta   = "/confirmarNuevaEntidadJuridica";
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

function recuperarDatosFormularioAltaNuevaEntidadJuridica() {
    return {
        nombreEntidadJuridicaNueva   : valorDe("altaEntidadJuridica-nombreNuevo")
    };
}

function mostrarModalConfirmacionAltaNuevaEntidadJuridica() {
    var datos  = recuperarDatosFormularioAltaNuevaEntidadJuridica();
    var ruta   = "/GuardarNuevaEntidadJuridica";
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        data     : datos,
        success  : function (result) {
            showInModal("modal3", result);
            mostrarModalNuevaEntidadJurdica();
        }
    });
}

function mostrarModalConfirmarGuardarNuevaEntidadEmpresa() {
    var ruta   = "/confirmarNuevaEntidadEmpresa";
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

function recuperarDatosFormularioNuevaEmpresa() {
    return {
        entidadJuridicaSeleccionadaEmpresa  : $("#altaEmpresa-EJSeleccionada option:selected").val(),
        nombreFicticioEmpresa               : valorDe("altaEmpresa-nombreFicticio"),
        codigoInscripcionDefinitiva         : valorDe("altaEmpresa-codigo"),
        cuitEmpresa                         : valorDe("altaEmpresa-cuit"),
        razonSocialEmpresa                  : valorDe("altaEmpresa-razonSocial"),
        sectorEmpresa                       : $("#altaEmpresa-sector option:selected").val(),
        promedioVAEmpresa                   : valorDe("altaEmpresa-promedioVA"),
        cantPersonalEmpresa                 : valorDe("altaEmpresa-cantPersonal"),
        barrio                              : valorDe("altaEmpresa-barrio"),
        calle                               : valorDe("altaEmpresa-calle"),
        altura                              : valorDe("altaEmpresa-altura"),
        departamento                        : valorDe("altaEmpresa-departamento"),
        piso                                : valorDe("altaEmpresa-piso"),
        pais                                : $("#altaEmpresa-pais option:selected").val(),
        provincia                           : $("#altaEmpresa-provincia option:selected").val(),
        ciudad                              : $("#altaEmpresa-ciudad option:selected").val()
    };
}
function mostrarModalConfirmacionAltaNuevaEntidadEmpresa() {
    var datos  = recuperarDatosFormularioNuevaEmpresa();
    var ruta   = "/GuardarNuevaEntidadEmpresa";
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

function mostrarModalConfirmarGuardarNuevaEntidadOrgSoc() {
    var ruta   = "/confirmarNuevaEntidadOrgSoc";
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

function recuperarDatosFormularioNuevaOrgSoc() {
    return {
        entidadJuridicaSeleccionadaOrgSoc  : $("#altaOrgSoc-EJSeleccionada option:selected").val(),
        nombreFicticioOrgSoc               : valorDe("altaOrgSoc-nombreFicticio"),
        razonSocialOrgSoc                  : valorDe("altaOrgSoc-razonSocial")
    };
}

function mostrarModalConfirmacionAltaNuevaEntidadOrgSoc() {
    var datos  = recuperarDatosFormularioNuevaOrgSoc();
    var ruta   = "/GuardarNuevaEntidadOrgSoc";
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

function mostrarModalConfirmarGuardarNuevaEntidadBase() {
    var ruta   = "/confirmarNuevaEntidadBase";
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

function recuperarDatosFormularioNuevaBase() {
    return {
        entidadJuridicaSeleccionadaBase  : $("#altaBase-EJSeleccionada option:selected").val(),
        nombreFicticioBase               : valorDe("altaBase-nombreFicticio")
    };
}
function mostrarModalConfirmacionAltaNuevaEntidadBase() {
    var datos  = recuperarDatosFormularioNuevaBase();
    var ruta   = "/GuardarNuevaEntidadBase";
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

//////////////////////////////
/*Habilitar entidad jurídica*/
//////////////////////////////
function mostrarModalHabilitarEntidadesJuridicas() {
    var ruta   = "/habilitarEntidadesJuridicas";
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

function habilitarEntidadJuridica(id) {
    var ruta   = "/confirmarHabilitarEntidadJuridica/"+id;
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

function mostrarModalConfirmacionHabilitacionEntidadJuridica(id) {
    var ruta   = "/confirmacionHabilitacionEntidadJuridica/"+id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal3", result);
            mostrarModalHabilitarEntidadesJuridicas();
        }
    });
}

///////////////////////////
/*Editar entidad jurídica*/
///////////////////////////
function mostrarModalEditarEntidadesJuridicas() {
    var ruta   = "/editarEntidadesJuridicas";
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

//Empresas
function mostrarModalParaConfirmarListarEmpresas(id) {
    var ruta   = "/confirmarListarEmpresas/"+id;
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

function mostrarModalListaEmpresas(id) {
    var ruta   = "/listadoEmpresas/"+id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal3", result);
        }
    });
}

function mostrarModalParaConfirmarDarDeBajaEntidadEmpresa(id){
    var ruta   = "/confirmarBajaEmpresa/"+id;
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

function mostrarModalConfirmacionBajaEmpresa(id) {
    var ruta   = "/confirmacionBajaEmpresa/"+id;
    var metodo = "DELETE";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal5", result);
            mostrarModalListaEmpresas(id);
        }
    });
}

function mostrarModalConfirmarHabilitarEmpresa(id) {
    var ruta   = "/confirmarHabilitarEmpresa/"+id;
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

function mostrarModalConfirmacionHabilitacionEmpresa(id) {
    var ruta   = "/confirmacionHabilitarEmpresa/"+id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal5", result);
            mostrarModalListaEmpresas(id);
        }
    });
}

//Organizaciones de sector social
function mostrarModalParaConfirmarListarOrgSoc(id) {
    var ruta   = "/confirmarListarOrgSoc/"+id;
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

function mostrarModalListaOrgSoc(id) {
    var ruta   = "/listadoOrgSoc/"+id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal3", result);
        }
    });
}

function mostrarModalParaConfirmarDarDeBajaEntidadOrgSoc(id) {
    var ruta   = "/confirmarBajaOrgSoc/"+id;
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

function mostrarModalConfirmacionBajaOrgSoc(id) {
    var ruta   = "/confirmacionBajaOrgSoc/"+id;
    var metodo = "DELETE";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal5", result);
            mostrarModalListaOrgSoc(id);
        }
    });
}

function mostrarModalConfirmarHabilitarOrgSoc(id) {
    var ruta   = "/confirmarHabilitarOrgSoc/"+id;
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

function mostrarModalConfirmacionHabilitarOrgSoc(id) {
    var ruta   = "/confirmacionHabilitarOrgSoc/"+id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal5", result);
            mostrarModalListaOrgSoc(id);
        }
    });
}

//Entidades base
function mostrarModalParaConfirmarListarEntidadesBase(id) {
    var ruta   = "/confirmarListarEntidadesBase/"+id;
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

function mostrarModalListaEntidadesBase(id) {
    var ruta   = "/listadoEntidadesBase/"+id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal3", result);
        }
    });
}

function darDeBajaEntidadJuridica(id){
    var ruta   = "/confirmarBajaEntidadJuridica/"+id;
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

function mostrarModalConfirmacionBajaEntidadJuridica(id){
    var ruta   = "/confirmacionBajaEntidadJuridica/"+id;
    var metodo = "DELETE";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal3", result);
            mostrarModalEditarEntidadesJuridicas();
        }
    });
}

function mostrarModalParaConfirmarEditarUnaEntidadJuridica(id){
    var ruta   = "/confirmarEditarEntidadJuridica/"+id;
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

function mostrarModalParaEditarEntidadJuridica(id){
    var ruta   = "/editarEntidadJuridica/"+id;
    var metodo = "GET";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        success  : function (result) {
            showInModal("modal3", result);
        }
    });
}

function recuperarDatosNuevoNombreEntidadJuridica() {
    return {
       nombre   : valorDe("altaEntidad-nombre")
    };


}

function mostrarModalParaConfirmarCambiosRealizadosEnEntidadJuridica(id){
    var datos = recuperarDatosNuevoNombreEntidadJuridica();
    var ruta = "/confirmarCambiosDatosEnEntidadJuridica/"+id;
    var metodo = "POST";
    $.ajax({
        type     : metodo,
        url      : ruta,
        datatype : "html",
        data : datos,
        success  : function (result) {
            showInModal("modal4", result);
            mostrarModalEditarEntidadesJuridicas();
        }
    });

}


////////////////////////

/*------------------------*/
$('#altaEmpresa-pais').change(function(){
    $('#altaEmpresa-provincia').removeAttr('disabled');
    filterSelectOptions($("#altaEmpresa-provincia"), "data-attribute", $(this).val());
    filterSelectOptions($("#altaEmpresa-ciudad"), "data-attribute", $(this).val());
});

$('#altaEmpresa-provincia').change(function(){
    $('#altaEmpresa-ciudad').removeAttr('disabled');
    filterSelectOptions($("#altaEmpresa-ciudad"), "data-attribute", $(this).val());
});

function filterSelectOptions(selectElement, attributeName, attributeValue) {
    if (selectElement.data("currentFilter") != attributeValue) {
        selectElement.data("currentFilter", attributeValue);
        var originalHTML = selectElement.data("originalHTML");
        if (originalHTML)
            selectElement.html(originalHTML)
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

function mostrarSeleccion(elemento) {
    if (elemento.value === "empresa"){
        document.getElementById("nuevaOrgSoc").style.display = "none";
        document.getElementById("nuevaBase").style.display = "none";
        document.getElementById("nuevaEmpresa").style.display = "block";
    }
    if (elemento.value === "orgSoc"){
        document.getElementById("nuevaOrgSoc").style.display = "block";
        document.getElementById("nuevaBase").style.display = "none";
        document.getElementById("nuevaEmpresa").style.display = "none";
    }
    if (elemento.value === "base"){
        document.getElementById("nuevaOrgSoc").style.display = "none";
        document.getElementById("nuevaBase").style.display = "block";
        document.getElementById("nuevaEmpresa").style.display = "none";
    }
}
/*
* $('input:radio[name=nuevaEntidad]:checked').val() ---> devuelve el valor del input radio seleccionado
* */