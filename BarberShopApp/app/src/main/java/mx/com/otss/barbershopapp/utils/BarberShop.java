package mx.com.otss.barbershopapp.utils;

public class BarberShop {

    //extras
    private String namePromocion;
    private String duracionPromocion;
    private String nombreFranqui;
    private String notificacion;
    private String nombreCompletoCliente;

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    public String getNombreCompletoCliente() {
        return nombreCompletoCliente;
    }

    public void setNombreCompletoCliente(String nombreCompletoCliente) {
        this.nombreCompletoCliente = nombreCompletoCliente;
    }

    //Clientes
    private String idCliente;
    private String nombreCliente;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String telefono;
    private String direccion;
    private String idFranquisia;
    private String clasificacion;

    //Empleados
    private String idEmpleados;
    private String nombreEmpleado;
    private String appEmpleado;
    private String apmEmpleado;
    private String telefonoEmpleado;
    private String correoEmpleado;
    private String nameUser;
    private String password;
    private String tipoEmpleado;

    //Servicios
    private String idServicios;
    private String nombreServicio;
    private String precio;
    private String imagen;
    private String tiempoRequerido;
    private String[] imagenes;

    //Promociones

    private String idPromocion;
    private String nombrePromocion;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;

    //Ingresos

    private String idIngresos;
    private String formaPago;
    private String total;
    private String totalPago;

    //Franquisia

    private String idFranquisia1;
    private String nombreFranquisia;
    private String direccionFranquisia;
    private String telefonoFranquisia;
    private String ingresosGenerales;


    //Cortesias

    private String idCortesia;
    private String nombreCortesia;
    private String tipoCortesia;
    private String totalCortesia;
    private String localidad;


    //Comisiones
    private String idComision;
    private String tipoComision;
    private String valorComision;


    //citas
    private String idCita;
    private String fechaComision;
    private String horaComision;

    //horarios
    private String nombreEmpleadoHorario;
    private String fecha;
    private String horaEntrada;
    private String horaSalida;

    //pagos
    private String horasTrabajadas;
    private String totalPagos;
    private String diasTrabajados;
    private String totalComision;
    private String pagoComision;

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getIdFranquisia() {
        return idFranquisia;
    }

    public void setIdFranquisia(String idFranquisia) {
        this.idFranquisia = idFranquisia;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getIdEmpleados() {
        return idEmpleados;
    }

    public void setIdEmpleados(String idEmpleados) {
        this.idEmpleados = idEmpleados;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getAppEmpleado() {
        return appEmpleado;
    }

    public void setAppEmpleado(String appEmpleado) {
        this.appEmpleado = appEmpleado;
    }

    public String getApmEmpleado() {
        return apmEmpleado;
    }

    public void setApmEmpleado(String apmEmpleado) {
        this.apmEmpleado = apmEmpleado;
    }

    public String getTelefonoEmpleado() {
        return telefonoEmpleado;
    }

    public void setTelefonoEmpleado(String telefonoEmpleado) {
        this.telefonoEmpleado = telefonoEmpleado;
    }

    public String getCorreoEmpleado() {
        return correoEmpleado;
    }

    public void setCorreoEmpleado(String correoEmpleado) {
        this.correoEmpleado = correoEmpleado;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdServicios() {
        return idServicios;
    }

    public void setIdServicios(String idServicios) {
        this.idServicios = idServicios;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTiempoRequerido() {
        return tiempoRequerido;
    }

    public void setTiempoRequerido(String tiempoRequerido) {
        this.tiempoRequerido = tiempoRequerido;
    }

    public String getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(String idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getNombrePromocion() {
        return nombrePromocion;
    }

    public void setNombrePromocion(String nombrePromocion) {
        this.nombrePromocion = nombrePromocion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getIdIngresos() {
        return idIngresos;
    }

    public void setIdIngresos(String idIngresos) {
        this.idIngresos = idIngresos;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(String totalPago) {
        this.totalPago = totalPago;
    }

    public String getIdFranquisia1() {
        return idFranquisia1;
    }

    public void setIdFranquisia1(String idFranquisia1) {
        this.idFranquisia1 = idFranquisia1;
    }

    public String getNombreFranquisia() {
        return nombreFranquisia;
    }

    public void setNombreFranquisia(String nombreFranquisia) {
        this.nombreFranquisia = nombreFranquisia;
    }

    public String getDireccionFranquisia() {
        return direccionFranquisia;
    }

    public void setDireccionFranquisia(String direccionFranquisia) {
        this.direccionFranquisia = direccionFranquisia;
    }

    public String getTelefonoFranquisia() {
        return telefonoFranquisia;
    }

    public void setTelefonoFranquisia(String telefonoFranquisia) {
        this.telefonoFranquisia = telefonoFranquisia;
    }

    public String getIdCortesia() {
        return idCortesia;
    }

    public void setIdCortesia(String idCortesia) {
        this.idCortesia = idCortesia;
    }

    public String getNombreCortesia() {
        return nombreCortesia;
    }

    public void setNombreCortesia(String nombreCortesia) {
        this.nombreCortesia = nombreCortesia;
    }

    public String getTipoCortesia() {
        return tipoCortesia;
    }

    public void setTipoCortesia(String tipoCortesia) {
        this.tipoCortesia = tipoCortesia;
    }

    public String getTotalCortesia() {
        return totalCortesia;
    }

    public void setTotalCortesia(String totalCortesia) {
        this.totalCortesia = totalCortesia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getIdComision() {
        return idComision;
    }

    public void setIdComision(String idComision) {
        this.idComision = idComision;
    }

    public String getTipoComision() {
        return tipoComision;
    }

    public void setTipoComision(String tipoComision) {
        this.tipoComision = tipoComision;
    }

    public String getValorComision() {
        return valorComision;
    }

    public void setValorComision(String valorComision) {
        this.valorComision = valorComision;
    }

    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public String getFechaComision() {
        return fechaComision;
    }

    public void setFechaComision(String fechaComision) {
        this.fechaComision = fechaComision;
    }

    public String getHoraComision() {
        return horaComision;
    }

    public void setHoraComision(String horaComision) {
        this.horaComision = horaComision;
    }

    public String getIngresosGenerales() {
        return ingresosGenerales;
    }

    public void setIngresosGenerales(String ingresosGenerales) {
        this.ingresosGenerales = ingresosGenerales;
    }

    public BarberShop(){}

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getNamePromocion() {
        return namePromocion;
    }

    public void setNamePromocion(String namePromocion) {
        this.namePromocion = namePromocion;
    }

    public String getDuracionPromocion() {
        return duracionPromocion;
    }

    public void setDuracionPromocion(String duracionPromocion) {
        this.duracionPromocion = duracionPromocion;
    }

    public String getNombreFranqui() {
        return nombreFranqui;
    }

    public void setNombreFranqui(String nombreFranqui) {
        this.nombreFranqui = nombreFranqui;
    }

    public String[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(String[] imagenes) {
        this.imagenes = imagenes;
    }

    public BarberShop(String ingresosGenerales, String tipoEmpleado, String idCliente, String nombreCliente, String apellidoPaterno, String apellidoMaterno, String correo, String telefono, String direccion, String idFranquisia, String clasificacion, String idEmpleados, String nombreEmpleado, String appEmpleado, String apmEmpleado, String telefonoEmpleado, String correoEmpleado, String nameUser, String password, String idServicios, String nombreServicio, String precio, String imagen, String tiempoRequerido, String idPromocion, String nombrePromocion, String descripcion, String fechaInicio, String fechaFin, String idIngresos, String formaPago, String total, String totalPago, String idFranquisia1, String nombreFranquisia, String direccionFranquisia, String telefonoFranquisia, String idCortesia, String nombreCortesia, String tipoCortesia, String totalCortesia, String localidad, String idComision, String tipoComision, String valorComision, String idCita, String fechaComision, String horaComision) {
        this.ingresosGenerales = ingresosGenerales;
        this.tipoEmpleado = tipoEmpleado;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.idFranquisia = idFranquisia;
        this.clasificacion = clasificacion;
        this.idEmpleados = idEmpleados;
        this.nombreEmpleado = nombreEmpleado;
        this.appEmpleado = appEmpleado;
        this.apmEmpleado = apmEmpleado;
        this.telefonoEmpleado = telefonoEmpleado;
        this.correoEmpleado = correoEmpleado;
        this.nameUser = nameUser;
        this.password = password;
        this.idServicios = idServicios;
        this.nombreServicio = nombreServicio;
        this.precio = precio;
        this.imagen = imagen;
        this.tiempoRequerido = tiempoRequerido;
        this.idPromocion = idPromocion;
        this.nombrePromocion = nombrePromocion;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idIngresos = idIngresos;
        this.formaPago = formaPago;
        this.total = total;
        this.totalPago = totalPago;
        this.idFranquisia1 = idFranquisia1;
        this.nombreFranquisia = nombreFranquisia;
        this.direccionFranquisia = direccionFranquisia;
        this.telefonoFranquisia = telefonoFranquisia;
        this.idCortesia = idCortesia;
        this.nombreCortesia = nombreCortesia;
        this.tipoCortesia = tipoCortesia;
        this.totalCortesia = totalCortesia;
        this.localidad = localidad;
        this.idComision = idComision;
        this.tipoComision = tipoComision;
        this.valorComision = valorComision;
        this.idCita = idCita;
        this.fechaComision = fechaComision;
        this.horaComision = horaComision;
    }

    public String getNombreEmpleadoHorario() {
        return nombreEmpleadoHorario;
    }

    public void setNombreEmpleadoHorario(String nombreEmpleadoHorario) {
        this.nombreEmpleadoHorario = nombreEmpleadoHorario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(String horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public String getTotalPagos() {
        return totalPagos;
    }

    public void setTotalPagos(String totalPagos) {
        this.totalPagos = totalPagos;
    }

    public String getDiasTrabajados() {
        return diasTrabajados;
    }

    public void setDiasTrabajados(String diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }

    public String getTotalComision() {
        return totalComision;
    }

    public void setTotalComision(String totalComision) {
        this.totalComision = totalComision;
    }

    public String getPagoComision() {
        return pagoComision;
    }

    public void setPagoComision(String pagoComision) {
        this.pagoComision = pagoComision;
    }
}
