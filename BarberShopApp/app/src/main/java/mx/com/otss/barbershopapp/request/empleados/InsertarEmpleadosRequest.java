package mx.com.otss.barbershopapp.request.empleados;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InsertarEmpleadosRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://otss.com.mx/android/barbershop20/empleados/InsertarEmpleados.php";
    private Map<String, String> params;

    public InsertarEmpleadosRequest(String nombreFranquicia, String nombre,String apellidoPaterno,String apellidoMaterno,String telefonoEmpleado,String correoEmpleado,String tipoEmpleado,String nameUser,String passwordUser, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("nombreEmpleado", nombre);
        params.put("apellidoPaterno", apellidoPaterno);
        params.put("apellidoMaterno", apellidoMaterno);
        params.put("telefonoEmpleado", telefonoEmpleado);
        params.put("correoEmpleado", correoEmpleado);
        params.put("tipoEmpleado", tipoEmpleado);
        params.put("nameUser", nameUser);
        params.put("passwordUser", passwordUser);
        params.put("nombreFranquicia", nombreFranquicia);
        Log.i("i", ""+params);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
