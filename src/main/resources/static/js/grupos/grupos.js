 setTimeout(function () {
     const parrafoUno = document.querySelector('.departamentoInfo');
     parrafoUno.remove()
    }, 3000);

 const id = document.querySelector('[data-id]').innerHTML;

 function myFunction() {
     if (confirm("¿Seguro que quieres eliminar el departamento?")) {
         location.href = '/departamentos/delete/'+id;
     }
 }