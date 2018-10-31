package mx.com.otss.barbershopapp.request.cortesias;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ConsultaCortesiaEmpleadoRequest extends StringRequest {
    private static final String CONSULTA_REQUEST_URL = "http://otss.com.mx/android/barbershop20/cortesias/ConsultaCortesiaEmpleado.php";
    private Map<String, String> params;

    public ConsultaCortesiaEmpleadoRequest(String idEmpleado, Response.Listener<String> listener) {
        super(Request.Method.POST, CONSULTA_REQUEST_URL, listener, null);
        params = new HashMap<>();
        Log.i("i", ""+params);
        params.put("idEmpleado", idEmpleado);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}