package mx.edu.utez.photoparty.models.Objects;

public class Cts_users {
    private String id, fecha, horas, direccion, nombre, telefono, cabina;

    //Constructores
    public Cts_users(){
    }

    public Cts_users(String id, String fecha, String horas, String direccion, String nombre, String telefono, String cabina) {
        this.id = id;
        this.fecha = fecha;
        this.horas = horas;
        this.direccion = direccion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.cabina = cabina;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCabina() {
        return cabina;
    }

    public void setCabina(String cabina) {
        this.cabina = cabina;
    }
}
