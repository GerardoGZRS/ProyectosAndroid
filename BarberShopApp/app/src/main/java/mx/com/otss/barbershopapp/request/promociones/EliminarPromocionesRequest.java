package mx.com.otss.barbershopapp.request.promociones;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class EliminarPromocionesRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://otss.com.mx/android/barbershop20/promociones/eliminarPromociones.php";
    private Map<String, String> params;

    public EliminarPromocionesRequest(String id_promocion, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_promocion", id_promocion);
        Log.i("i", ""+params);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


