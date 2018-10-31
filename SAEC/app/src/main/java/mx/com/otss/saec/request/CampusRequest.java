package mx.com.otss.saec.request;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CampusRequest extends StringRequest {
    private static final String CAMPUS_REQUEST_URL = "http://azh.edu.mx/android/Campus.php";
    private Map<String, String> params;

    public CampusRequest(String idusuario, Response.Listener<String> listener) throws SecurityException{
        super(Method.POST, CAMPUS_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put("campus", idusuario);
        Log.i("Info: ", "" + params);
        Log.i("url", ""+ CAMPUS_REQUEST_URL);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

