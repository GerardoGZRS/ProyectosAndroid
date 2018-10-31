package mx.com.otss.c3.model;

public class NivelDos{

    private int idNivelDos;
    private String nombreIndex;
    private String contenido;
    private String idNivelUno;

    public NivelDos(int idNivelDos, String nombreIndex, String contenido, String idNivelUno) {
        this.idNivelDos = idNivelDos;
        this.nombreIndex = nombreIndex;
        this.contenido = contenido;
        this.idNivelUno = idNivelUno;
    }

    public NivelDos(){}

    public int getIdNivelDos() {
        return idNivelDos;
    }

    public void setIdNivelDos(int idNivelDos) {
        this.idNivelDos = idNivelDos;
    }

    public String getNombreIndex() {
        return nombreIndex;
    }

    public void setNombreIndex(String nombreIndex) {
        this.nombreIndex = nombreIndex;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getIdNivelUno() {
        return idNivelUno;
    }

    public void setIdNivelUno(String idNivelUno) {
        this.idNivelUno = idNivelUno;
    }

    @Override
    public String toString() {
        return "NivelDosActivity{" +
                "idNivelDos='" + idNivelDos + '\'' +
                ", nombreIndex='" + nombreIndex + '\'' +
                ", contenidoActivity='" + contenido + '\'' +
                ", idNivelUno='" + idNivelUno + '\'' +
                '}';
    }
}