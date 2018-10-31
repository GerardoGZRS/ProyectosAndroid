package mx.com.otss.barbershopapp.activities.cortesias;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import mx.com.otss.barbershopapp.adapters.cortesias.ConsultaEmpleadosCortesiaAdapter;
import mx.com.otss.barbershopapp.request.cortesias.ConsultaCortesiaEmpleadoRequest;
import mx.com.otss.barbershopapp.utils.BarberShop;
import mx.com.otss.barbershopapp.utils.Comunicador;
import mx.com.otss.barbershopapp.utils.RecyclerTouchListener;

import static mx.com.otss.barbershopapp.utils.Red.verificaConexion;

public class ConsultaCortesiaEmpleadoActivity extends AppCompatActivity {
    private String nombreFranquicia;
    private ArrayList<BarberShop> listA ;
    private RecyclerView recyclerView;
    private ConsultaEmpleadosCortesiaAdapter mAdapter;
    private ArrayList<String> lista;
    private BarberShop al;
    private Button btnObtenerReporte;
    private View v;
    private ArrayList<String> listas;
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
        setContentView(R.layout.activity_consulta_cortesia_empleado);
        Intent intent_receptor = getIntent();
        nombreFranquicia = intent_receptor.getStringExtra("nombreFranquicia");
        setNombreFranquicia(nombreFranquicia);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_consulta_empleado_cortesia);

        listA = new ArrayList<>();

        for(BarberShop i: Comunicador.getOBJ()){
            al = new BarberShop();

            al.setIdEmpleados(i.getIdEmpleados());
            al.setNombreEmpleado(i.getNombreEmpleado());
            al.setApellidoPaterno(i.getApellidoPaterno());
            al.setApellidoMaterno(i.getApellidoMaterno());

            listA.add(al);
        }

        mAdapter = new ConsultaEmpleadosCortesiaAdapter(listA);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            /**
             *
             * @param view
             * @param position
             */
            @Override
            public void onClick(View view, int position) {
                Comunicador.limpiar();
                BarberShop movie = listA.get(position);
                Toast.makeText(getApplicationContext(), movie.getIdEmpleados() + " is selected!", Toast.LENGTH_SHORT).show();
                String idEmpleado = movie.getIdEmpleados();
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
                                        JSONArray array = jsonObject.getJSONArray("cortesias");
                                        String cadena = null;
                                        Comunicador obj = new Comunicador();
                                        BarberShop o = new BarberShop();
                                        for (int i = 0; i < array.length(); i++) {

                                            o.setIdCortesia(array.getJSONObject(i).getString("idCortesia"));
                                            o.setNombreCortesia(array.getJSONObject(i).getString("nombreCortesia"));
                                            o.setTipoCortesia(array.getJSONObject(i).getString("tipoCortesia"));
                                            o.setTotalCortesia(array.getJSONObject(i).getString("totalCortesia"));
                                            o.setLocalidad(array.getJSONObject(i).getString("localidad"));
                                            o.setNombreEmpleado(array.getJSONObject(i).getString("nombreEmpleado"));
                                            obj.setOBJ(o);

                                        }

                                    }

                                }

                                Intent intentComisionConsulta = new Intent(getApplicationContext(), ResultadoCortesiaEmpleadoActivity.class);
                                intentComisionConsulta.putExtra("nombreFranquicia", getNombreFranquicia());
                                finish();
                                startActivity(intentComisionConsulta);

                            } catch (JSONException e) {
                                Log.e("Error", "Error" + e.getMessage());
                                e.printStackTrace();
                                Toast.makeText(ConsultaCortesiaEmpleadoActivity.this, "No hay registros", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };

                    ConsultaCortesiaEmpleadoRequest consultaCortesiaEmpleadoRequest = new ConsultaCortesiaEmpleadoRequest(idEmpleado ,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(ConsultaCortesiaEmpleadoActivity.this);
                    queue.add(consultaCortesiaEmpleadoRequest);

                }

            }

            /**
             *
             * @param view
             * @param position
             */
            @Override
            public void onLongClick(View view, int position) {
            }
        }));
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
