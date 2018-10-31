package mx.com.otss.barbershopapp.request.empleados;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class EliminarEmpleadosRequest extends StringRequest {
    private static final String ELIMINAR_REQUEST_URL = "http://otss.com.mx/android/barbershop20/empleados/EliminarEmpleados.php";
    private Map<String, String> params;

    public EliminarEmpleadosRequest(String idEmpleado, Response.Listener<String> listener) {
        super(Method.POST, ELIMINAR_REQUEST_URL, listener, null);
        params = new HashMap<>();
       params.put("nombreEmpleado", idEmpleado);
        Log.i("i", ""+params);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

