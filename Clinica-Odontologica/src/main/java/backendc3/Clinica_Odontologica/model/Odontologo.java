
package backendc3.Clinica_Odontologica.model;

public class Odontologo {
        private int id;
        private String numeroMatricula;
        private String nombre;
        private String apellido;

        // Constructor con ID (usado para crear instancias desde la base de datos)
        public Odontologo(int id, String numeroMatricula, String nombre, String apellido) {
                this.id = id;
                this.numeroMatricula = numeroMatricula;
                this.nombre = nombre;
                this.apellido = apellido;
        }

        // Constructor sin ID (usado para crear nuevas instancias que serán guardadas en la base de datos)
        public Odontologo(String numeroMatricula, String nombre, String apellido) {
                this.numeroMatricula = numeroMatricula;
                this.nombre = nombre;
                this.apellido = apellido;
        }

        // Getters y setters
        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getNumeroMatricula() {
                return numeroMatricula;
        }

        public void setNumeroMatricula(String numeroMatricula) {
                this.numeroMatricula = numeroMatricula;
        }

        public String getNombre() {
                return nombre;
        }

        public void setNombre(String nombre) {
                this.nombre = nombre;
        }

        public String getApellido() {
                return apellido;
        }

        public void setApellido(String apellido) {
                this.apellido = apellido;
        }

        @Override
        public String toString() {
                return "Odontologo{" +
                        "id=" + id +
                        ", numeroMatricula='" + numeroMatricula + '\'' +
                        ", nombre='" + nombre + '\'' +
                        ", apellido='" + apellido + '\'' +
                        '}';
        }
}