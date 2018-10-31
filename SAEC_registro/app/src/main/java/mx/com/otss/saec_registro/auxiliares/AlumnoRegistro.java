package mx.com.otss.saec_registro.auxiliares;

import java.util.ArrayList;

public class AlumnoRegistro {
    private String matricula;
    private String nombreAlummo;
    private String apAlumno;
    private String amAlumno;
    private String horaEntrada;
    private String horaSalida;
    private String dia;
    private ArrayList list;

    public AlumnoRegistro(){}

    public AlumnoRegistro(String matricula, String nombreAlummo, String apAlumno, String amAlumno, String horaEntrada, String horaSalida, String dia) {
        this.matricula = matricula;
        this.nombreAlummo = nombreAlummo;
        this.apAlumno = apAlumno;
        this.amAlumno = amAlumno;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.dia = dia;
    }

    /**
     *
     * @return matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     *
     * @param matricula
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     *
     * @return nombreAlumno
     */
    public String getNombreAlummo() {
        return nombreAlummo;
    }

    /**
     *
     * @param nombreAlummo
     */
    public void setNombreAlummo(String nombreAlummo) {
        this.nombreAlummo = nombreAlummo;
    }

    /**
     *
     * @return apAlumno
     */
    public String getApAlumno() {
        return apAlumno;
    }

    /**
     *
     * @param apAlumno
     */
    public void setApAlumno(String apAlumno) {
        this.apAlumno = apAlumno;
    }

    /**
     *
     * @return amAlumno
     */
    public String getAmAlumno() {
        return amAlumno;
    }

    /**
     *
     * @param amAlumno
     */
    public void setAmAlumno(String amAlumno) {
        this.amAlumno = amAlumno;
    }

    /**
     *
     * @return horaEntrada
     */
    public String getHoraEntrada() {
        return horaEntrada;
    }

    /**
     *
     * @param horaEntrada
     */
    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    /**
     *
     * @return horaSalida
     */
    public String getHoraSalida() {
        return horaSalida;
    }

    /**
     *
     * @param horaSalida
     */
    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    /**
     *
     * @return dia
     */
    public String getDia() {
        return dia;
    }

    /**
     *
     * @param dia
     */
    public void setDia(String dia) {
        this.dia = dia;
    }

    /**
     *
     * @return AlumnoRegistro
     */
    @Override
    public String toString() {
        return "AlumnoRegistro{" +
                "matricula='" + matricula + '\'' +
                ", nombreAlummo='" + nombreAlummo + '\'' +
                ", apAlumno='" + apAlumno + '\'' +
                ", amAlumno='" + amAlumno + '\'' +
                ", horaEntrada='" + horaEntrada + '\'' +
                ", horaSalida='" + horaSalida + '\'' +
                ", dia='" + dia + '\'' +
                '}';
    }

    /**
     *
     * @return list
     */
    public ArrayList getList() {
        return list;
    }

    /**
     *
     * @param list
     */
    public void setList(ArrayList list) {
        this.list = list;
    }
}
