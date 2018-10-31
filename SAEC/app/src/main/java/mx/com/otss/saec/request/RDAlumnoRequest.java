package mx.com.otss.saec.request;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RDAlumnoRequest extends StringRequest {
    private static final String RDAALUMNO_REQUEST_URL = "http://192.168.1.90/proyecto/selects/HoraEntraAlumno.php";
    private Map<String, String> params;

    public RDAlumnoRequest(String idusuario, Response.Listener<String> listener) throws SecurityException{
        super(Method.POST, RDAALUMNO_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put("id_usuario", idusuario);
        Log.i("Info: ", "" + params);
        Log.i("url", ""+ RDAALUMNO_REQUEST_URL);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}