function clearForm() {
    document.querySelector('#update_odontologo_form').reset();
    document.getElementById('div_odontologo_updating').style.display = "none";
}

function loadPacientes() {
    const url = '/odontologo';
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
                            <button type="button" class="btn btn-danger" onclick="deleteBy(${odontologo.id})">&times;</button>
                            <button type="button" class="btn btn-info" onclick="findBy(${odontologo.id})">${odontologo.id}</button>
                        </td>
                    `;
                tableBody.appendChild(odontologoRow);
            });
        })
        .catch(error => console.error('Error al cargar los odontologo:', error));
}