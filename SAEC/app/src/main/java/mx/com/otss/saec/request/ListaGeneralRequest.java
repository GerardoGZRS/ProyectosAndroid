package mx.com.otss.saec.request;


import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ListaGeneralRequest extends StringRequest {
    private static final String LISTADOG_REQUEST_URL = "http://azh.edu.mx/android/ListadoGeneral.php";
    private Map<String, String> params;

    public ListaGeneralRequest(String idusuario, Response.Listener<String> listener) throws SecurityException {
        super(Method.POST, LISTADOG_REQUEST_URL, listener, null);
        params = new HashMap<>();

        Log.i("Info: ", "" + params);
        Log.i("url", "" + LISTADOG_REQUEST_URL);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}