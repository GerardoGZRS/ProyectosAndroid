package mx.com.otss.barbershopapp.activities.cortesias;

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
import mx.com.otss.barbershopapp.activities.comisiones.MenuComisionesActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.ListadosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalEmpleadosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalFranquiciasActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalServiciosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.ReportesActivity;
import mx.com.otss.barbershopapp.request.cortesias.InsertarCortesiasRequest;
import mx.com.otss.barbershopapp.utils.BarberShop;
import mx.com.otss.barbershopapp.utils.Comunicador;

import static mx.com.otss.barbershopapp.utils.Red.verificaConexion;

public class AgregarCortesiasActivity extends AppCompatActivity {
    private String nombreFranquicia;
    private Button btnAgregarCortesia;
    private EditText txt1, txt2, txt3, txt4;
    private String nombreEmpleado;
    private Spinner spinner;
    private Menu menu;
    private FloatingActionButton ft;
    private MenuItem menuItem;
    private BottomNavigationView navigation;
    private Button btn1, btn2, btn3, btn4, btn5, btn6;
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
        setContentView(R.layout.activity_agregar_cortesias);
        txt1 = (EditText)findViewById(R.id.txtNombreCortesia);
        txt2 = (EditText)findViewById(R.id.txtTipoCortesia);
        txt3 = (EditText)findViewById(R.id.txtTotalCortesia);
        txt4 = (EditText)findViewById(R.id.txtLocalidad);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Selecciona");

        for (BarberShop b : Comunicador.getOBJ()){
            String nombre = b.getNombreEmpleado();
            arrayList.add(nombre);
        }
        ft = (FloatingActionButton)findViewById(R.id.fBtnAgregar);

        spinner = (Spinner)findViewById(R.id.spinnerEmpleados);
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
                String name = null;
                name = (String) parent.getItemAtPosition(position);
                setNombreEmpleado(name);
            }

            /**
             *
             * @param parent
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verificaConexion(getApplication())) {

                    Toast.makeText(getBaseContext(),
                            "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                            .show();

                }else {

                    final String nombreCortesia = txt1.getText().toString();
                    final String tipoCortesia = txt2.getText().toString();
                    final String totalCortesia = txt3.getText().toString();
                    final String localidad = txt4.getText().toString();
                    final String nombreEmpleado = getNombreEmpleado();

                    if (nombreCortesia.equals("")||tipoCortesia.equals("")||totalCortesia.equals("")||localidad.equals("")) {
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
                                        Toast.makeText(AgregarCortesiasActivity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(AgregarCortesiasActivity.this, "Error al ingresar el registro", Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(AgregarCortesiasActivity.this, "Error", Toast.LENGTH_SHORT).show();

                                }
                            }
                        };

                        InsertarCortesiasRequest insertarCortesiasRequest = new InsertarCortesiasRequest(nombreCortesia, tipoCortesia, totalCortesia, localidad, nombreEmpleado, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        queue.add(insertarCortesiasRequest);
                    }
            }}
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

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }
}
