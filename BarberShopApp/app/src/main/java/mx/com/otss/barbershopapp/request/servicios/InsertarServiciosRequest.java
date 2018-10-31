package mx.com.otss.barbershopapp.request.servicios;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mx.com.otss.barbershopapp.utils.BarberShop;

public class InsertarServiciosRequest extends StringRequest {

    private static final String INSERT_REQUEST_URL = "http://otss.com.mx/android/barbershop20/servicios/InsertarServicios.php";
    private Map<String, String> params;
    private String KEY_IMAGEN = "foto";
    private String KEY_NOMBRE = "nombre";
    private String imagen = null;

    public InsertarServiciosRequest(ArrayList<BarberShop> barberShops, Response.Listener<String> listener) {
        super(Method.POST, INSERT_REQUEST_URL, listener, null);
        params = new HashMap<>();
        for (BarberShop b : barberShops){
            params.put("nombreServicio", b.getNombreServicio());
            params.put("precio", b.getPrecio());
            params.put("tiempoRequerido", b.getTiempoRequerido());
            params.put("nombreFranquicia", b.getNombreFranquisia());
            for(String i : b.getImagenes()){
                imagen = i;
            }
            params.put("foto", imagen);
        }
        Log.i("Parametros", "" + params);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}