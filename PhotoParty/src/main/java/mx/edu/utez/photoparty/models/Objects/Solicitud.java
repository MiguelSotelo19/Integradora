package mx.edu.utez.photoparty.models.Objects;

public class Solicitud {
    private Long id;
    private String fecha, hr_inicio, hr_final, direccion, fk_id_cliente, fk_id_contrato;
    private int extra;

    //Constructores
    public Solicitud(){
    }

    public Solicitud(Long id, String fecha, String hr_inicio, String hr_final, String direccion, String fk_id_cliente, String fk_id_contrato, int extra) {
        this.id = id;
        this.fecha = fecha;
        this.hr_inicio = hr_inicio;
        this.hr_final = hr_final;
        this.direccion = direccion;
        this.fk_id_cliente = fk_id_cliente;
        this.fk_id_contrato = fk_id_contrato;
        this.extra = extra;
    }

    public Solicitud(String start, String horaInicio, String horaFinal, String direccion, String id, String id1, int i) {

        this.fecha = start;
        this.hr_inicio = horaInicio;
        this.hr_final = horaFinal;
        this.direccion = direccion;
        this.fk_id_cliente = id;
        this.fk_id_contrato = id1;
        this.extra = i;
    }
    public Solicitud(String start, String horaInicio, String horaFinal, String direccion, String id1, int i) {

        this.fecha = start;
        this.hr_inicio = horaInicio;
        this.hr_final = horaFinal;
        this.direccion = direccion;
        this.fk_id_contrato = id1;
        this.extra = i;
    }

    public Solicitud(String start, String horaInicio, String horaFinal, String s) {
        this.fecha = start;
        this.hr_inicio = horaInicio;
        this.hr_final = horaFinal;
        this.fk_id_contrato = s;
    }


    //Getter && Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHr_inicio() {
        return hr_inicio;
    }

    public void setHr_inicio(String hr_inicio) {
        this.hr_inicio = hr_inicio;
    }

    public String getHr_final() {
        return hr_final;
    }

    public void setHr_final(String hr_final) {
        this.hr_final = hr_final;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFk_id_cliente() {
        return fk_id_cliente;
    }

    public void setFk_id_cliente(String fk_id_cliente) {
        this.fk_id_cliente = fk_id_cliente;
    }

    public String getFk_id_contrato() {
        return fk_id_contrato;
    }

    public void setFk_id_contrato(String fk_id_contrato) {
        this.fk_id_contrato = fk_id_contrato;
    }

    public int getExtra() {
        return extra;
    }

    public void setExtra(int extra) {
        this.extra = extra;
    }
}
