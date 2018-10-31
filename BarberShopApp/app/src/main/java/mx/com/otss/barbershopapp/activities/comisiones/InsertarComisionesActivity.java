package mx.com.otss.barbershopapp.activities.comisiones;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mx.com.otss.barbershopapp.R;
import mx.com.otss.barbershopapp.activities.menu_inferior.ListadosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalEmpleadosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalFranquiciasActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalServiciosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.ReportesActivity;
import mx.com.otss.barbershopapp.request.comisiones.InsertarComisionesRequest;
import mx.com.otss.barbershopapp.utils.BarberShop;
import mx.com.otss.barbershopapp.utils.Comunicador;

import static mx.com.otss.barbershopapp.utils.Red.verificaConexion;

public class InsertarComisionesActivity extends AppCompatActivity {
    private FloatingActionButton ft;
    private String nombreFranquicia;
    private String tipoEmpleados;
    private String vc = null;
    private Button btnGuardar;
    private EditText txt1, txt2, txt3;
    private Menu menu;
    private MenuItem menuItem;
    private Spinner spinner, spinnerValorComision;
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
        setContentView(R.layout.activity_insertar_comisiones);
        Intent intent_receptor = getIntent();
        nombreFranquicia = intent_receptor.getStringExtra("nombreFranquicia");
        ArrayList<String> arrayList = new ArrayList<String>();

        arrayList.add("Selecciona..");

        for(BarberShop b: Comunicador.getOBJ()){
            String nombreEmpleado = b.getNombreEmpleado();
            arrayList.add(nombreEmpleado);
        }
        ft = (FloatingActionButton)findViewById(R.id.fBtnAgregar);

        spinner = (Spinner) findViewById(R.id.spinnerComisiones);
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
                tipoEmpleados = (String) parent.getItemAtPosition(position);}

            /**
             *
             * @param parent
             */
                @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        ArrayList<String> valorComision = new ArrayList<String>();
        valorComision.add("Selecciona");
        valorComision.add("0.10");
        valorComision.add("0.15");
        valorComision.add("0.20");
        valorComision.add("0.25");
        spinnerValorComision = (Spinner)findViewById(R.id.spinnerValorComision);
        spinnerValorComision.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valorComision));
        spinnerValorComision.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vc = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        txt1 = (EditText)findViewById(R.id.txtTipoComision);
        txt2 = (EditText)findViewById(R.id.txtValorComision);
        spinner = (Spinner)findViewById(R.id.spinnerComisiones);
        ft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt1.equals("")){
                    Toast.makeText(getApplicationContext(), "Este esta vacio!!!!!", Toast.LENGTH_SHORT).show();
                } else{
                    if (!verificaConexion(getApplication())) {

                        Toast.makeText(getBaseContext(),
                                "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                .show();

                    }else {

                        final String tipoComision = txt1.getText().toString();
                        final String valorComision = vc;
                        final String nombreEmpleados = tipoEmpleados;
                        if (tipoComision.equals("")||valorComision.equals("")||nombreEmpleados.equals("")) {
                            Toast.makeText(getApplicationContext(), "Ha dejado campos vacios", Toast.LENGTH_LONG).show();
                        }

                        else {

                            // Response received from the server
                            Response.Listener<String> responseListener = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.i("error", "" + response);
                                    try {
                                        JSONObject jsonResponse = new JSONObject(response);
                                        boolean exito = jsonResponse.getBoolean("success");
                                        boolean success = exito;
                                        if (success == true) {
                                            Toast.makeText(InsertarComisionesActivity.this, "Registro exitoso!!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(InsertarComisionesActivity.this, "Error!!", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(InsertarComisionesActivity.this, "Error en el servidor", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            };

                            InsertarComisionesRequest insertarComisionesRequest = new InsertarComisionesRequest(tipoComision, valorComision, nombreEmpleados, responseListener);
                            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                            queue.add(insertarComisionesRequest);
                        }
                    }
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


