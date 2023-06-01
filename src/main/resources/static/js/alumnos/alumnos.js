 setTimeout(function () {
     const empleadoInfo = document.querySelector('.empleadoInfo');
     empleadoInfo.remove()
    }, 3000);

 const id = document.querySelector('[data-id]').innerHTML;
 console.log("id: " + id)

 function myFunction() {
    if (confirm("¿Seguro que quieres eliminar el usuario?")) {
        location.href = '/alumnos/delete/'+id;
    }else {
        location.href = '/alumnos/';
    }
 }





