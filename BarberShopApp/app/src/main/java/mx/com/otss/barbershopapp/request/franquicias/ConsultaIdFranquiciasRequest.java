package mx.com.otss.barbershopapp.request.franquicias;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ConsultaIdFranquiciasRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://otss.com.mx/android/barbershop20/franquicias/ConsultaFranquiciaId.php";
    private Map<String, String> params;

    public ConsultaIdFranquiciasRequest(String idFranquicia, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("idFranquicia", idFranquicia);
        Log.i("i", ""+params);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
