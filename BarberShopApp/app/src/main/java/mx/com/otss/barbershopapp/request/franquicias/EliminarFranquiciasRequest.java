package mx.com.otss.barbershopapp.request.franquicias;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class EliminarFranquiciasRequest extends StringRequest {
    private static final String ELIMINAR_REQUEST_URL = "http://otss.com.mx/android/barbershop20/franquicias/EliminarFranquicia.php";
    private Map<String, String> params;

    public EliminarFranquiciasRequest(String idFranquicia ,Response.Listener<String> listener) {
        super(Request.Method.POST, ELIMINAR_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("nombreFranquicia", idFranquicia);
        Log.i("mensaje", "" + idFranquicia);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}