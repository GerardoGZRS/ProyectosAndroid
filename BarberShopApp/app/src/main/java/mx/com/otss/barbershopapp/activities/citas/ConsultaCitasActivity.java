package mx.com.otss.barbershopapp.activities.citas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import mx.com.otss.barbershopapp.R;
import mx.com.otss.barbershopapp.activities.comisiones.MenuComisionesActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.ListadosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalEmpleadosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalFranquiciasActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalServiciosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.ReportesActivity;
import mx.com.otss.barbershopapp.adapters.citas.ConsultaCitasAdapter;
import mx.com.otss.barbershopapp.utils.BarberShop;
import mx.com.otss.barbershopapp.utils.Comunicador;
import mx.com.otss.barbershopapp.utils.RecyclerTouchListener;

public class ConsultaCitasActivity extends AppCompatActivity {
    private String nombreFranquicia;
    private ArrayList<BarberShop> listA;
    private RecyclerView recyclerView;
    private ConsultaCitasAdapter mAdapter;
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
        setContentView(R.layout.activity_consulta_citas);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_consulta_pago_cita);
        listA = new ArrayList<>();
        for(BarberShop i: Comunicador.getOBJ()){
            al = new BarberShop();
            al.setNombreCliente(i.getNombreCliente());
            al.setNombreEmpleado(i.getNombreEmpleado());
            al.setNombreServicio(i.getNombreServicio());
            al.setNombreFranquisia(i.getNombreFranquisia());
            al.setFechaInicio(i.getFechaInicio());
            listA.add(al);
        }


        mAdapter = new ConsultaCitasAdapter(listA);
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
                Toast.makeText(getApplicationContext(), movie.getNombreCliente() + " is selected!", Toast.LENGTH_SHORT).show();
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
    //menu

    /**
     *
     * @param menu
     * @return
     */
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
     * @return nombreFranquicia
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
