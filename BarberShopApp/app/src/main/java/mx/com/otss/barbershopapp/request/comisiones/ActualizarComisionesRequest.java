package mx.com.otss.barbershopapp.request.comisiones;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ActualizarComisionesRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://otss.com.mx/android/barbershop20/comisiones/actualizarComisiones.php";
    private Map<String, String> params;

    public ActualizarComisionesRequest(String id, String tipoComision, String valorComision, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put("idComision", id);
        params.put("tipoComision", tipoComision);
        params.put("valorComision", valorComision);

        Log.i("i", ""+params);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


