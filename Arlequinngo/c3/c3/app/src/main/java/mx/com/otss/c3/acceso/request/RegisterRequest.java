package mx.com.otss.c3.acceso.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL="http://otss.com.mx/app/android/Register.php";
    private Map<String,String> params;

    public RegisterRequest(String nombre_uss, String ap_uss, String am_uss,String usuario, String pass, String correo, String telefono, Response.Listener<String> listener){
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);

        params =new HashMap<>();
        params.put("nombre_uss",nombre_uss);
        params.put("ap_uss",ap_uss);
        params.put("am_uss",am_uss);
        params.put("usuario",usuario);
        params.put("pass",pass);
        params.put("correo",correo);
        params.put("telefono",telefono);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
