package mx.edu.utez.photoparty.models.Objects;

public class Contrato {
    private Long fk_id_cabina2;
    private String id, actual, fk_id_cabina, color;
    private Double total;

    //Constructores
    public Contrato(){
    }

    public Contrato(Long fk_id_cabina2, String id, String actual, String fk_id_cabina, String color, Double total) {
        this.fk_id_cabina2 = fk_id_cabina2;
        this.id = id;
        this.actual = actual;
        this.fk_id_cabina = fk_id_cabina;
        this.color = color;
        this.total = total;
    }

    public Contrato(String id, String fk_id_cabina, String actual, Double total) {
        this.id = id;
        this.fk_id_cabina = fk_id_cabina;
        this.actual = actual;
        this.total = total;
    }

    public Contrato(String id, Long fk_id_cabina2, String actual, Double total) {
        this.id = id;
        this.fk_id_cabina2 = fk_id_cabina2;
        this.actual = actual;
        this.total = total;
    }

    public Contrato(String id, String cabina, Double aDouble, String color) {
        this.id = id;
        this.fk_id_cabina = cabina;
        this.total = aDouble;
        this.color = color;
    }

    //Getter && Setter


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getFk_id_cabina2() {
        return fk_id_cabina2;
    }

    public void setFk_id_cabina2(Long fk_id_cabina2) {
        this.fk_id_cabina2 = fk_id_cabina2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFk_id_cabina() {
        return fk_id_cabina;
    }

    public void setFk_id_cabina(String fk_id_cabina) {
        this.fk_id_cabina = fk_id_cabina;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
