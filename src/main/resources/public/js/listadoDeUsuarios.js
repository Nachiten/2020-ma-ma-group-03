$(function () {

    $(document).on('click', '.borrar', function (event) {
        console.log("Entre en la funcion borrar");
        event.preventDefault();
        $(this).closest('tr').remove();
    });
});

