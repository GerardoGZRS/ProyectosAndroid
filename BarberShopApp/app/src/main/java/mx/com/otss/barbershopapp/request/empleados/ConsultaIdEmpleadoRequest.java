package mx.com.otss.barbershopapp.request.empleados;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ConsultaIdEmpleadoRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://otss.com.mx/android/barbershop20/empleados/ConsultaIdEmpleado.php";
    private Map<String, String> params;

    public ConsultaIdEmpleadoRequest(String idEmpleado, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("idEmpleado", idEmpleado);
        Log.i("i", ""+params);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
