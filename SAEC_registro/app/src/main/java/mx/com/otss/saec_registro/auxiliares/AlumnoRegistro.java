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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombreAlummo() {
        return nombreAlummo;
    }

    public void setNombreAlummo(String nombreAlummo) {
        this.nombreAlummo = nombreAlummo;
    }

    public String getApAlumno() {
        return apAlumno;
    }

    public void setApAlumno(String apAlumno) {
        this.apAlumno = apAlumno;
    }

    public String getAmAlumno() {
        return amAlumno;
    }

    public void setAmAlumno(String amAlumno) {
        this.amAlumno = amAlumno;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

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

    public ArrayList getList() {
        return list;
    }

    public void setList(ArrayList list) {
        this.list = list;
    }
}
