package mx.com.otss.barbershopapp.activities.pagos;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import mx.com.otss.barbershopapp.R;
import mx.com.otss.barbershopapp.activities.menu_inferior.ListadosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalEmpleadosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalFranquiciasActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalServiciosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.ReportesActivity;
import mx.com.otss.barbershopapp.request.pagos.InsertarPagosEmpleadosActivity;
import mx.com.otss.barbershopapp.utils.BarberShop;
import mx.com.otss.barbershopapp.utils.Comunicador;

import static mx.com.otss.barbershopapp.utils.Red.verificaConexion;

public class AgregarPagosEmpleadoActivity extends AppCompatActivity {
    private Button btnIngresar;
    private EditText e1, e2, e3, e4, e5;
    private String id;
    private String nombreFranquicia;
    private FloatingActionButton ft;
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
        setContentView(R.layout.activity_modificar_pagos_empleado);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        ft = (FloatingActionButton)findViewById(R.id.fBtnAgregar);

        e1 = (EditText)findViewById(R.id.txtHorasTrabajadas);
        e2 = (EditText)findViewById(R.id.txtTotalPago);
        e3 = (EditText)findViewById(R.id.txtDiasTrabajados);
        e4 = (EditText)findViewById(R.id.txtTotalComision);
        e5 = (EditText)findViewById(R.id.txtPagoComision);

        for(BarberShop b : Comunicador.getOBJ()){
            e1.setText(b.getHorasTrabajadas());
            e2.setText(b.getTotalPagos());
            e3.setText(b.getDiasTrabajados());
            e4.setText(b.getTotalComision());
            e5.setText(b.getPagoComision());
            setId(b.getIdEmpleados());
        }

        ft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e1.equals("")){
                    Toast.makeText(getApplicationContext(), "Este esta vacio!!!!!", Toast.LENGTH_SHORT).show();
                } else{
                    if (!verificaConexion(getApplication())) {

                        Toast.makeText(getBaseContext(),
                                "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                .show();

                    }else {

                        final String horasTrabajadas = e1.getText().toString();
                        final String totalPago = e2.getText().toString();
                        final String diasTrabajados = e3.getText().toString();
                        final String totalComision = e4.getText().toString();
                        final String pagoComision = e5.getText().toString();
                        final String idEmpleados =  getId();


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
                                        Toast.makeText(getApplicationContext(), "Registro exitoso!!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Error!!", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(AgregarPagosEmpleadoActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        };

                        InsertarPagosEmpleadosActivity insertarPagosEmpleadosActivity = new InsertarPagosEmpleadosActivity(pagoComision, idEmpleados, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        queue.add(insertarPagosEmpleadosActivity);

                    }
                }
            }
        });


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreFranquicia() {
        return nombreFranquicia;
    }

    public void setNombreFranquicia(String nombreFranquicia) {
        this.nombreFranquicia = nombreFranquicia;
    }
}
