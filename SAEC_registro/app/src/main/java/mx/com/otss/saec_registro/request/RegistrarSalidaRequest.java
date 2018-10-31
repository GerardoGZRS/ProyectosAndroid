package mx.com.otss.saec_registro.request;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistrarSalidaRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://azh.edu.mx/android/RegistrarSalida.php";
    private Map<String, String> params;

    public RegistrarSalidaRequest(String idusuario, Response.Listener<String> listener) throws SecurityException {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put("matricula", idusuario);
        Log.i("Info: ", "" + params);
        Log.i("url", ""+ LOGIN_REQUEST_URL);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
