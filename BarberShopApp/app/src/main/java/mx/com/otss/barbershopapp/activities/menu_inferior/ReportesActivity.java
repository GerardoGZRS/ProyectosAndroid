package mx.com.otss.barbershopapp.activities.menu_inferior;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mx.com.otss.barbershopapp.R;
import mx.com.otss.barbershopapp.activities.comisiones.MenuComisionesActivity;
import mx.com.otss.barbershopapp.activities.reportes.ReporteCitasActivity;
import mx.com.otss.barbershopapp.activities.reportes.ReporteControlTurnosActivity;
import mx.com.otss.barbershopapp.activities.reportes.ReporteHorariosActivity;
import mx.com.otss.barbershopapp.activities.reportes.ReportePagosEmpleadosActivity;
import mx.com.otss.barbershopapp.activities.reportes.ReportePromocionCorteActivity;
import mx.com.otss.barbershopapp.activities.reportes.ReportePromocionesAcivity;
import mx.com.otss.barbershopapp.activities.reportes.ReportsCortesiasActivity;
import mx.com.otss.barbershopapp.request.citas.ConsultaCitasRequest;
import mx.com.otss.barbershopapp.request.reportes.ControlCortesiasRequest;
import mx.com.otss.barbershopapp.request.reportes.ControlHorariosRequest;
import mx.com.otss.barbershopapp.request.reportes.ControlPromocionesRequest;
import mx.com.otss.barbershopapp.request.reportes.ControlTurnoRequest;
import mx.com.otss.barbershopapp.request.reportes.PromocionCorteRequest;
import mx.com.otss.barbershopapp.request.reportes.ReportePagosEmpleadoRequest;
import mx.com.otss.barbershopapp.utils.BarberShop;
import mx.com.otss.barbershopapp.utils.Comunicador;

import static mx.com.otss.barbershopapp.utils.Red.verificaConexion;

public class ReportesActivity extends AppCompatActivity {
    private Spinner spinner;
    private String nombreFranquicia;
    private Button btn1, btn2, btn3, btn4, btn5, btn6;
    private Menu menu;
    private MenuItem menuItem;
    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        /**
         *
         * @param item
         * @return
         */
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_inferior_empleados:
                    Intent intentPrincipalEmpleados=new Intent(getApplication(),PrincipalEmpleadosActivity.class);
                    intentPrincipalEmpleados.putExtra("nombreFranquicia", getNombreFranquicia());
                    finish();
                    startActivity(intentPrincipalEmpleados);
                    return true;

                case R.id.menu_inferior_servicios:
                    Intent intentPrincipalServicios=new Intent(getApplication(),PrincipalServiciosActivity.class);
                    intentPrincipalServicios.putExtra("nombreFranquicia", getNombreFranquicia());
                    finish();
                    startActivity(intentPrincipalServicios);
                    return true;

                case R.id.menu_inferior_franquicias:
                    Intent intentPrincipalFranquicias = new Intent(getApplicationContext(), PrincipalFranquiciasActivity.class);
                    intentPrincipalFranquicias.putExtra("nombreFranquicia", getNombreFranquicia());
                    finish();
                    startActivity(intentPrincipalFranquicias);
                    return true;
                case R.id.menu_inferior_listados:
                    Intent intentPrincipalClientes = new Intent(getApplicationContext(), ListadosActivity.class);
                    intentPrincipalClientes.putExtra("nombreFranquicia", getNombreFranquicia());
                    finish();
                    startActivity(intentPrincipalClientes);
                    return true;
                case R.id.menu_inferior_reportes:
                    Intent intentReportes = new Intent(getApplicationContext(), ReportesActivity.class);
                    intentReportes.putExtra("nombreFranquicia", getNombreFranquicia());
                    finish();
                    startActivity(intentReportes);
                    return true;
            }
            return false;
        }
    };

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);
        Intent intent_receptor = getIntent();
        nombreFranquicia = intent_receptor.getStringExtra("nombreFranquicia");
        setNombreFranquicia(nombreFranquicia);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(4);
        menuItem.setChecked(true);

        final ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Selecciona...");
        arrayList.add("Reporte Citas");
        arrayList.add("Reporte Turnos");
        arrayList.add("Reporte Cortesias");
        arrayList.add("Reporte Promociones");
        arrayList.add("Reporte Promoción Corte");
        arrayList.add("Reporte Horarios");
        arrayList.add("Reporte Pago Empleados");
        spinner = (Spinner) findViewById(R.id.spinnerReporte);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             *
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String nombreServicio = (String) parent.getItemAtPosition(position);
                if (nombreServicio.equals("Reporte Citas")){
                    if (!verificaConexion(getApplication())) {
                        Toast.makeText(getBaseContext(),
                                "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                .show();
                    }else {
                        final ArrayList<BarberShop> arrayList = new ArrayList<BarberShop>();
                        String user = "";
                        final BarberShop obj = new BarberShop();
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            /**
                             *
                             * @param response
                             */
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray jsonResponse = new JSONArray(response);
                                    Log.i("Info", "" + jsonResponse.length());
                                    ArrayList<String> list = new ArrayList<String>();
                                    String[] q = new String[jsonResponse.length()];
                                    for (int x = 0; x < q.length; x++) {
                                        String v = q[x] = jsonResponse.optString(x);
                                        Log.i("info", "Mi lista" + v);
                                    }
                                    for (String s : q) {
                                        JSONObject jsonObject = new JSONObject(s);
                                        for (int z = 0; z < jsonObject.length(); z++) {
                                            JSONArray array = jsonObject.getJSONArray("citas");
                                            String cadena = null;
                                            Comunicador obj = new Comunicador();
                                            BarberShop o = new BarberShop();
                                            for (int i = 0; i < array.length(); i++) {
                                                o.setNombreCliente(array.getJSONObject(i).getString("nombreCliente"));
                                                o.setNombreEmpleado(array.getJSONObject(i).getString("nombreEmpleado"));
                                                o.setNombreServicio(array.getJSONObject(i).getString("nombreServicio"));
                                                o.setNombreFranquisia(array.getJSONObject(i).getString("nombreFranquicia"));
                                                o.setFechaInicio(array.getJSONObject(i).getString("fecha"));
                                                obj.setOBJ(o);
                                            }
                                        }
                                    }

                                    Intent intentReporteCitas = new Intent(getApplicationContext(), ReporteCitasActivity.class);
                                    intentReporteCitas.putExtra("nombreFranquicia", getNombreFranquicia());
                                    finish();
                                    startActivity(intentReporteCitas);

                                } catch (JSONException e) {
                                    Log.e("Error", "Error" + e.getMessage());
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "No hay registros", Toast.LENGTH_SHORT).show();
                                }
                            }
                        };

                        ConsultaCitasRequest consultaCitasRequest = new ConsultaCitasRequest(responseListener);
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        queue.add(consultaCitasRequest);
                    }

                } else if(nombreServicio.equals("Reporte Turnos")){
                    Comunicador.limpiar();
                    if (!verificaConexion(getApplication())) {
                        Toast.makeText(getBaseContext(),
                                "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                .show();
                    }else {
                        final ArrayList<BarberShop> arrayList = new ArrayList<BarberShop>();
                        String user = "";
                        final BarberShop obj = new BarberShop();
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            /**
                             *
                             * @param response
                             */
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonResponse = new JSONArray(response);
                                    Log.i("Info", "" + jsonResponse.length());
                                    ArrayList<String> list = new ArrayList<String>();
                                    String[] q = new String[jsonResponse.length()];
                                    for (int x = 0; x < q.length; x++) {
                                        String v = q[x] = jsonResponse.optString(x);
                                        Log.i("info", "Mi lista" + v);
                                    }
                                    for (String s : q) {
                                        JSONObject jsonObject = new JSONObject(s);
                                        for (int z = 0; z < jsonObject.length(); z++) {
                                            JSONArray array = jsonObject.getJSONArray("turno");
                                            String cadena = null;
                                            Comunicador obj = new Comunicador();
                                            BarberShop o = new BarberShop();
                                            for (int i = 0; i < array.length(); i++) {
                                                o.setNombreEmpleado(array.getJSONObject(i).getString("nombreEmpleado"));
                                                o.setTiempoRequerido(array.getJSONObject(i).getString("tiempoEmpleado"));
                                                o.setNombreFranquisia(array.getJSONObject(i).getString("franquicia"));
                                                o.setNombreServicio(array.getJSONObject(i).getString("nombreServicio"));
                                                obj.setOBJ(o);
                                            }
                                        }
                                    }
                                    Intent intentReporteTurno = new Intent(getApplicationContext(), ReporteControlTurnosActivity.class);
                                    intentReporteTurno.putExtra("nombreFranquicia", getNombreFranquicia());
                                    finish();
                                    startActivity(intentReporteTurno);
                                } catch (JSONException e) {
                                    Log.e("Error", "Error" + e.getMessage());
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "No hay registros", Toast.LENGTH_SHORT).show();
                                }
                            }
                        };

                        ControlTurnoRequest controlTurnoRequest = new ControlTurnoRequest(responseListener);
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        queue.add(controlTurnoRequest);
                    }
                } else if(nombreServicio.equals("Reporte Cortesias")){
                    Comunicador.limpiar();
                    if (!verificaConexion(getApplication())) {
                        Toast.makeText(getBaseContext(),
                                "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                .show();
                    }else {
                        final ArrayList<BarberShop> arrayList = new ArrayList<BarberShop>();
                        String user = "";
                        final BarberShop obj = new BarberShop();
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            /**
                             *
                             * @param response
                             */
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray jsonResponse = new JSONArray(response);
                                    Log.i("Info", "" + jsonResponse.length());
                                    ArrayList<String> list = new ArrayList<String>();
                                    String[] q = new String[jsonResponse.length()];
                                    for (int x = 0; x < q.length; x++) {
                                        String v = q[x] = jsonResponse.optString(x);
                                        Log.i("info", "Mi lista" + v);
                                    }

                                    for (String s : q) {
                                        JSONObject jsonObject = new JSONObject(s);
                                        for (int z = 0; z < jsonObject.length(); z++) {
                                            JSONArray array = jsonObject.getJSONArray("cortesias");
                                            String cadena = null;
                                            Comunicador obj = new Comunicador();
                                            BarberShop o = new BarberShop();
                                            for (int i = 0; i < array.length(); i++) {

                                                o.setNombreCortesia(array.getJSONObject(i).getString("nombreCortesia"));
                                                o.setTipoCortesia(array.getJSONObject(i).getString("tipoCortesia"));
                                                o.setTotalCortesia(array.getJSONObject(i).getString("totalCortesias"));
                                                o.setNombreEmpleado(array.getJSONObject(i).getString("nombreEmpleado"));
                                                o.setDireccionFranquisia(array.getJSONObject(i).getString("direccionFranquicia"));

                                                obj.setOBJ(o);
                                            }
                                        }
                                    }

                                    Intent intentReporteCorteaias = new Intent(getApplicationContext(), ReportsCortesiasActivity.class);
                                    intentReporteCorteaias.putExtra("nombreFranquicia", getNombreFranquicia());
                                    finish();
                                    startActivity(intentReporteCorteaias);

                                } catch (JSONException e) {
                                    Log.e("Error", "Error" + e.getMessage());
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "No hay registros", Toast.LENGTH_SHORT).show();
                                }
                            }
                        };

                        ControlCortesiasRequest controlCortesiasRequest = new ControlCortesiasRequest(responseListener);
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        queue.add(controlCortesiasRequest);
                    }
                } else if (nombreServicio.equals("Reporte Promociones")){
                    Comunicador.limpiar();
                    if (!verificaConexion(getApplication())) {
                        Toast.makeText(getBaseContext(),
                                "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                .show();
                    }else {
                        final ArrayList<BarberShop> arrayList = new ArrayList<BarberShop>();
                        String user = "";
                        final BarberShop obj = new BarberShop();
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            /**
                             *
                             * @param response
                             */
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray jsonResponse = new JSONArray(response);
                                    Log.i("Info", "" + jsonResponse.length());
                                    ArrayList<String> list = new ArrayList<String>();
                                    String[] q = new String[jsonResponse.length()];
                                    for (int x = 0; x < q.length; x++) {
                                        String v = q[x] = jsonResponse.optString(x);
                                        Log.i("info", "Mi lista" + v);
                                    }

                                    for (String s : q) {
                                        JSONObject jsonObject = new JSONObject(s);
                                        for (int z = 0; z < jsonObject.length(); z++) {
                                            JSONArray array = jsonObject.getJSONArray("promociones");
                                            String cadena = null;
                                            Comunicador obj = new Comunicador();
                                            BarberShop o = new BarberShop();
                                            for (int i = 0; i < array.length(); i++) {

                                                o.setNombrePromocion(array.getJSONObject(i).getString("nombrePromocion"));
                                                o.setDuracionPromocion(array.getJSONObject(i).getString("duracionPromocion"));
                                                o.setNombreFranquisia(array.getJSONObject(i).getString("nombreFranquicia"));

                                                obj.setOBJ(o);
                                            }
                                        }
                                    }

                                    Intent intenReportePromociones = new Intent(getApplicationContext(), ReportePromocionesAcivity.class);
                                    intenReportePromociones.putExtra("nombreFranquicia", getNombreFranquicia());
                                    finish();
                                    startActivity(intenReportePromociones);
                                } catch (JSONException e) {
                                    Log.e("Error", "Error" + e.getMessage());
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "No hay registros", Toast.LENGTH_SHORT).show();
                                }
                            }
                        };

                        ControlPromocionesRequest controlPromocionesRequest = new ControlPromocionesRequest(responseListener);
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        queue.add(controlPromocionesRequest);
                    }
                } else if (nombreServicio.equals("Reporte Promoción Corte")){
                    Comunicador.limpiar();
                    if (!verificaConexion(getApplication())) {
                        Toast.makeText(getBaseContext(),
                                "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                .show();
                    }else {
                        final ArrayList<BarberShop> arrayList = new ArrayList<BarberShop>();
                        String user = "";
                        final BarberShop obj = new BarberShop();
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            /**
                             *
                             * @param response
                             */
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonResponse = new JSONArray(response);
                                    Log.i("Info", "" + jsonResponse.length());
                                    ArrayList<String> list = new ArrayList<String>();
                                    String[] q = new String[jsonResponse.length()];
                                    for (int x = 0; x < q.length; x++) {
                                        String v = q[x] = jsonResponse.optString(x);
                                        Log.i("info", "Mi lista" + v);
                                    }
                                    for (String s : q) {
                                        JSONObject jsonObject = new JSONObject(s);
                                        for (int z = 0; z < jsonObject.length(); z++) {
                                            JSONArray array = jsonObject.getJSONArray("promociones");
                                            String cadena = null;
                                            Comunicador obj = new Comunicador();
                                            BarberShop o = new BarberShop();
                                            for (int i = 0; i < array.length(); i++) {
                                                o.setNotificacion(array.getJSONObject(i).getString("notificacion"));
                                                o.setNombreCompletoCliente(array.getJSONObject(i).getString("clientes"));
                                                obj.setOBJ(o);
                                            }
                                        }
                                    }
                                    Intent intentReporteComisiones = new Intent(getApplicationContext(), ReportePromocionCorteActivity.class);
                                    intentReporteComisiones.putExtra("nombreFranquicia", getNombreFranquicia());
                                    finish();
                                    startActivity(intentReporteComisiones);
                                } catch (JSONException e) {
                                    Log.e("Error", "Error" + e.getMessage());
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "No hay registros", Toast.LENGTH_SHORT).show();
                                }
                            }
                        };

                        PromocionCorteRequest promocionCorteRequest = new PromocionCorteRequest(responseListener);
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        queue.add(promocionCorteRequest);
                    }
                } else if (nombreServicio.equals("Reporte Horarios")){
                    Comunicador.limpiar();
                    if (!verificaConexion(getApplication())) {
                        Toast.makeText(getBaseContext(),
                                "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                .show();
                    }else {
                        final ArrayList<BarberShop> arrayList = new ArrayList<BarberShop>();
                        String user = "";
                        final BarberShop obj = new BarberShop();
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            /**
                             *
                             * @param response
                             */
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray jsonResponse = new JSONArray(response);
                                    Log.i("Info", "" + jsonResponse.length());
                                    ArrayList<String> list = new ArrayList<String>();
                                    String[] q = new String[jsonResponse.length()];
                                    for (int x = 0; x < q.length; x++) {
                                        String v = q[x] = jsonResponse.optString(x);
                                        Log.i("info", "Mi lista" + v);
                                    }

                                    for (String s : q) {
                                        JSONObject jsonObject = new JSONObject(s);
                                        for (int z = 0; z < jsonObject.length(); z++) {
                                            JSONArray array = jsonObject.getJSONArray("horarios");
                                            String cadena = null;
                                            Comunicador obj = new Comunicador();
                                            BarberShop o = new BarberShop();
                                            for (int i = 0; i < array.length(); i++) {
                                                o.setNombreEmpleadoHorario(array.getJSONObject(i).getString("nombreEmpleado"));
                                                o.setFecha(array.getJSONObject(i).getString("fecha"));
                                                o.setHoraEntrada(array.getJSONObject(i).getString("horaEntrada"));
                                                o.setHoraSalida(array.getJSONObject(i).getString("horaSalida"));
                                                obj.setOBJ(o);
                                            }
                                        }
                                    }
                                    Intent intentReporteHorarios = new Intent(getApplicationContext(), ReporteHorariosActivity.class);
                                    intentReporteHorarios.putExtra("nombreFranquicia", getNombreFranquicia());
                                    finish();
                                    startActivity(intentReporteHorarios);
                                } catch (JSONException e) {
                                    Log.e("Error", "Error" + e.getMessage());
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "No hay registros", Toast.LENGTH_SHORT).show();
                                }
                            }
                        };

                        ControlHorariosRequest controlHorariosRequest = new ControlHorariosRequest(responseListener);
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        queue.add(controlHorariosRequest);
                    }
                } else if(nombreServicio.equals("Reporte Pago Empleados")){
                    Toast.makeText(ReportesActivity.this, "Reporte Pagos Empleados", Toast.LENGTH_SHORT).show();
                    Comunicador.limpiar();
                    if (!verificaConexion(getApplication())) {
                        Toast.makeText(getBaseContext(),
                                "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                .show();
                    }else {
                        final ArrayList<BarberShop> arrayList = new ArrayList<BarberShop>();
                        String user = "";
                        final BarberShop obj = new BarberShop();
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            /**
                             *
                             * @param response
                             */
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray jsonResponse = new JSONArray(response);
                                    Log.i("Info", "" + jsonResponse.length());
                                    ArrayList<String> list = new ArrayList<String>();
                                    String[] q = new String[jsonResponse.length()];
                                    for (int x = 0; x < q.length; x++) {
                                        String v = q[x] = jsonResponse.optString(x);
                                        Log.i("info", "Mi lista" + v);
                                    }

                                    for (String s : q) {
                                        JSONObject jsonObject = new JSONObject(s);
                                        for (int z = 0; z < jsonObject.length(); z++) {
                                            JSONArray array = jsonObject.getJSONArray("detallesPago");
                                            String cadena = null;
                                            Comunicador obj = new Comunicador();
                                            BarberShop o = new BarberShop();
                                            for (int i = 0; i < array.length(); i++) {
                                                o.setHorasTrabajadas(array.getJSONObject(i).getString("horasTrabajadas"));
                                                o.setTotalPagos(array.getJSONObject(i).getString("totalPago"));
                                                o.setDiasTrabajados(array.getJSONObject(i).getString("diasTrabajados"));
                                                o.setTotalComision(array.getJSONObject(i).getString("totalComision"));
                                                o.setPagoComision(array.getJSONObject(i).getString("pagoComision"));
                                                o.setIdEmpleados(array.getJSONObject(i).getString("idEmpleado"));
                                                obj.setOBJ(o);
                                            }
                                        }
                                    }
                                    Intent intentReporteHorarios = new Intent(getApplicationContext(), ReportePagosEmpleadosActivity.class);
                                    intentReporteHorarios.putExtra("nombreFranquicia", getNombreFranquicia());
                                    finish();
                                    startActivity(intentReporteHorarios);
                                } catch (JSONException e) {
                                    Log.e("Error", "Error" + e.getMessage());
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "No hay registros", Toast.LENGTH_SHORT).show();
                                }
                            }
                        };

                        ReportePagosEmpleadoRequest reportePagosEmpleadoRequest = new ReportePagosEmpleadoRequest(responseListener);
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        queue.add(reportePagosEmpleadoRequest);
                    }
                }
            }

            /**
             *
             * @param parent
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     *
     * @param menu
     * @return
     */
    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_superior, menu);
        return true;
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_superior_otros){
            Intent intentMenuComisiones = new Intent(getApplicationContext(), MenuComisionesActivity.class);
            startActivity(intentMenuComisiones);
        }
        if (id == R.id.menu_superior_salir) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     *
     * @return
     */
    public String getNombreFranquicia() {
        return nombreFranquicia;
    }

    /**
     *
     * @param nombreFranquicia
     */
    public void setNombreFranquicia(String nombreFranquicia) {
        this.nombreFranquicia = nombreFranquicia;
    }
}