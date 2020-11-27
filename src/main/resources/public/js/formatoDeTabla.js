$(document).on('ready', funcMain);

// Para borrar fila
$(function () {

	$(document).on('click', '.borrar', function (event) {
		console.log("Entre en la funcion borrar");
		event.preventDefault();
		$(this).closest('tr').remove();
	});
});

// Para editar fila
$(function () {
	$(document).on('click', '.editar', function (event) {
		console.log("Entre en la funcion editar");

		var idColumna = this.name;

		var itemFilaActual = document.getElementById("nombre_I[" + idColumna + "]").value;

		var elementoPrecioFila;
		if ( (elementoPrecioFila = document.getElementById("precio_I[" + idColumna + "]") ) != null){
			document.getElementById("precio").value = elementoPrecioFila.value;
		}

		var elementoCantidadFila;
		if ( (elementoCantidadFila = document.getElementById("cantidad_I[" + idColumna + "]") ) != null){
			document.getElementById("cantidad").value = elementoCantidadFila.value;
		}

		// Copiar los datos del item actual para editarlos
		document.getElementById("item").value = itemFilaActual;

		// Eliminar la fila actual
		event.preventDefault();
		$(this).closest('tr').remove();
	});
});

function funcMain()
{
	$("add_row").on('click',nuevaFilaEnItems);
}

// Se incrementa por cada fila nueva agregada
var numeroFila = 0;

function nuevaFilaEnItems()
{

	console.log("Entre en nuevaFilaItems");

	var item = document.getElementById("item").value;
	var precioUnitario = document.getElementById("precio").value;
	var cantidad = document.getElementById("cantidad").value;

	console.log("Item: [" + item + "]");
	console.log("Precio: [" + precioUnitario + "]");

	if (item === "") {
		alert("El nombre de un item no puede estar vacio.");
		return;
	}

	if (precioUnitario === "") {
		alert("El precio unitario de un item no puede estar vacio.");
		return;
	}

	if (cantidad === "") {
		alert("La cantidad de un item no puede estar vacia.");
		return;
	}

	var name_table = document.getElementById("tabla_items");

	var row = name_table.insertRow(1);

    var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	var cell5 = row.insertCell(4);

	var itemInputOculto   = '<input type="hidden" class="nombreItem" name="nombre_I[' + numeroFila + ']" id="nombre_I[' + numeroFila + ']" value="' + item;
	var precioInputOculto = '<input type="hidden" class="precioItem" name="precio_I[' + numeroFila + ']" id="precio_I[' + numeroFila + ']" value="' + precioUnitario;
	var cantidadInputOculto = '<input type="hidden" class="cantidadItem" name="cantidad_I[' + numeroFila + ']" id="cantidad_I[' + numeroFila + ']" value="' + cantidad;

	var itemTextoMostrado   = '"> <p name="" class="non-margin" style="color : white">' + item + '</p>';
	var precioTextoMostrado = '"> <p name="" class="non-margin" style="color : white">' + precioUnitario + '</p>';
	var cantidadTextoMostrado = '"> <p name="" class="non-margin" style="color : white">' + cantidad + '</p>';

	var precioTotalMostrado = '<p name="" class="non-margin" style="color : white">' + cantidad * precioUnitario + '</p>';

	cell1.innerHTML = itemInputOculto + itemTextoMostrado;
	cell2.innerHTML = precioInputOculto + precioTextoMostrado;
	cell3.innerHTML = cantidadInputOculto + cantidadTextoMostrado;
	cell4.innerHTML = precioTotalMostrado;
	cell5.innerHTML = '<div class="acciones">' +
		'<i style="color: yellow" class="fas fa-edit"></i><input type="button" class="editar" value="Editar" style="color: white" name="' + numeroFila + '"/> ' +
		'<i style="color: red" class="fas fa-trash"></i><input type="button" class="borrar" value="Eliminar" style="color: white" /></div>' +
		'<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">' +
		'<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">';

	numeroFila++;

	document.getElementById("item").value = "";
	document.getElementById("precio").value = "";
	document.getElementById("cantidad").value = "";

}

function nuevaFilaEnCategoria()
{
	console.log("Entre en nuevaFilaCategoria");

	var categoria = document.getElementById("item").value;

	console.log("Categoria: [" + categoria + "]");

	if (categoria === ""){
		console.log("No hay categoría");
	} else {
		console.log("Si hay categoría");
	}

	if (categoria === "") {
		//textoItems.innerHTML = 'El nombre de item no puede estar vacio';
		alert("El nombre de una categoria no puede estar vacio");
		return
	}

	var name_table = document.getElementById("tabla_categorias");

	var row = name_table.insertRow(1);

	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);

	var categoriaInputOculto   = '<input type="hidden" class="categoriaCriterio" name="nombre_I[' + numeroFila + ']" id="nombre_I[' + numeroFila + ']" value="' + categoria;
	var categoriaTextoMostrado = '"> <p name="" class="non-margin" style="color = white">' + categoria + '</p>';

	cell1.innerHTML =  categoriaInputOculto + categoriaTextoMostrado;
	cell2.innerHTML = '<div class="acciones">' +
		'<i class="fas fa-edit" ></i><input type="button" class="editar" value="Editar" name="' + numeroFila + '"/> ' +
		'<i class="fas fa-trash"></i><input type="button" class="borrar" value="Eliminar" /></div>' +
		'<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">' +
		'<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">';

	numeroFila++;
}


function seleccionarCategoria(){
	console.log("Entre en seleccionarCategoria");

	var categoria = document.getElementById("categoria").value;

	var name_table = document.getElementById("tabla_items");


}

  
function format(input){
	var num = input.value.replace(/\,/g,'');
	if(!isNaN(num)){
		input.value = num;
	}
	else{ alert('Solo se permiten numeros');
		input.value = input.value.replace(/[^\d\.]*/g,'');
	}
}
