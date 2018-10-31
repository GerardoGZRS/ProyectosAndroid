package mx.com.otss.barbershopapp.activities.empleados;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import mx.com.otss.barbershopapp.R;
import mx.com.otss.barbershopapp.activities.comisiones.MenuComisionesActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.ListadosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalEmpleadosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalFranquiciasActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalServiciosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.ReportesActivity;
import mx.com.otss.barbershopapp.request.empleados.ActualizarEmpleadosRequest;
import mx.com.otss.barbershopapp.utils.BarberShop;
import mx.com.otss.barbershopapp.utils.Comunicador;

import static mx.com.otss.barbershopapp.utils.Red.verificaConexion;

public class ActualizarEmpleadoActivity extends AppCompatActivity {
    private String nombreFranquicia;
    private Menu menu;
    private EditText t1, t2, t3, t4, t5, t6, t7, t8;
    private Button btnActualizar;
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
        setContentView(R.layout.activity_actualizar_empleado);
        Intent intent_receptor = getIntent();
        setNombreFranquicia(nombreFranquicia);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        t1 = (EditText)findViewById(R.id.txtNombre);
        t2 = (EditText)findViewById(R.id.txtAPaterno);
        t3 = (EditText)findViewById(R.id.txtAMaterno);
        t4 = (EditText)findViewById(R.id.txtTelefono);
        t5 = (EditText)findViewById(R.id.txtCorreo);
        t6 = (EditText)findViewById(R.id.txtTipoUsuario);
        t7 = (EditText)findViewById(R.id.txtNombreUsuario);
        t8 = (EditText)findViewById(R.id.txtPassword);
        btnActualizar = (Button)findViewById(R.id.btnModificarEmpleado);
        String idEmpleado = null;
        for(BarberShop i: Comunicador.getOBJ()){
            idEmpleado = i.getIdEmpleados();
            t1.setText(i.getNombreEmpleado());
            t2.setText(i.getAppEmpleado());
            t3.setText(i.getApmEmpleado());
            t4.setText(i.getTelefonoEmpleado());
            t5.setText(i.getCorreoEmpleado());
            t6.setText(i.getTipoEmpleado());
            t7.setText(i.getNameUser());
            t8.setText(i.getPassword());
        }

        final String finalIdEmpleado = idEmpleado;
        btnActualizar.setOnClickListener(new View.OnClickListener() {
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

                    final String id = finalIdEmpleado;
                    final String nombre = t1.getText().toString();
                    final String apellidoPaterno  = t2.getText().toString();
                    final String apellidoMaterno = t3.getText().toString();
                    final String telefonoEmpleado = t4.getText().toString();
                    final String correoEmpleado = t5.getText().toString();
                    final String tipo = t6.getText().toString();
                    final String nameUser = t7.getText().toString();
                    final String passwordUser = t8.getText().toString();

                    // Response received from the server
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        /**
                         *
                         * @param response
                         */
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);


                                boolean exito = jsonResponse.getBoolean("success");
                                boolean success = exito;
                                if (success==true) {
                                    Toast.makeText(ActualizarEmpleadoActivity.this, "Registro actualizado correctamente", Toast.LENGTH_SHORT).show();
                                    Intent intentConsulta = new Intent(getApplicationContext(), ConsultarEmpleadoActivity.class);
                                    intentConsulta.putExtra("nombreFranquicia", getNombreFranquicia());
                                    finish();
                                    startActivity(intentConsulta);
                                } else {
                                    Toast.makeText(ActualizarEmpleadoActivity.this, "Error al actualizar el registro", Toast.LENGTH_SHORT).show();

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    ActualizarEmpleadosRequest actualizarEmpleadosRequest = new ActualizarEmpleadosRequest(id, nombre, apellidoPaterno, apellidoMaterno, telefonoEmpleado, correoEmpleado, tipo ,nameUser, passwordUser, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    queue.add(actualizarEmpleadosRequest);
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
