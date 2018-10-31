package mx.com.otss.barbershopapp.activities.clientes;

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
import mx.com.otss.barbershopapp.activities.menu_inferior.ListadosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalEmpleadosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalFranquiciasActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalServiciosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.ReportesActivity;
import mx.com.otss.barbershopapp.request.clientes.ConsultaClienteRequest;
import mx.com.otss.barbershopapp.request.franquicias.ConsultaFranquiciaRequest;
import mx.com.otss.barbershopapp.utils.BarberShop;
import mx.com.otss.barbershopapp.utils.Comunicador;

import static mx.com.otss.barbershopapp.utils.Red.verificaConexion;

public class PrincipalClientesActivity extends AppCompatActivity {
    private Spinner spinner;
    private String nombreFranquicia;
    private Button btn1, btn2, btn3;
    private Menu menu;
    private MenuItem menuItem;
    private BottomNavigationView navigation;
    /**
     * Accion Listener
     */
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
                    finish();
                    startActivity(intentPrincipalEmpleados);
                    return true;

                case R.id.menu_inferior_servicios:
                    Intent intentPrincipalServicios=new Intent(getApplication(),PrincipalServiciosActivity.class);
                    finish();
                    startActivity(intentPrincipalServicios);
                    return true;

                case R.id.menu_inferior_franquicias:
                    Intent intentPrincipalFranquicias = new Intent(getApplicationContext(), PrincipalFranquiciasActivity.class);
                    finish();
                    startActivity(intentPrincipalFranquicias);
                    return true;
                case R.id.menu_inferior_listados:
                    Intent intentPrincipalClientes = new Intent(getApplicationContext(), ListadosActivity.class);
                    finish();
                    startActivity(intentPrincipalClientes);
                    return true;
                case R.id.menu_inferior_reportes:
                    Intent intentReportes = new Intent(getApplicationContext(), ReportesActivity.class);
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
        setContentView(R.layout.activity_principal_clientes);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        final ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Selecciona...");
        arrayList.add("Consulta Clientes");
        arrayList.add("Consulta Clientes Franquicia");
        arrayList.add("Consulta Clientes Clasificación");
        spinner = (Spinner) findViewById(R.id.spinnerClientes);
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
                Comunicador.limpiar();
                if(nombreServicio.equals("Consulta Clientes")){
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
                                try{

                                    JSONArray jsonResponse = new JSONArray(response);
                                    Log.i("Info", "" + jsonResponse.length());
                                    ArrayList<String> list = new ArrayList<String>();
                                    String [] q = new String[jsonResponse.length()];
                                    for(int x = 0; x<q.length; x ++){
                                        String v =   q[x] = jsonResponse.optString(x);
                                        Log.i("info", "Mi lista"+v );
                                    }

                                    for(String s: q){
                                        JSONObject jsonObject = new JSONObject(s);
                                        for(int z=0; z<jsonObject.length();z++) {
                                            JSONArray array = jsonObject.getJSONArray("clientes");
                                            String cadena = null;
                                            Comunicador obj = new Comunicador();
                                            BarberShop o = new BarberShop();
                                            for (int i = 0; i < array.length(); i++) {

                                                o.setNombreCliente(array.getJSONObject(i).getString("nombreCliente"));
                                                o.setApellidoPaterno(array.getJSONObject(i).getString("aPaterno"));
                                                o.setApellidoMaterno(array.getJSONObject(i).getString("aMaterno"));
                                                obj.setOBJ(o);

                                            }

                                        }

                                    }

                                    Intent intentConsultar = new Intent(getApplicationContext(), ConsultaClientesActivity.class);
                                    finish();
                                    startActivity(intentConsultar);

                                } catch (JSONException e) {
                                    Log.e("Error", "Error" + e.getMessage());
                                    e.printStackTrace();
                                    Toast.makeText(PrincipalClientesActivity.this, "No hay registros", Toast.LENGTH_SHORT).show();
                                }
                            }
                        };

                        ConsultaClienteRequest consultaClienteRequest = new ConsultaClienteRequest(responseListener);
                        RequestQueue queue = Volley.newRequestQueue(PrincipalClientesActivity.this);
                        queue.add(consultaClienteRequest);

                    }
                } else if (nombreServicio.equals("Consulta Clientes Franquicia")){
                    Comunicador.limpiar();
                    if (!verificaConexion(getApplication())) {
                        Toast.makeText(getBaseContext(),
                                "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                .show();
                    }else {
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{

                                    JSONArray jsonResponse = new JSONArray(response);
                                    Log.i("Info", "" + jsonResponse.length());
                                    ArrayList<String> list = new ArrayList<String>();
                                    String [] q = new String[jsonResponse.length()];
                                    for(int x = 0; x<q.length; x ++){
                                        String v =   q[x] = jsonResponse.optString(x);
                                        Log.i("info", "Mi lista"+v );
                                    }

                                    for(String s: q){
                                        JSONObject jsonObject = new JSONObject(s);
                                        for(int z=0; z<jsonObject.length();z++) {
                                            JSONArray array = jsonObject.getJSONArray("franquicias");
                                            String cadena = null;
                                            Comunicador obj = new Comunicador();
                                            BarberShop o = new BarberShop();
                                            for (int i = 0; i < array.length(); i++) {

                                                o.setIdFranquisia(array.getJSONObject(i).getString("idFranquicia"));
                                                o.setNombreFranquisia(array.getJSONObject(i).getString("nombre"));
                                                o.setDireccionFranquisia(array.getJSONObject(i).getString("direccion"));
                                                o.setTelefonoFranquisia(array.getJSONObject(i).getString("telefono"));
                                                o.setIngresosGenerales(array.getJSONObject(i).getString("ingresos_generales"));

                                                obj.setOBJ(o);

                                            }

                                        }

                                    }

                                    Intent intentBuscarClienteFranquicia = new Intent(getApplicationContext(), BuscarClienteFranquiciaActivity.class);
                                    finish();
                                    startActivity(intentBuscarClienteFranquicia);

                                } catch (JSONException e) {
                                    Log.e("Error", "Error" + e.getMessage());
                                    e.printStackTrace();
                                    Toast.makeText(PrincipalClientesActivity.this, "No hay registros", Toast.LENGTH_SHORT).show();
                                }
                            }
                        };

                        ConsultaFranquiciaRequest consultaFranquiciaRequest = new ConsultaFranquiciaRequest(responseListener);
                        RequestQueue queue = Volley.newRequestQueue(PrincipalClientesActivity.this);
                        queue.add(consultaFranquiciaRequest);
                    }
                } else if(nombreServicio.equals("Consulta Clientes Clasificación")){
                    Intent intentClienteClasificacion = new Intent(getApplicationContext(), ClientesClasificacionActivity.class);
                    finish();
                    startActivity(intentClienteClasificacion);
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