package mx.com.otss.barbershopapp.request.promociones;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InsertarPromocionesRequest  extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://otss.com.mx/android/barbershop20/promociones/insertarPromociones.php";
    private Map<String, String> params;

    public InsertarPromocionesRequest(String nombrePromocion, String descripcionPromocion, String fechaInicio, String fechaFin, String nombreFranquicia, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("nombrePromocion", nombrePromocion);
        params.put("descripcionPromocion", descripcionPromocion);
        params.put("fechaInicio", fechaInicio);
        params.put("fechaFin", fechaFin);
        params.put("nombreFranquicia", nombreFranquicia);
        Log.i("i", ""+params);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
