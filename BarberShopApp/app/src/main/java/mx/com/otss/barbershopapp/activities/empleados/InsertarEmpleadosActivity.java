package mx.com.otss.barbershopapp.activities.empleados;

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
import mx.com.otss.barbershopapp.request.empleados.InsertarEmpleadosRequest;
import mx.com.otss.barbershopapp.utils.BarberShop;
import mx.com.otss.barbershopapp.utils.Comunicador;

import static mx.com.otss.barbershopapp.utils.Red.verificaConexion;

public class InsertarEmpleadosActivity extends AppCompatActivity {
    private String nombreFranquicia;
    private EditText nombreEmpleado, aPaterno, aMaterno, telefono, correo, tipoEmpleado, nombreUsuario, idFranquicia;
    private EditText password;
    private FloatingActionButton ft;
    private Button btnGuardar;
    private Spinner spinner, spinnerSucursales;
    private String tipoEmpleados;
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
        setContentView(R.layout.activity_insertar_empleados);
        Intent intent_receptor = getIntent();
        nombreFranquicia = intent_receptor.getStringExtra("nombreFranquicia");
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Selecciona..");
        arrayList.add("Sueldo");
        arrayList.add("Sueldo y Comisión");
        arrayList.add("Comisión");
        spinner = (Spinner) findViewById(R.id.spinnerEmpleados);
        spinnerSucursales = (Spinner)findViewById(R.id.spinnerFraquicias);
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

        ArrayList<String> arrayListSucursales = new ArrayList<>();
        arrayListSucursales.add("Selecciona");
        for (BarberShop b : Comunicador.getOBJ()){
            String nameSucursal = b.getNombreFranquisia();
            arrayListSucursales.add(nameSucursal);
        }

        spinnerSucursales.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayListSucursales));
        spinnerSucursales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             *
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String nameFranquicia = (String) parent.getItemAtPosition(position);
                setNombreFranquicia(nameFranquicia); }

            /**
             *
             * @param parent
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        nombreEmpleado = (EditText)findViewById(R.id.txtNombre);
        aPaterno = (EditText)findViewById(R.id.txtAPaterno);
        aMaterno = (EditText)findViewById(R.id.txtAMaterno);
        telefono = (EditText)findViewById(R.id.txtTelefono);
        correo = (EditText)findViewById(R.id.txtCorreo);
        tipoEmpleado = (EditText)findViewById(R.id.txtTipoUsuario);
        nombreUsuario = (EditText)findViewById(R.id.txtNombreUsuario);
        password = (EditText) findViewById(R.id.txtPassword1);
        ft = (FloatingActionButton)findViewById(R.id.fBtnAgregar);
        ft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nombreEmpleado.equals("")){
                    Toast.makeText(InsertarEmpleadosActivity.this, "Este esta vacio!!!!!", Toast.LENGTH_SHORT).show();
                } else{
                    if (!verificaConexion(getApplication())) {

                        Toast.makeText(getBaseContext(),
                                "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                .show();

                    }else {

                        final String nombre = nombreEmpleado.getText().toString();
                        final String apellidoPaterno = aPaterno.getText().toString();
                        final String apellidoMaterno = aMaterno.getText().toString();
                        final String telefonoEmpleado = telefono.getText().toString();
                        final String correoEmpleado = correo.getText().toString();
                        final String tipo = tipoEmpleados;
                        final String nameUser = nombreUsuario.getText().toString();
                        final String passwordUser = password.getText().toString();
                        final String nombreFranquicia = getNombreFranquicia();



                            // Response received from the server
                            Response.Listener<String> responseListener = new Response.Listener<String>() {
                                /**
                                 *
                                 * @param response
                                 */
                                @Override
                                public void onResponse(String response) {
                                    Log.i("error", "" + response);
                                    try {
                                        JSONObject jsonResponse = new JSONObject(response);

                                        boolean exito = jsonResponse.getBoolean("success");
                                        boolean success = exito;
                                        if (success == true) {
                                            Toast.makeText(InsertarEmpleadosActivity.this, "Registro exitoso..", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(InsertarEmpleadosActivity.this, "Error al insertar", Toast.LENGTH_SHORT).show();
                                        }


                                    } catch (JSONException e) {
                                        Toast.makeText(InsertarEmpleadosActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            };

                            InsertarEmpleadosRequest insertarEmpleadosRequest = new InsertarEmpleadosRequest(nombreFranquicia, nombre, apellidoPaterno, apellidoMaterno, telefonoEmpleado, correoEmpleado, tipo, nameUser, passwordUser, responseListener);
                            RequestQueue queue = Volley.newRequestQueue(InsertarEmpleadosActivity.this);
                            queue.add(insertarEmpleadosRequest);

                    }}
            }
        });
       ;

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

}
