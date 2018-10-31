package mx.com.otss.barbershopapp.request.servicios;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mx.com.otss.barbershopapp.utils.BarberShop;

public class ActualizarServiciosRequest extends StringRequest {
    private static final String ACTUALIZAR_REQUEST_URL = "http://otss.com.mx/android/barbershop20/servicios/ActualizarServicios.php";
    private Map<String, String> params;
    private String imagen = null;


    public ActualizarServiciosRequest(ArrayList<BarberShop> barberShops, Response.Listener<String> listener) {
        super(Method.POST, ACTUALIZAR_REQUEST_URL, listener, null);
        params = new HashMap<>();
        for (BarberShop b : barberShops){
            params.put("idServicio", b.getIdServicios());
            params.put("nombreServicio", b.getNombreServicio());
            params.put("precio", b.getPrecio());
            params.put("tiempoRequerido", b.getTiempoRequerido());
            params.put("idFranquicia", b.getIdFranquisia());
            params.put("imagen", "");
        }
    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
