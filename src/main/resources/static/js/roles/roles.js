const id = document.querySelector('[data-id]').innerHTML;
console.log("id: " + id)

function myFunction() {
    if (confirm("¿Seguro que quieres eliminar el rol?")) {
        location.href = '/roles/delete/'+id;
    }else {
        location.href = '/roles/';
    }
}