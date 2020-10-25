$(document).on('ready', funcMain);


$(function () {

	$(document).on('click', '.borrar', function (event) {
		console.log("entre en la funcion borrar");
		event.preventDefault();
		$(this).closest('tr').remove();
	});
});


function funcMain()
{
	$("add_row").on('click',nuevaFilaEnTabla);

	$("loans_table").on('click','.borrar',deleteProduct);
	$("loans_table").on('click','.editar',editProduct);

	$("body").on('click',".borrar",deleteProduct);
	$("body").on('click',".editar",editProduct);
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
*/

function editProduct(){
	var _this = this;;
	var array_fila=getRowSelected(_this);
	console.log(array_fila[0]+" - "+array_fila[1] );
	//Codigo de editar una fila lo pueden agregar aqui
}



function getRowSelected(objectPressed){
	//Obteniendo la linea que se esta eliminando
	var a=objectPressed.parentNode.parentNode;
	//b=(fila).(obtener elementos de clase columna y traer la posicion 0).(obtener los elementos de tipo parrafo y traer la posicion0).(contenido en el nodo)
	
	var item=a.getElementsByTagName("td")[0].getElementsByTagName("p")[0].innerHTML;
    var precio=a.getElementsByTagName("td")[1].getElementsByTagName("p")[0].innerHTML;

	return [item, precio];
	//console.log(item + ' ' + precio);
}

// Se incrementa por cada fila nueva agregada
var numeroFila = 0;

function nuevaFilaEnTabla()
{

	var item = document.getElementById("item").value;
	var precio = document.getElementById("precio").value;

	//var textoItems = document.getElementById("textoItems");

	//textoItems.innerHTML = '';

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
		//textoItems.innerHTML = 'El nombre de item no puede estar vacio';
		alert("El nombre de un item no puede estar vacio");
		return
	}

	if (precio === "") {
		//textoItems.innerHTML = 'El precio del item no puede estar vacio';
		alert("El precio de un item no puede estar vacio");
		return
	}

	var name_table = document.getElementById("tabla_items");

	var row = name_table.insertRow(1);

    var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);

	var itemInputOculto   = '<input type="hidden" name="nombre_I[' + numeroFila + ']" value="' + item;
	var precioInputOculto = '<input type="hidden" name="precio_I[' + numeroFila + ']" value="' + precio;

	var itemTextoMostrado   = '"> <p name="" class="non-margin" style="color = black">' + item + '</p>';
	var precioTextoMostrado = '"> <p name="" class="non-margin" style="color = black">' + precio + '</p>';

	cell1.innerHTML =  itemInputOculto + itemTextoMostrado;
	cell2.innerHTML =  precioInputOculto + precioTextoMostrado;
	//cell3.innerHTML = '<span class="icon fa-edit"></span><span class="icon fa-eraser"></span>';
	cell3.innerHTML = '<input type="button" class="editar" value="Editar" /> <input type="button" class="borrar" value="Eliminar" />';

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