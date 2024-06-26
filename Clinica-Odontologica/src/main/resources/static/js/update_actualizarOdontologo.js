document.addEventListener('DOMContentLoaded', (event) => {
    loadOdontologos(); // Cargar los odontólogos al cargar la página
});

function clearForm() {
    document.querySelector('#update_odontologo_form').reset();
    document.getElementById('div_odontologo_updating').style.display = "none";
}

function loadOdontologos() {
    const url = '/odontologos'; // Corrige el endpoint aquí si es necesario
    fetch(url)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById('odontologoTableBody');
            tableBody.innerHTML = '';
            data.forEach(odontologo => {
                const odontologoRow = document.createElement('tr');
                odontologoRow.id = `tr_${odontologo.id}`;
                odontologoRow.innerHTML = `
                    <td>${odontologo.id}</td>
                    <td>${odontologo.numeroMatricula}</td>
                    <td>${odontologo.nombre}</td>
                    <td>${odontologo.apellido}</td>
                    <td>
                        <button class="btn btn-danger" onclick="deleteBy(${odontologo.id})">X</button>
                        <button class="btn btn-info" onclick="findBy(${odontologo.id})">✏️</button>
                    </td>
                `;
                tableBody.appendChild(odontologoRow);
            });
        })
        .catch(error => console.error('Error al cargar los odontólogos:', error));
}

function deleteBy(id) {
    const url = `/odontologos/${id}`;
    const settings = {
        method: 'DELETE'
    };

    fetch(url, settings)
        .then(response => {
            if (response.ok) {
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

function findBy(id) {
    const url = `/odontologos/${id}`;
    const settings = {
        method: 'GET'
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            console.log(data); // Agregado para verificar los datos que se están recibiendo
            document.querySelector('#odontologo_id').value = data.id;
            document.querySelector('#numeroMatricula').value = data.numeroMatricula;
            document.querySelector('#nombre').value = data.nombre;
            document.querySelector('#apellido').value = data.apellido;

            document.getElementById('div_odontologo_updating').style.display = "block";
        })
        .catch(error => console.error('Error al obtener el odontólogo:', error));
}
