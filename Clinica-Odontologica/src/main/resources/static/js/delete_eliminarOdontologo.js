// Función para eliminar un odontólogo por su ID
function deleteBy(id) {
    const url = `/odontologos/${id}`;
    const settings = {
        method: 'DELETE'
    };

    fetch(url, settings)
        .then(response => {
            if (response.ok) {
                // Eliminar la fila de la tabla
                const row = document.getElementById(`tr_${id}`);
                if (row) {
                    row.remove();
                    console.log(`Odontólogo con id ${id} eliminado`);
                } else {
                    console.error(`No se encontró la fila con id tr_${id}`);
                }
                clearForm(); // Limpiar el formulario
                loadOdontologos(); // Recargar la lista de odontólogos
            } else {
                console.error('Error al eliminar el odontólogo, respuesta no OK');
            }
        })
        .catch(error => console.error('Error al eliminar el odontólogo:', error));
}
// Función para encontrar un odontólogo por su ID y cargar sus datos en el formulario de actualización
function findBy(id) {
    const url = `/odontologos/${id}`;
    const settings = {
        method: 'GET'
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            document.querySelector('#odontologo_id').value = data.id;
            document.querySelector('#numeroMatricula').value = data.numeroMatricula;
            document.querySelector('#nombre').value = data.nombre;
            document.querySelector('#apellido').value = data.apellido;


            document.getElementById('div_odontologo_updating').style.display = "block";
        })
        .catch(error => console.error('Error al obtener el odontólogo:', error));
}
