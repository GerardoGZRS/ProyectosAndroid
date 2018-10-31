package mx.com.otss.barbershopapp.request.empleados;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ActualizarEmpleadosRequest extends StringRequest {
    private static final String CONSULTA_REQUEST_URL = "http://otss.com.mx/android/barbershop20/empleados/ActualizarEmpleados.php";

    private Map<String, String> params;

    public ActualizarEmpleadosRequest(String idEmpleado, String nombre,String apellidoPaterno,String apellidoMaterno,String telefonoEmpleado,String correoEmpleado,String tipoEmpleado,String nameUser,String passwordUser, Response.Listener<String> listener) {
        super(Method.POST, CONSULTA_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("idEmpleado", idEmpleado);
        params.put("nombreEmpleado", nombre);
        params.put("apellidoPaterno", apellidoPaterno);
        params.put("apellidoMaterno", apellidoMaterno);
        params.put("telefonoEmpleado", telefonoEmpleado);
        params.put("correoEmpleado", correoEmpleado);
        params.put("tipoEmpleado", tipoEmpleado);
        params.put("nameuUser", nameUser);
        params.put("passwordUser", passwordUser);

        Log.i("i", ""+params);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
