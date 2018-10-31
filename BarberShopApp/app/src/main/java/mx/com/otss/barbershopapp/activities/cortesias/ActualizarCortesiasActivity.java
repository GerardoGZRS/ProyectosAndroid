package mx.com.otss.barbershopapp.activities.cortesias;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import mx.com.otss.barbershopapp.request.cortesias.ActualizarCortesiaRequest;
import mx.com.otss.barbershopapp.utils.BarberShop;
import mx.com.otss.barbershopapp.utils.Comunicador;

import static mx.com.otss.barbershopapp.utils.Red.verificaConexion;

public class ActualizarCortesiasActivity extends AppCompatActivity {
    private String nombreFranquicia;
    private Button btnActualizar;
    private EditText txt1, txt2, txt3, txt4;
    private String idCortesia;
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
        setContentView(R.layout.activity_actualizar_cortesias);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        txt1 = (EditText)findViewById(R.id.txtNombreCortesia);
        txt2 = (EditText)findViewById(R.id.txtTipoCortesia);
        txt3 = (EditText)findViewById(R.id.txtTotalCortesia);
        txt4 = (EditText)findViewById(R.id.txtLocalidad);
        btnActualizar = (Button)findViewById(R.id.btnActualizarCortesia);
        for (BarberShop b: Comunicador.getOBJ()){
            setIdCortesia(b.getIdCortesia());
            txt1.setText(b.getNombreCortesia());
            txt2.setText(b.getTipoCortesia());
            txt3.setText(b.getTotalCortesia());
            txt4.setText(b.getLocalidad());
        }
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
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
                        final String idCortesia = getIdCortesia();
                        final String nombreCortesia = txt1.getText().toString();
                        final String tipoCortesia = txt2.getText().toString();
                        final String totalCortesia = txt3.getText().toString();
                        final String localidad = txt4.getText().toString();
                        if (nombreCortesia.equals("")||tipoCortesia.equals("")||totalCortesia.equals("")||localidad.equals("" +
                                "")) {
                            Toast.makeText(getApplicationContext(), "Ha dejado campos vacios", Toast.LENGTH_LONG).show();
                        } else {

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
                                            Toast.makeText(getApplicationContext(), "Registro actualizado!!", Toast.LENGTH_SHORT).show();
                                            Intent intentConsulta = new Intent(getApplicationContext(), ConsultaCortesiaActivity.class);
                                            intentConsulta.putExtra("nombreFranquicia", getNombreFranquicia());
                                            finish();
                                            startActivity(intentConsulta);
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Error!!", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(ActualizarCortesiasActivity.this, "Error al actualizar", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            };

                            ActualizarCortesiaRequest actualizarCortesiaRequest = new ActualizarCortesiaRequest(nombreCortesia, tipoCortesia, totalCortesia,localidad, idCortesia, responseListener);
                            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                            queue.add(actualizarCortesiaRequest);
                        }
                    }}
                        }
        });
    }

    /**
     *
     * @return
     */
    public String getIdCortesia() {
        return idCortesia;
    }

    /**
     *
     * @param idCortesia
     */
    public void setIdCortesia(String idCortesia) {
        this.idCortesia = idCortesia;
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
