package mx.edu.utez.photoparty.models.Objects;

public class pdf {
    private String id_solicitud, nombre_completo, telefono, fecha, direccion, horas, id_contrato, cabina;
    private double monto;

    public pdf() {
    }

    public pdf(String id_solicitud, String nombre_completo, String telefono, String fecha, String direccion, String horas, String id_contrato, String cabina, double monto) {
        this.id_solicitud = id_solicitud;
        this.nombre_completo = nombre_completo;
        this.telefono = telefono;
        this.fecha = fecha;
        this.direccion = direccion;
        this.horas = horas;
        this.id_contrato = id_contrato;
        this.cabina = cabina;
        this.monto = monto;
    }

    public String getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(String id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getId_contrato() {
        return id_contrato;
    }

    public void setId_contrato(String id_contrato) {
        this.id_contrato = id_contrato;
    }

    public String getCabina() {
        return cabina;
    }

    public void setCabina(String cabina) {
        this.cabina = cabina;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}

