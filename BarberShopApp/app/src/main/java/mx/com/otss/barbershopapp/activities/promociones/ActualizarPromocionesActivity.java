package mx.com.otss.barbershopapp.activities.promociones;

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
import android.widget.Spinner;
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
import mx.com.otss.barbershopapp.request.promociones.ActualizarPromocionesRequest;
import mx.com.otss.barbershopapp.utils.BarberShop;
import mx.com.otss.barbershopapp.utils.Comunicador;

import static mx.com.otss.barbershopapp.utils.Red.verificaConexion;

public class ActualizarPromocionesActivity extends AppCompatActivity {
    private String idPromocion;
    private String nombreFranquicia;
    private Button buttonActualizarPromociones;
    private EditText txt1, txt2, txt3, txt4;
    private Spinner spinnerEmpleado;
    private Menu menu;
    private MenuItem menuItem;
    private Spinner spinner;
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
        setContentView(R.layout.activity_actualizar_promociones);
        buttonActualizarPromociones = (Button)findViewById(R.id.btnActualizarPromociones);
        txt1 = (EditText) findViewById(R.id.txtNombrePromocion);
        txt2 = (EditText) findViewById(R.id.txtDescripcion);
        txt3 = (EditText) findViewById(R.id.txtFechaInicio);
        txt4 = (EditText) findViewById(R.id.txtFechaFin);

        for (BarberShop b: Comunicador.getOBJ()){
            setIdPromocion(b.getIdPromocion());
            txt1.setText(b.getNombrePromocion());
            txt2.setText(b.getDescripcion());
            txt3.setText(b.getFechaInicio());
            txt4.setText(b.getFechaFin());
        }

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(3);
        menuItem.setChecked(true);


        buttonActualizarPromociones.setOnClickListener(new View.OnClickListener() {
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
                } else {

                    final String idPromocion = getIdPromocion();
                    final String nombrePromocion = txt1.getText().toString();
                    final String descripcion = txt2.getText().toString();
                    final String fechaInicio = txt3.getText().toString();
                    final String fechaFin = txt4.getText().toString();
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
                                if (success == true) {
                                    Toast.makeText(getApplicationContext(), "Registro actualizado correctamente", Toast.LENGTH_SHORT).show();
                                    Intent intentConsulta = new Intent(getApplicationContext(), ConsultaPromocionActivity.class);
                                    intentConsulta.putExtra("nombreFranquicia", getNombreFranquicia());
                                    finish();
                                    startActivity(intentConsulta);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Error al actualizar el registro", Toast.LENGTH_SHORT).show();

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    ActualizarPromocionesRequest actualizarPromocionesRequest = new ActualizarPromocionesRequest(idPromocion, nombrePromocion, descripcion, fechaInicio, fechaFin, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    queue.add(actualizarPromocionesRequest);
                }
            }});

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

    /**
     *
     * @return
     */
    public String getIdPromocion() {
        return idPromocion;
    }

    /**
     *
     * @param idPromocion
     */
    public void setIdPromocion(String idPromocion) {
        this.idPromocion = idPromocion;
    }


}
