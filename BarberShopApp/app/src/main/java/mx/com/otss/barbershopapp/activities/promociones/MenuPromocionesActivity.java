package mx.com.otss.barbershopapp.activities.promociones;

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
import mx.com.otss.barbershopapp.activities.menu_inferior.ListadosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalEmpleadosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalFranquiciasActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalServiciosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.ReportesActivity;
import mx.com.otss.barbershopapp.request.franquicias.ConsultaFranquiciaRequest;
import mx.com.otss.barbershopapp.request.promociones.ConsultaPromocionesRequest;
import mx.com.otss.barbershopapp.utils.BarberShop;
import mx.com.otss.barbershopapp.utils.Comunicador;

import static mx.com.otss.barbershopapp.utils.Red.verificaConexion;

public class MenuPromocionesActivity extends AppCompatActivity {
    private String nombreFranquicia;
    private Button btn1, btn2, btn3, btn4;
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
        setContentView(R.layout.activity_menu_promociones);
        final Intent intent_receptor = getIntent();
        nombreFranquicia = intent_receptor.getStringExtra("nombreFranquicia");
        setNombreFranquicia(nombreFranquicia);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        btn1 = (Button)findViewById(R.id.btnConsultaPromocion);
        btn2 = (Button)findViewById(R.id.btnConsultaPromocionFranquicia);
        btn3 = (Button)findViewById(R.id.btnIngresarPromciones);
        btn4 = (Button)findViewById(R.id.btnEliminarPromociones);

        btn1.setOnClickListener(new View.OnClickListener() {
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

                                        o.setIdPromocion(array.getJSONObject(i).getString("idPromociones"));
                                        o.setNombrePromocion(array.getJSONObject(i).getString("nombre"));
                                        o.setDescripcion(array.getJSONObject(i).getString("descripcion"));
                                        o.setFechaInicio(array.getJSONObject(i).getString("fechaInicio"));
                                        o.setFechaFin(array.getJSONObject(i).getString("fechaFin"));
                                        o.setIdFranquisia(array.getJSONObject(i).getString("idFranquicia"));

                                        obj.setOBJ(o);

                                    }

                                }

                            }

                            Intent intentConsultaPromocion = new Intent(getApplicationContext(), ConsultaPromocionActivity.class);
                            intentConsultaPromocion.putExtra("nombreFranquicia", getNombreFranquicia());
                            finish();
                            startActivity(intentConsultaPromocion);
                        } catch (JSONException e) {
                            Log.e("Error", "Error" + e.getMessage());
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "No hay registros", Toast.LENGTH_SHORT).show();
                        }
                    }
                };

                ConsultaPromocionesRequest consultaPromocionesRequest = new ConsultaPromocionesRequest(responseListener);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(consultaPromocionesRequest);

            }
        }
    });

    btn2.setOnClickListener(new View.OnClickListener() {
        /**
         *
         * @param v
         */
        @Override
        public void onClick(View v) {
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

                            Intent intentConsultaPromocionFranquicia = new Intent(getApplicationContext(), ConsultaPromocionFranquiciaActivity.class);
                            intentConsultaPromocionFranquicia.putExtra("nombreFranquicia", getNombreFranquicia());
                            finish();
                            startActivity(intentConsultaPromocionFranquicia);
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

                Intent intentConsultaPromocionFranquicia = new Intent(getApplicationContext(), ConsultaPromocionFranquiciaActivity.class);
                finish();
                startActivity(intentConsultaPromocionFranquicia);
            }
        }
    });

    btn3.setOnClickListener(new View.OnClickListener() {
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
                                        o.setIdFranquisia(array.getJSONObject(i).getString("idFranquicia"));
                                        o.setNombreFranquisia(array.getJSONObject(i).getString("nombre"));
                                        o.setDireccionFranquisia(array.getJSONObject(i).getString("direccion"));
                                        o.setTelefonoFranquisia(array.getJSONObject(i).getString("telefono"));
                                        o.setIngresosGenerales(array.getJSONObject(i).getString("ingresos_generales"));
                                        obj.setOBJ(o);

                                    }

                                }

                            }

                            Intent intentInsertar = new Intent(getApplicationContext(), AgregarPromocionesActivity.class);
                            finish();
                            startActivity(intentInsertar);

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

    btn4.setOnClickListener(new View.OnClickListener() {
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

                                        o.setIdPromocion(array.getJSONObject(i).getString("idPromociones"));
                                        o.setNombrePromocion(array.getJSONObject(i).getString("nombre"));
                                        o.setDescripcion(array.getJSONObject(i).getString("descripcion"));
                                        o.setFechaInicio(array.getJSONObject(i).getString("fechaInicio"));
                                        o.setFechaFin(array.getJSONObject(i).getString("fechaFin"));
                                        o.setIdFranquisia(array.getJSONObject(i).getString("idFranquicia"));

                                        obj.setOBJ(o);

                                    }
                                }
                            }

                            Intent intentEliminar  =  new Intent(getApplicationContext(), EliminarPromocionesActivity.class);
                            intentEliminar.putExtra("nombreFranquicia", getNombreFranquicia());
                            finish();
                            startActivity(intentEliminar);

                        } catch (JSONException e) {
                            Log.e("Error", "Error" + e.getMessage());
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "No hay registros", Toast.LENGTH_SHORT).show();
                        }
                    }
                };

                ConsultaPromocionesRequest consultaPromocionesRequest = new ConsultaPromocionesRequest(responseListener);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(consultaPromocionesRequest);
            }

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
