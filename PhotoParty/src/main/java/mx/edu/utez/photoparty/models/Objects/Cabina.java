package mx.edu.utez.photoparty.models.Objects;

import jakarta.servlet.http.Part;
import java.io.InputStream;

public class Cabina {
    private Long id,id_cab;
    private String nombre, descripcion;
    private Float costo, extra;
    private Boolean activo;
    private InputStream imagen;
    private byte[] imagenBytes;






    public Cabina(Long id, String nombre, String descripcion, Float costo, Float extra, Boolean activo, InputStream imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.extra = extra;
        this.activo = activo;
        this.imagen = imagen;
    }

    //Constructores
    public Cabina(){
    }


    public Cabina(Long id, String nombre, String descripcion, Float costo, Float extra, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.extra = extra;
        this.activo = activo;
    }

    public Cabina(Long id, String nombre, String descripcion, float costo, float extra, boolean activo, byte[] imagenBytes) {
        // Inicializar los atributos de la clase, incluido el array de bytes de la imagen
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.extra = extra;
        this.activo = activo;
        this.imagenBytes = imagenBytes;
    }

    public Cabina(String nombre, InputStream inputStream) {
        this.nombre = nombre;
        this.imagen = inputStream;
    }

    public Cabina(Long id, Long id_cab, String nombre, String descripcion, Float costo, Float extra, Boolean activo, InputStream imagen, byte[] imagenBytes) {
        this.id = id;
        this.id_cab = id_cab;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.extra = extra;
        this.activo = activo;
        this.imagen = imagen;
        this.imagenBytes = imagenBytes;
    }
//Getter && Setter


    public Long getId_cab() {
        return id_cab;
    }

    public void setId_cab(Long id_cab) {
        this.id_cab = id_cab;
    }

    public InputStream getImagen() {
        return imagen;
    }

    public void setImagen(InputStream imagen) {
        this.imagen = imagen;
    }

    public byte[] getImagenBytes() {
        return imagenBytes;
    }

    public void setImagenBytes(byte[] imagenBytes) {
        this.imagenBytes = imagenBytes;
    }


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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public Float getExtra() {
        return extra;
    }

    public void setExtra(Float extra) {
        this.extra = extra;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
