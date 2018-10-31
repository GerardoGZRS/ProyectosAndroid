package mx.com.otss.barbershopapp.request.comisiones;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InsertarComisionesRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://otss.com.mx/android/barbershop20/comisiones/insertarComisiones.php";
    private Map<String, String> params;

    public InsertarComisionesRequest(String tipoComision, String valorComision, String nombreEmpleado, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put("tipoComision", tipoComision);
        params.put("valorComision", valorComision);
        params.put("nombreEmpleado", nombreEmpleado);

        Log.i("i", ""+params);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

