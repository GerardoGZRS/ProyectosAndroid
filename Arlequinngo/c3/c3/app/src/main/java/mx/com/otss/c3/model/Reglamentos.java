package mx.com.otss.c3.model;

public class Reglamentos {

    private int idReglamentoCompleto;
    private String nombre_reglamento;
    private String contenido;

    public Reglamentos(){}

    public Reglamentos(int idReglamentoCompleto, String inombre_reglamento, String contenido) {
        this.idReglamentoCompleto = idReglamentoCompleto;
        this.nombre_reglamento = nombre_reglamento;
        this.contenido = contenido;
    }

    public int getIdReglamentoCompleto() {
        return idReglamentoCompleto;
    }

    public void setIdReglamentoCompleto(int idReglamentoCompleto) { this.idReglamentoCompleto = idReglamentoCompleto; }

    public String getNombreReglamento() {
        return nombre_reglamento;
    }

    public void setNombreReglamento(String nombre_reglamento) { this.nombre_reglamento = nombre_reglamento; }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Contenido{" +
                "idReglamentoCompleto='" + idReglamentoCompleto + '\'' +
                ", nombre_reglamento='" + nombre_reglamento + '\'' +
                ", contenido='" + contenido + '\'' +
                '}';
    }
}
