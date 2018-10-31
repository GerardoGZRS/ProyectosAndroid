package mx.com.otss.barbershopapp.activities.comisiones;

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
import mx.com.otss.barbershopapp.activities.menu_inferior.ListadosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalEmpleadosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalFranquiciasActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalServiciosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.ReportesActivity;
import mx.com.otss.barbershopapp.adapters.comisiones.ConsultaEmpleadosComisionAdapter;
import mx.com.otss.barbershopapp.request.comisiones.ConsultaEmpleadoComisionRequest;
import mx.com.otss.barbershopapp.utils.BarberShop;
import mx.com.otss.barbershopapp.utils.Comunicador;
import mx.com.otss.barbershopapp.utils.RecyclerTouchListener;

import static mx.com.otss.barbershopapp.utils.Red.verificaConexion;

public class ConsultaEmpleadoComisionesActivity extends AppCompatActivity {
    private String nombreFranquicia;
    private ArrayList<BarberShop> listA ;
    private RecyclerView recyclerView;
    private ConsultaEmpleadosComisionAdapter mAdapter;
    private ArrayList<String> lista;
    private BarberShop al;
    private Button btnObtenerReporte;
    private View v;
    private ArrayList<String> listas;
    private Menu menu;
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
        setContentView(R.layout.activity_consulta_empleado_comisiones);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_consulta_empleado_comisiones);
        listA = new ArrayList<>();
        for(BarberShop i: Comunicador.getOBJ()){
            al = new BarberShop();
            al.setIdEmpleados(i.getIdEmpleados());
            al.setNombreEmpleado(i.getNombreEmpleado());
            al.setApellidoPaterno(i.getApellidoPaterno());
            al.setApellidoMaterno(i.getApellidoMaterno());
            listA.add(al);
        }

        mAdapter = new ConsultaEmpleadosComisionAdapter(listA);
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
                BarberShop movie = listA.get(position);
                Toast.makeText(getApplicationContext(), movie.getIdEmpleados() + " is selected!", Toast.LENGTH_SHORT).show();
                String idEmpleado = movie.getIdEmpleados();
                Log.i("", "IdEmpleado"+ idEmpleado);
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
                                        JSONArray array = jsonObject.getJSONArray("comisiones");
                                        String cadena = null;
                                        Comunicador obj = new Comunicador();
                                        BarberShop o = new BarberShop();
                                        for (int i = 0; i < array.length(); i++) {
                                            o.setIdComision(array.getJSONObject(i).getString("idComision"));
                                            o.setTipoComision(array.getJSONObject(i).getString("tipoComision"));
                                            o.setValorComision(array.getJSONObject(i).getString("valorComision"));
                                            o.setIdEmpleados(array.getJSONObject(i).getString("idEmpleado"));
                                            obj.setOBJ(o);
                                        }
                                    }
                                }

                                Intent intentComisionConsulta = new Intent(getApplicationContext(), ResultadoComisionEmpleadoActivity.class);
                                finish();
                                startActivity(intentComisionConsulta);

                            } catch (JSONException e) {
                                Log.e("Error", "Error" + e.getMessage());
                                e.printStackTrace();
                                Toast.makeText(ConsultaEmpleadoComisionesActivity.this, "No hay registros", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };

                    ConsultaEmpleadoComisionRequest consultaEmpleadoComisionRequest = new ConsultaEmpleadoComisionRequest(idEmpleado ,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(ConsultaEmpleadoComisionesActivity.this);
                    queue.add(consultaEmpleadoComisionRequest);
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
