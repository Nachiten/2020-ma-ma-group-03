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
		var precioFilActual = document.getElementById("precio_I[" + idColumna + "]").value;

		// Copiar los datos del item actual para editarlos
		document.getElementById("item").value = itemFilaActual;
		document.getElementById("precio").value = precioFilActual;

		// Eliminar la fila actual
		event.preventDefault();
		$(this).closest('tr').remove();
	});
});

function funcMain()
{
	$("add_row").on('click',nuevaFilaEnTabla);

	//$("loans_table").on('click','.borrar', borrar);
	//$("loans_table").on('click','.editar', editProduct);

	//$("body").on('click',".borrar", deleteProduct);
	//$("body").on('click',".editar", editProduct);
}

/*
function funcEliminarProductosso(){
	//Obteniendo la fila que se esta eliminando
	var a=this.parentNode.parentNode;
	//Obteniendo el array de todos loe elementos columna en esa fila
	//var b=a.getElementsByTagName("td");
	var cantidad=a.getElementsByTagName("td")
	console.log(a);

	$(this).parent().parent().fadeOut("slow",function(){$(this).remove();});
}

function deleteProduct(){
	//Guardando la referencia del objeto presionado
	var _this = this;
	//Obtener las filas los datos de la fila que se va a eliminar
	var array_fila=getRowSelected(_this);

	$(this).parent().parent().fadeOut("slow",function(){$(this).remove();});
}

function editProduct(){
	var _this = this;
	var array_fila = getRowSelected(_this);
	console.log(array_fila[0]+" - "+array_fila[1] );
	//Codigo de editar una fila lo pueden agregar aqui
}

function getRowSelected(objectPressed){
	//Obteniendo la linea que se esta eliminando
	var a=objectPressed.parentNode.parentNode;
	//b=(fila).(obtener elementos de clase columna y traer la posicion 0).(obtener los elementos de tipo parrafo y traer la posicion0).(contenido en el nodo)
	
	var item = a.getElementsByTagName("td")[0].getElementsByTagName("p")[0].innerHTML;
    var precio=a.getElementsByTagName("td")[1].getElementsByTagName("p")[0].innerHTML;

	return [item, precio];
	//console.log(item + ' ' + precio);
}
*/

// Se incrementa por cada fila nueva agregada
var numeroFila = 0;

function nuevaFilaEnTabla()
{

	var item = document.getElementById("item").value;
	var precio = document.getElementById("precio").value;

	console.log("Item: [" + item + "]");
	console.log("Precio: [" + precio + "]");

	if (item === ""){
		console.log("No hay item");
	} else {
		console.log("Si hay item");
	}

	if (precio === ""){
		console.log("No hay precio");
	} else {
		console.log("Si hay precio");
	}

	if (item === "") {
		alert("El nombre de un item no puede estar vacio");
		return
	}

	if (precio === "") {
		alert("El precio de un item no puede estar vacio");
		return
	}

	var name_table = document.getElementById("tabla_items");

	var row = name_table.insertRow(1);

    var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);

	var itemInputOculto   = '<input type="hidden" name="nombre_I[' + numeroFila + ']" id="nombre_I[' + numeroFila + ']" value="' + item;
	var precioInputOculto = '<input type="hidden" name="precio_I[' + numeroFila + ']" id="precio_I[' + numeroFila + ']" value="' + precio;

	var itemTextoMostrado   = '"> <p name="" class="non-margin" style="color = black">' + item + '</p>';
	var precioTextoMostrado = '"> <p name="" class="non-margin" style="color = black">' + precio + '</p>';

	// name="nombre_I[' + numeroFila + ']"

	cell1.innerHTML =  itemInputOculto + itemTextoMostrado;
	cell2.innerHTML =  precioInputOculto + precioTextoMostrado;
	//cell3.innerHTML = '<span class="icon fa-edit"></span><span class="icon fa-eraser"></span>';
	cell3.innerHTML = '<div class="acciones">' +
		'<i class="fas fa-edit"></i><input type="button" class="editar" value="Editar" name="' + numeroFila + '"/> ' +
		'<i class="fas fa-trash"></i><input type="button" class="borrar" value="Eliminar" /></div>' +
		'<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">' +
		'<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">';

	numeroFila++;
}

function nuevaFilaEnCriterio()
{
	var criterio = document.getElementById("criterio").value;

	console.log("Criterio: [" + criterio + "]");

	if (criterio === ""){
		console.log("No hay criterio");
	} else {
		console.log("Si hay criterio");
	}

	if (criterio === "") {
		//textoItems.innerHTML = 'El nombre de item no puede estar vacio';
		alert("El nombre de un criterio no puede estar vacio");
		return
	}

	var name_table = document.getElementById("tabla_criterios");

	var row = name_table.insertRow(1);

	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);


	var criterioInputOculto   = '<input type="hidden" name="nombre_I[' + numeroFila + ']" value=" ' + criterio;

	var criterioTextoMostrado   = '"> <p name="" class="non-margin" style="color = black">' + criterio + '</p>';

	cell1.innerHTML =  criterioInputOculto + criterioTextoMostrado;
	cell2.innerHTML = '<div class="acciones">' +
		'<i class="fas fa-edit"></i><input type="button" class="editar" value="Editar" /> ' +
		'<i class="fas fa-trash"></i><input type="button" class="borrar" value="Eliminar" /></div>' +
		'<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">' +
		'<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">';

	numeroFila++;
}

function nuevaFilaEnCategoria()
{
	var categoria = document.getElementById("categoria").value;

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


	var categoriaInputOculto   = '<input type="hidden" name="nombre_I[' + numeroFila + ']" value="' + categoria;

	var categoriaTextoMostrado   = '"> <p name="" class="non-margin" style="color = black">' + categoria + '</p>';

	cell1.innerHTML =  categoriaInputOculto + categoriaTextoMostrado;
	cell2.innerHTML = '<div class="acciones">' +
		'<i class="fas fa-edit"></i><input type="button" class="editar" value="Editar" /> ' +
		'<i class="fas fa-trash"></i><input type="button" class="borrar" value="Eliminar" /></div>' +
		'<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">' +
		'<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">';

	numeroFila++;
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