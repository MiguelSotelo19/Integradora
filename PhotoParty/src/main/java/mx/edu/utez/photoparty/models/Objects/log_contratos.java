package mx.edu.utez.photoparty.models.Objects;

public class log_contratos {
    private Double monto;
    private Long telefono;
    private String id, fecha_act, fecha_ev, horas, direccion, id_cliente, nombre, correo;

    public log_contratos(){
    }

    public log_contratos(Double monto, long telefono, String id, String fecha_act, String fecha_ev, String horas, String direccion, String id_cliente, String nombre, String correo) {
        this.monto = monto;
        this.telefono = telefono;
        this.id = id;
        this.fecha_act = fecha_act;
        this.fecha_ev = fecha_ev;
        this.horas = horas;
        this.direccion = direccion;
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.correo = correo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha_act() {
        return fecha_act;
    }

    public void setFecha_act(String fecha_act) {
        this.fecha_act = fecha_act;
    }

    public String getFecha_ev() {
        return fecha_ev;
    }

    public void setFecha_ev(String fecha_ev) {
        this.fecha_ev = fecha_ev;
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

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
