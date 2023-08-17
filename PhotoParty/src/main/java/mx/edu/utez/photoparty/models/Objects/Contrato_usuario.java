package mx.edu.utez.photoparty.models.Objects;

public class Contrato_usuario {
    public Long fk_id_usuario;
    public String fk_id_contrato;

    //Constructores
    public Contrato_usuario(){
    }

    public Contrato_usuario(Long fk_id_usuario, String fk_id_contrato) {
        this.fk_id_usuario = fk_id_usuario;
        this.fk_id_contrato = fk_id_contrato;
    }
    //Getter && Setter
    public String getFk_id_contrato() {
        return fk_id_contrato;
    }

    public void setFk_id_contrato(String fk_id_contrato) {
        this.fk_id_contrato = fk_id_contrato;
    }

    public Long getFk_id_usuario() {
        return fk_id_usuario;
    }

    public void setFk_id_usuario(Long fk_id_usuario) {
        this.fk_id_usuario = fk_id_usuario;
    }
}
