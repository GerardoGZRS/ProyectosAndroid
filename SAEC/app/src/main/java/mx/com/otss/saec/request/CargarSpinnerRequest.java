package mx.com.otss.saec.request;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CargarSpinnerRequest extends StringRequest{
    private static final String CARGAR_SPINNER_REQUEST_URL = "http://azh.edu.mx/android/CargarSpinner.php";
    private Map<String, String> params;

    public CargarSpinnerRequest(String idusuario, Response.Listener<String> listener) throws SecurityException{
        super(Method.POST, CARGAR_SPINNER_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put("campus", idusuario);
        Log.i("Info: ", "" + params);
        Log.i("url", ""+ CARGAR_SPINNER_REQUEST_URL);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
