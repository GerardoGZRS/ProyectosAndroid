package mx.com.otss.c3.acceso.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PasswordRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://otss.com.mx/app/android/contrase%C3%B1a.php";
    private Map<String, String> params;

    public PasswordRequest(String usuario, String pasnueva, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("usuario", usuario);
        params.put("pasnueva", pasnueva);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
