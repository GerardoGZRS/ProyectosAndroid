package mx.com.otss.barbershopapp.activities.servicios;

import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.ImageView;
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
import mx.com.otss.barbershopapp.request.servicios.InsertarServiciosRequest;
import mx.com.otss.barbershopapp.utils.BarberShop;
import mx.com.otss.barbershopapp.utils.Comunicador;

import static mx.com.otss.barbershopapp.utils.Red.verificaConexion;

public class InsertarServiciosActivity extends AppCompatActivity {
    private String nombreFranquicia;
    private FloatingActionButton ft;
    private Button btnBuscar;
    private Button btnSubir;
    private ImageView imageView;
    private Bitmap bitmap;
    private Spinner spinnerFranquicia;
    private ArrayList<BarberShop> array;
    private int PICK_IMAGE_REQUEST = 1;
    private EditText txtNombreServicio, txtPrecio, txtImagen, txtTiempoRequerido, txtIdFranquicia;
    private Button btnGuardarServicio;
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
        setContentView(R.layout.activity_insertar_servicios);
        ft = (FloatingActionButton)findViewById(R.id.fBtnAgregar);
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Selecciona");

        for (BarberShop b : Comunicador.getOBJ()){
            String nombre = b.getNombreFranquisia();
            arrayList.add(nombre);
        }
        spinnerFranquicia = (Spinner)findViewById(R.id.textFranquicia);
        spinnerFranquicia.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList));
        spinnerFranquicia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                setNombreFranquicia(name);
            }

            /**
             *
             * @param parent
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        Intent intent_receptor = getIntent();
        setNombreFranquicia(nombreFranquicia);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        txtNombreServicio = (EditText)findViewById(R.id.txtNombreServicio);
        txtPrecio = (EditText)findViewById(R.id.txtPrecio);
        txtTiempoRequerido = (EditText)findViewById(R.id.txtTiempoRequerido);
        txtIdFranquicia = (EditText)findViewById(R.id.txtIdFranquicia);

        ft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verificaConexion(getApplication())) {
                    Toast.makeText(getBaseContext(),
                            "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                            .show();
                }else {
                    array = new ArrayList<>();
                    BarberShop barberShop = new BarberShop();
                    barberShop.setNombreServicio(String.valueOf(txtNombreServicio.getText()));
                    barberShop.setPrecio(String.valueOf(txtPrecio.getText().toString()));
                    barberShop.setTiempoRequerido(String.valueOf(txtTiempoRequerido.getText().toString()));
                    barberShop.setNombreFranquisia(getNombreFranquicia());
                    final String[] imagen = new String[1];
                    final String[] nombre = new String[1];
                  //  imagen[0] = getStringImagen(bitmap);
                    barberShop.setImagenes(new String[]{""});
                    array.add(barberShop);
                    // Response received from the server
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        /**
                         *
                         * @param response
                         */
                        @Override
                        public void onResponse(String response) {
                            Log.i("messaje", "recibo" + response);
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean exito = jsonResponse.getBoolean("success");
                                boolean success = exito;
                                if (success == true) {
                                    Toast.makeText(getApplicationContext(), "Registro insertado correctamente", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Error al insertar  el registro", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(InsertarServiciosActivity.this, "Error al insertar el registro", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };

                    InsertarServiciosRequest insertarServiciosRequest = new InsertarServiciosRequest(array, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    queue.add(insertarServiciosRequest);
                }
            }
        });

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
