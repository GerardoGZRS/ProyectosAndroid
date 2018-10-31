package mx.com.otss.barbershopapp.request.promociones;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ActualizarPromocionesRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://otss.com.mx/android/barbershop20/promociones/actualizarPromociones";
    private Map<String, String> params;

    public ActualizarPromocionesRequest(String idPromocion, String nombrePromocion, String descripcion, String fechaInicio, String fechaFin, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("idPromocion", idPromocion);
        params.put("nombrePromocion", nombrePromocion);
        params.put("descripcionPromocion", descripcion);
        params.put("fechaInicio", fechaInicio);
        params.put("fechaFin", fechaFin);
        Log.i("i", ""+params);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

