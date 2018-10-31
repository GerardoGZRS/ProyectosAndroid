package mx.com.otss.barbershopapp.request.franquicias;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InsertarFranquiciasRequest extends StringRequest {
    private static final String INSERTAR_REQUEST_URL = "http://otss.com.mx/android/barbershop20/franquicias/InsertarFranquicia.php";
    private Map<String, String> params;

    public InsertarFranquiciasRequest(String nombreFranquicia, String direccionFranquicia, String telefonoFranquicia, String ingresosGenerales, Response.Listener<String> listener) {
        super(Request.Method.POST, INSERTAR_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put("nombreFranquicia", nombreFranquicia);
        params.put("direccionFranquicia", direccionFranquicia);
        params.put("telefonoFranquicia", telefonoFranquicia);
        params.put("ingresosGenerales", ingresosGenerales);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}