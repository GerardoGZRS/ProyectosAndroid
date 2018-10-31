package mx.com.otss.barbershopapp.request.pagos;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InsertarPagosEmpleadosActivity extends StringRequest {
    private static final String INSERTAR_REQUEST_URL = "http://otss.com.mx/android/barbershop20/pagos/InsertarEmpleadosPagos.php";
    private Map<String, String> params;

    public InsertarPagosEmpleadosActivity(String pagoComision, String idEmpleado, Response.Listener<String> listener) {
        super(Request.Method.POST, INSERTAR_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("pagoComision", pagoComision);
        params.put("idEmpleado", idEmpleado);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
