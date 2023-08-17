package mx.edu.utez.photoparty.models.Objects;

public class Cliente {
    public String id, nombre, segnombre, ape_p, ape_m, tel, correo;

    //Constructores
    public Cliente(){
    }

    public Cliente(String id, String nombre, String segnombre, String ape_p, String ape_m, String tel, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.segnombre = segnombre;
        this.ape_p = ape_p;
        this.ape_m = ape_m;
        this.tel = tel;
        this.correo = correo;
    }

    //Getter && Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
