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
import android.widget.Button;
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
import mx.com.otss.barbershopapp.activities.empleados.ConsultarEmpleadoActivity;
import mx.com.otss.barbershopapp.activities.empleados.EliminarEmpleadoActivity;
import mx.com.otss.barbershopapp.activities.empleados.InsertarEmpleadosActivity;
import mx.com.otss.barbershopapp.request.empleados.ConsultarEmpleadosRequest;
import mx.com.otss.barbershopapp.request.franquicias.ConsultaFranquiciaRequest;
import mx.com.otss.barbershopapp.utils.BarberShop;
import mx.com.otss.barbershopapp.utils.Comunicador;

import static mx.com.otss.barbershopapp.utils.Red.verificaConexion;

public class PrincipalEmpleadosActivity extends AppCompatActivity {
    private String nombreFranquicia;
    private Button btnAgregar, btnActualizar, btnEliminar, btnConsultar;
    private String usuario;
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
                    Intent intent1=new Intent(getApplication(),PrincipalEmpleadosActivity.class);
                    intent1.putExtra("nombreFranquicia", getUsuario());
                    finish();
                    startActivity(intent1);
                    return true;

                case R.id.menu_inferior_servicios:
                    Intent intent4=new Intent(getApplication(),PrincipalServiciosActivity.class);
                    intent4.putExtra("nombreFranquicia", getUsuario());
                    finish();
                    startActivity(intent4);
                    return true;

                case R.id.menu_inferior_franquicias:
                    Intent exIntent = new Intent(getApplicationContext(), PrincipalFranquiciasActivity.class);
                    exIntent.putExtra("nombreFranquicia", getUsuario());
                    finish();
                    startActivity(exIntent);
                    return true;
                case R.id.menu_inferior_listados:
                    Intent intentPrincipalClientes = new Intent(getApplicationContext(), ListadosActivity.class);
                    intentPrincipalClientes.putExtra("nombreFranquicia", getUsuario());
                    finish();
                    startActivity(intentPrincipalClientes);
                    return true;
                case R.id.menu_inferior_reportes:
                    Intent intentReportes = new Intent(getApplicationContext(), ReportesActivity.class);
                    intentReportes.putExtra("nombreFranquicia", getUsuario());
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
        setContentView(R.layout.activity_principal_empleados);
        btnAgregar = (Button)findViewById(R.id.btnIngresarEmpleados);
        Intent intent_receptor = getIntent();
        usuario = intent_receptor.getStringExtra("nombreFranquicia");
        setUsuario(usuario);
        btnConsultar = (Button)findViewById(R.id.btnConsultarEmpleados);
        btnEliminar = (Button)findViewById(R.id.btnEliminarEmpleados);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                Comunicador.limpiar();
                if (!verificaConexion(getApplication())) {
                    Toast.makeText(getBaseContext(),
                            "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                            .show();
                } else {
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

                                Intent intentAgregar = new Intent(getApplicationContext(), InsertarEmpleadosActivity.class);
                                intentAgregar.putExtra("nombreFranquicia", usuario);
                                finish();
                                startActivity(intentAgregar);


                            } catch (JSONException e) {
                                Log.e("Error", "Error" + e.getMessage());
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "No hay registros", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };

                    ConsultaFranquiciaRequest consultaFranquiciaRequest = new ConsultaFranquiciaRequest(responseListener);
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    queue.add(consultaFranquiciaRequest);
                }
                            }
        });



        btnConsultar.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
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
                                        JSONArray array = jsonObject.getJSONArray("empleados");
                                        String cadena = null;
                                        Comunicador obj = new Comunicador();
                                        BarberShop o = new BarberShop();
                                        for (int i = 0; i < array.length(); i++) {

                                            o.setIdEmpleados(array.getJSONObject(i).getString("idEmpleado"));
                                            o.setNombreEmpleado(array.getJSONObject(i).getString("nombre"));
                                            o.setAppEmpleado(array.getJSONObject(i).getString("apellidoPaterno"));
                                            o.setApmEmpleado(array.getJSONObject(i).getString("apellidoMaterno"));
                                            o.setTelefonoEmpleado(array.getJSONObject(i).getString("telefono"));
                                            o.setCorreoEmpleado(array.getJSONObject(i).getString("correo"));
                                            o.setTipoEmpleado(array.getJSONObject(i).getString("tipoEmpleado"));
                                            o.setNameUser(array.getJSONObject(i).getString("nameUser"));
                                            o.setPassword(array.getJSONObject(i).getString("password"));
                                            obj.setOBJ(o);
                                        }
                                    }
                                }

                                Intent intentConsultar = new Intent(getApplicationContext(), ConsultarEmpleadoActivity.class);
                                intentConsultar.putExtra("nombreFranquicia", getNombreFranquicia());
                                finish();
                                startActivity(intentConsultar);

                            } catch (JSONException e) {
                                Log.e("Error", "Error" + e.getMessage());
                                e.printStackTrace();
                                Toast.makeText(PrincipalEmpleadosActivity.this, "No hay registros", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };

                    ConsultarEmpleadosRequest consultarEmpleadosRequest = new ConsultarEmpleadosRequest(responseListener);
                    RequestQueue queue = Volley.newRequestQueue(PrincipalEmpleadosActivity.this);
                    queue.add(consultarEmpleadosRequest);
                }
                            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
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
                                        JSONArray array = jsonObject.getJSONArray("empleados");
                                        String cadena = null;
                                        Comunicador obj = new Comunicador();
                                        BarberShop o = new BarberShop();
                                        for (int i = 0; i < array.length(); i++) {
                                            o.setIdEmpleados(array.getJSONObject(i).getString("idEmpleado"));
                                            o.setNombreEmpleado(array.getJSONObject(i).getString("nombre"));
                                          //  o.setAppEmpleado(array.getJSONObject(i).getString("apellidoPaterno"));
                                            o.setApmEmpleado(array.getJSONObject(i).getString("apellidoMaterno"));
                                            o.setTelefonoEmpleado(array.getJSONObject(i).getString("telefono"));
                                            o.setCorreoEmpleado(array.getJSONObject(i).getString("correo"));
                                            o.setTipoEmpleado(array.getJSONObject(i).getString("tipoEmpleado"));
                                            o.setNameUser(array.getJSONObject(i).getString("nameUser"));
                                            o.setPassword(array.getJSONObject(i).getString("password"));
                                            obj.setOBJ(o);
                                        }
                                    }
                                }

                                Intent intentConsultar = new Intent(getApplicationContext(), EliminarEmpleadoActivity.class);
                                intentConsultar.putExtra("nombreFranquicia", getNombreFranquicia());
                                finish();
                                startActivity(intentConsultar);

                            } catch (JSONException e) {
                                Log.e("Error", "Error" + e.getMessage());
                                e.printStackTrace();
                                Toast.makeText(PrincipalEmpleadosActivity.this, "No hay registros", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };

                    ConsultarEmpleadosRequest consultarEmpleadosRequest = new ConsultarEmpleadosRequest(responseListener);
                    RequestQueue queue = Volley.newRequestQueue(PrincipalEmpleadosActivity.this);
                    queue.add(consultarEmpleadosRequest);
                    Intent intentEliminar = new Intent(getApplicationContext(), EliminarEmpleadoActivity.class);
                    finish();
                    startActivity(intentEliminar);
                }
            }
        });
    }

    /**
     *
     * @return
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     *
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
