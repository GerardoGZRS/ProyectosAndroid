package mx.com.otss.barbershopapp.request.clientes;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ConsultaClienteClasificacionRequest extends StringRequest {
    private static final String CONSULTA_REQUEST_URL = "http://otss.com.mx/android/barbershop20/clientes/ConsultaClientesClasificacion.php";
    private Map<String, String> params;

    public ConsultaClienteClasificacionRequest(String clasificacion, Response.Listener<String> listener) {
        super(Request.Method.POST, CONSULTA_REQUEST_URL, listener, null);
        params = new HashMap<>();
        Log.i("i", ""+params);
        params.put("clasificacion", clasificacion);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}