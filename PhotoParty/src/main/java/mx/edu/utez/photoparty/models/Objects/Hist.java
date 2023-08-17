package mx.edu.utez.photoparty.models.Objects;

public class Hist {
    private String id, evento, horas, direc, tel, cabina;
    private Long usuario;

    //constructores
    public Hist(){
    }

    public Hist(String id, String evento, String horas, String direc, String tel, String cabina, Long usuario) {
        this.id = id;
        this.evento = evento;
        this.horas = horas;
        this.direc = direc;
        this.tel = tel;
        this.cabina = cabina;
        this.usuario = usuario;
    }

    //Getters && Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getDirec() {
        return direc;
    }

    public void setDirec(String direc) {
        this.direc = direc;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCabina() {
        return cabina;
    }

    public void setCabina(String cabina) {
        this.cabina = cabina;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }
}
