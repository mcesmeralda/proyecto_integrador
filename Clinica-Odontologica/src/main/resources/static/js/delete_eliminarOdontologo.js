document.addEventListener('DOMContentLoaded', (event) => {
    loadOdontologos(); // Cargar los odontólogos al cargar la página
});

function clearForm() {
    document.querySelector('#odontologo_id').value = '';
    document.querySelector('#numeroMatricula').value = '';
    document.querySelector('#nombre').value = '';
    document.querySelector('#apellido').value = '';
    document.getElementById('div_odontologo_updating').style.display = "none";
}

function loadOdontologos() {
    fetch('/odontologos')
        .then(response => response.json())
        .then(data => {
            const normalizedData = normalizeData(data);
            const tbody = document.getElementById('odontologoTableBody');
            tbody.innerHTML = ''; // Limpiar la tabla antes de cargar nuevos datos
            normalizedData.forEach(odontologo => {
                const tr = document.createElement('tr');
                tr.id = `tr_${odontologo.id}`;
                tr.innerHTML = `
                        <td>${odontologo.id}</td>
                        <td>${odontologo.numeroMatricula}</td>
                        <td>${odontologo.nombre}</td>
                        <td>${odontologo.apellido}</td>
                        <td>
                            <button class="btn btn-danger" onclick="deleteBy(${odontologo.id})">X</button>
                            <button class="btn btn-info" onclick="findBy(${odontologo.id})">✏️</button>
                        </td>
                    `;
                tbody.appendChild(tr);
            });
        })
        .catch(error => console.error('Error al cargar los odontólogos:', error));
}

function normalizeData(data) {
    const seen = new Set();
    return data.filter(odontologo => {
        const key = `${odontologo.nombre} ${odontologo.apellido}`;
        if (seen.has(key)) {
            return false;
        }
        seen.add(key);
        return true;
    });
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
            document.querySelector('#odontologo_id').value = data.id;
            document.querySelector('#numeroMatricula').value = data.numeroMatricula;
            document.querySelector('#nombre').value = data.nombre;
            document.querySelector('#apellido').value = data.apellido;

            document.getElementById('div_odontologo_updating').style.display = "block";
        })
        .catch(error => console.error('Error al obtener el odontólogo:', error));
}