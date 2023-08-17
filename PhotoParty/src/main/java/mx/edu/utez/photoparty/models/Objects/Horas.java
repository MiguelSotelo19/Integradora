package mx.edu.utez.photoparty.models.Objects;

public class Horas {
    private String fecha, hrs_inicio, hrs_final;

    //Constructores
    public Horas(){
    }

    public Horas(String fecha, String hrs_inicio, String hrs_final) {
        this.fecha = fecha;
        this.hrs_inicio = hrs_inicio;
        this.hrs_final = hrs_final;
    }

    //Getters && Setters
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHrs_inicio() {
        return hrs_inicio;
    }

    public void setHrs_inicio(String hrs_inicio) {
        this.hrs_inicio = hrs_inicio;
    }

    public String getHrs_final() {
        return hrs_final;
    }

    public void setHrs_final(String hrs_final) {
        this.hrs_final = hrs_final;
    }
}
