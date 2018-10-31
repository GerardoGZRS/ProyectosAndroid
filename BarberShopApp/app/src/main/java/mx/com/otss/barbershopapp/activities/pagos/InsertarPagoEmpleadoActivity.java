package mx.com.otss.barbershopapp.activities.pagos;

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
import mx.com.otss.barbershopapp.activities.menu_inferior.ListadosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalEmpleadosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalFranquiciasActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalServiciosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.ReportesActivity;
import mx.com.otss.barbershopapp.request.pagos.ConsultaPagosEmpleadosRequest;
import mx.com.otss.barbershopapp.utils.BarberShop;
import mx.com.otss.barbershopapp.utils.Comunicador;

import static mx.com.otss.barbershopapp.utils.Red.verificaConexion;

public class InsertarPagoEmpleadoActivity extends AppCompatActivity {
    private Spinner spinner;
    private Button btnObtenerReporte;
    private String nombreFranquicia;
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
        setContentView(R.layout.activity_insertar_pago_empleado);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Selecciona");
        for(BarberShop b : Comunicador.getOBJ()){
            String nombreEmpleado =  b.getIdEmpleados() +  " " +  b.getNombreEmpleado();
            arrayList.add(nombreEmpleado);

        }
        spinner = (Spinner) findViewById(R.id.spinnerEmpleadosPagos);
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
                final String nombreEmpleado = (String) parent.getItemAtPosition(position);
                final   String idEmpleado  = nombreEmpleado.substring(0,2);
                Toast.makeText(InsertarPagoEmpleadoActivity.this, ""+idEmpleado, Toast.LENGTH_SHORT).show();

                Comunicador.limpiar();
                if (!verificaConexion(getApplication())) {
                    Toast.makeText(getBaseContext(),
                            "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    final ArrayList<BarberShop> arrayList = new ArrayList<BarberShop>();
                    final BarberShop obj = new BarberShop();
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        /**
                         *
                         * @param response
                         */
                        @Override
                        public void onResponse(String response) {
                            Log.i("", "Respuesta del servidor"+response);
                            try {
                                if(nombreEmpleado.equals("Selecciona")) {
                                    Toast.makeText(getApplicationContext(), "Error al eliminar!!!", Toast.LENGTH_SHORT).show();
                                } else{
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
                                    Intent intentAgregarPagos = new Intent(getApplicationContext(), AgregarPagosEmpleadoActivity.class);
                                    finish();
                                    startActivity(intentAgregarPagos);
                                }
                            } catch (JSONException e) {
                                Log.e("Error", "Error" + e.getMessage());
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "No hay registros", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };

                    ConsultaPagosEmpleadosRequest consultaPagosEmpleadosRequest = new ConsultaPagosEmpleadosRequest(idEmpleado,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    queue.add(consultaPagosEmpleadosRequest);
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

    public String getNombreFranquicia() {
        return nombreFranquicia;
    }

    public void setNombreFranquicia(String nombreFranquicia) {
        this.nombreFranquicia = nombreFranquicia;
    }
}
