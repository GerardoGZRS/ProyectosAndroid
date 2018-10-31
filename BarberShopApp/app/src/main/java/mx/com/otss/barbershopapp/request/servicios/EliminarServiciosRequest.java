package mx.com.otss.barbershopapp.request.servicios;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class EliminarServiciosRequest extends StringRequest {
    private static final String ELIMINAR_REQUEST_URL = "http://otss.com.mx/android/barbershop20/servicios/EliminarServicios.php";
    private Map<String, String> params;

    public EliminarServiciosRequest(String idServicio ,Response.Listener<String> listener) {
        super(Request.Method.POST, ELIMINAR_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("nombreServicio", idServicio);
        Log.i("", "Se elimino el servicio "+ params);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
