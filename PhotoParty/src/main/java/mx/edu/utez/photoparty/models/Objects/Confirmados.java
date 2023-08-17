package mx.edu.utez.photoparty.models.Objects;

public class Confirmados {
    private String id, fecha, inicio, hrs_final, direc, cabina;
    private int emp;

    //Constructores
    public Confirmados(){
    }

    public Confirmados(String id, String fecha, String inicio, String hrs_final, String direc, String cabina, int emp) {
        this.id = id;
        this.fecha = fecha;
        this.inicio = inicio;
        this.hrs_final = hrs_final;
        this.direc = direc;
        this.cabina = cabina;
        this.emp = emp;
    }

    //Getters && Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getHrs_final() {
        return hrs_final;
    }

    public void setHrs_final(String hrs_final) {
        this.hrs_final = hrs_final;
    }

    public String getDirec() {
        return direc;
    }

    public void setDirec(String direc) {
        this.direc = direc;
    }

    public String getCabina() {
        return cabina;
    }

    public void setCabina(String cabina) {
        this.cabina = cabina;
    }

    public int getEmp() {
        return emp;
    }

    public void setEmp(int emp) {
        this.emp = emp;
    }
}
