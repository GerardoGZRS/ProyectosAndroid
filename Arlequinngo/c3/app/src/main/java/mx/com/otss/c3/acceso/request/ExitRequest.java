package mx.com.otss.c3.acceso.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ExitRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://otss.com.mx/app/android/Cierra.php";
    private Map<String, String> params;

    public ExitRequest(String usuario, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("usuario", usuario);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
