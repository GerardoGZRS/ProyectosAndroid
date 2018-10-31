package mx.com.otss.saec.usuarios;

public class Tabla_Alumnos {

    private String idAlumno;
    private String nombreAlumno;
    private String aPaterno;
    private String aMaterno;
    private String Plantel;
    private String dia;
    private String hora_entrada;
    private String hora_salida;
    private String adeudo;


    public Tabla_Alumnos(String adeudo, String idAlumno, String nombreAlumno, String aPaterno, String aMaterno, String plantel, String dia, String hora_entrada, String hora_salida) {
        this.idAlumno = idAlumno;
        this.nombreAlumno = nombreAlumno;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        Plantel = plantel;
        this.dia = dia;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.adeudo = adeudo;
    }

    public String getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(String idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getaPaterno() {
        return aPaterno;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public String getPlantel() {
        return Plantel;
    }

    public void setPlantel(String plantel) {
        Plantel = plantel;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public String getAdeudo() { return adeudo; }

    public void setAdeudo(String adeudo) { this.adeudo = adeudo; }

    public Tabla_Alumnos(){}

    @Override
    public String toString() {
        return "Tabla_Alumnos{" +
                "idAlumno='" + idAlumno + '\'' +
                ", nombreAlumno='" + nombreAlumno + '\'' +
                ", aPaterno='" + aPaterno + '\'' +
                ", aMaterno='" + aMaterno + '\'' +
                ", Plantel='" + Plantel + '\'' +
                ", dia='" + dia + '\'' +
                ", hora_entrada='" + hora_entrada + '\'' +
                ", hora_salida='" + hora_salida + '\'' +
                ", adeudo='" + adeudo + '\'' +
                '}';
    }
}