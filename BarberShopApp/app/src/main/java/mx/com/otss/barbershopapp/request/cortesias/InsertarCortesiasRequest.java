package mx.com.otss.barbershopapp.request.cortesias;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InsertarCortesiasRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://otss.com.mx/android/barbershop20/cortesias/insertarCortesia.php";
    private Map<String, String> params;
    public InsertarCortesiasRequest(String nombreCortesia, String tipoCortesia, String totalCortesia, String localidad, String nombreEmpleado, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("nombreCortesia", nombreCortesia);
        params.put("tipoCortesia", tipoCortesia);
        params.put("totalCortesia",  totalCortesia);
        params.put("localidad", localidad);
        params.put("nombreEmpleado", nombreEmpleado);
        Log.i("i", ""+params);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
