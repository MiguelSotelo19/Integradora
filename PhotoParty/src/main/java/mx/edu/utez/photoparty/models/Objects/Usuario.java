package mx.edu.utez.photoparty.models.Objects;

public class Usuario {
    public Long id;
    public String nombre, segnombre, ape_p, ape_m, tel, usuario, contrasenia;

    //Constructores
    public Usuario(){
    }

    public Usuario(Long id, String nombre, String segnombre, String ape_p, String ape_m, String tel, String usuario, String contrasenia) {
        this.id = id;
        this.nombre = nombre;
        this.segnombre = segnombre;
        this.ape_p = ape_p;
        this.ape_m = ape_m;
        this.tel = tel;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public Usuario(Long id, String nombre, String segnombre, String ape_p, String ape_m, String tel, String usuario) {
        this.id = id;
        this.nombre = nombre;
        this.segnombre = segnombre;
        this.ape_p = ape_p;
        this.ape_m = ape_m;
        this.tel = tel;
        this.usuario = usuario;
    }

    //Getter && Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
