package mx.com.otss.barbershopapp.request.pagos;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ConsultaPagoCitaRequest  extends StringRequest{
    private static final String CONSULTA_REQUEST_URL = "http://otss.com.mx/android/barbershop20/pagos/ConsultaPagoCita.php";
    private Map<String, String> params;

    public ConsultaPagoCitaRequest(String idCita, Response.Listener<String> listener) {
        super(Request.Method.POST, CONSULTA_REQUEST_URL, listener, null);
        params = new HashMap<>();
        Log.i("i", ""+params);
        params.put("idCita", idCita);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
