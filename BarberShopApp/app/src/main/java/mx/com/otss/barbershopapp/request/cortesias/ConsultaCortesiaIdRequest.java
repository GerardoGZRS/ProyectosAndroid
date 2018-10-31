package mx.com.otss.barbershopapp.request.cortesias;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ConsultaCortesiaIdRequest extends StringRequest {
    private static final String CONSULTA_REQUEST_URL = "http://otss.com.mx/android/barbershop20/cortesias/consultaCortesiaId.php";
    private Map<String, String> params;

    public ConsultaCortesiaIdRequest(String idCortesia, Response.Listener<String> listener) {
        super(Request.Method.POST, CONSULTA_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("idCortesia", idCortesia);
        Log.i("i", ""+params);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

