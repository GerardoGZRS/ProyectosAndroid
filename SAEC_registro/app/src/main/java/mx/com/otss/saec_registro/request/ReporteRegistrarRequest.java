package mx.com.otss.saec_registro.request;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ReporteRegistrarRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://192.168.1.90/proyecto/selects/ReporteSalida.php";
    private Map<String, String> params;

    public ReporteRegistrarRequest(String idusuario, Response.Listener<String> listener) throws SecurityException{
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
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

