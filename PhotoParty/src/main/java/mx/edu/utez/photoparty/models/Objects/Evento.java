package mx.edu.utez.photoparty.models.Objects;

public class Evento {
    private String nombre, start, color,
            hora_inicio,hora_final ,telefono,total,direccion,hora_extra,
            segnombre,ape_p,ape_m,correo,cabina, a;
    private long id ;

    public Evento(String nombre, String start, String color, String hora_inicio, String hora_final, String telefono, String total, String direccion, String hora_extra, String segnombre, String ape_p, String ape_m, String correo, String cabina, long id) {
        this.nombre = nombre;
        this.start = start;
        this.color = color;
        this.hora_inicio = hora_inicio;
        this.hora_final = hora_final;
        this.telefono = telefono;
        this.total = total;
        this.direccion = direccion;
        this.hora_extra = hora_extra;
        this.segnombre = segnombre;
        this.ape_p = ape_p;
        this.ape_m = ape_m;
        this.correo = correo;
        this.cabina = cabina;
        this.id = id;
    }


    public Evento() {

    }

    public Evento(long id, String nombre, String start, String color, String horaInicio, String horaFinal, String telefono, String direccion, String total, String horaExtra, String segnombre, String apeP, String apeM, String correo, String cabina) {
        this.id = id;
        this.nombre = nombre;
        this.start = start;
        this.color = color;
        this.hora_inicio = horaInicio;
        this.hora_final = horaFinal;
        this.telefono = telefono;
        this.total = total;
        this.direccion = direccion;
        this.hora_extra = horaExtra;
        this.segnombre = segnombre;
        this.ape_p = apeP;
        this.ape_m = apeM;
        this.correo = correo;
        this.cabina = cabina;

    }

    public Evento(String a, String nombre, String segnombre, String apeP, String apeM, String telefono, String correo, String start, String horaInicio, String horaFinal, String horaExtra, String direccion, String total, String cabina, String color) {
        this.a = a;
        this.nombre = nombre;
        this.segnombre = segnombre;
        this.ape_p = apeP;
        this.ape_m = apeM;
        this.telefono = telefono;
        this.correo = correo;
        this.start = start;
        this.hora_inicio = horaInicio;
        this.hora_final = horaFinal;
        this.hora_extra = horaExtra;
        this.direccion = direccion;
        this.total = total;
        this.cabina = cabina;
        this.color = color;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_final() {
        return hora_final;
    }

    public void setHora_final(String hora_final) {
        this.hora_final = hora_final;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHora_extra() {
        return hora_extra;
    }

    public void setHora_extra(String hora_extra) {
        this.hora_extra = hora_extra;
    }

    public String getSegnombre() {
        return segnombre;
    }

    public void setSegnombre(String segnombre) {
        this.segnombre = segnombre;
    }

    public String getApe_p() {
        return ape_p;
    }

    public void setApe_p(String ape_p) {
        this.ape_p = ape_p;
    }

    public String getApe_m() {
        return ape_m;
    }

    public void setApe_m(String ape_m) {
        this.ape_m = ape_m;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCabina() {
        return cabina;
    }

    public void setCabina(String cabina) {
        this.cabina = cabina;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
