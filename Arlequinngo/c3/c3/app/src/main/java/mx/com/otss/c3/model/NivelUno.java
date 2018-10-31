package mx.com.otss.c3.model;

public class NivelUno{

    private int idNivelUno;
    private String nombre;
    private String contenido;

    public NivelUno(){}

    public NivelUno(int idNivelUno, String nombre, String contenido) {
        this.idNivelUno = idNivelUno;
        this.nombre = nombre;
        this.contenido = contenido;
    }

    public int getIdNivelUno() {
        return idNivelUno;
    }

    public void setIdNivelUno(int idNivelUno) {
        this.idNivelUno = idNivelUno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "NivelDosActivity{" +
                "idNivelUno='" + idNivelUno + '\'' +
                ", nombre='" + nombre + '\'' +
                ", contenidoActivity='" + contenido + '\'' +
                '}';
    }
}
