package mx.com.otss.administrador.activities.maquileros;

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

import java.util.ArrayList;

import mx.com.otss.administrador.R;
import mx.com.otss.administrador.activities.MenuPrinicipalActivity;
import mx.com.otss.administrador.adapters.maquileros.ConsultaMaquilerosAdapter;
import mx.com.otss.administrador.utils.Administrador;
import mx.com.otss.administrador.utils.RecyclerTouchListener;

public class ConsultarMaquilerosActivity extends AppCompatActivity {

    private ArrayList<Administrador> listA;
    private RecyclerView recyclerView;
    private ConsultaMaquilerosAdapter mAdapter;
    private ArrayList<String> lista;
    private Administrador al;
    private Button btnObtenerReporte;
    private View v;
    private ArrayList<String> listas;

    private Menu menu;
    private MenuItem menuItem;
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_inferior_perfiles:
                    Intent intentM = new Intent(getApplicationContext(), MenuPrinicipalActivity.class);
                    startActivity(intentM);
                    return true;
                case R.id.menu_inferior_produccion:

                    return true;
                case R.id.menu_inferior_finanzas:
                    return true;
                case R.id.menu_inferior_reportes:
                    return true;
                case R.id.menu_inferior_ayuda:
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_maquileros);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_consulta_maquileros);

        listA = new ArrayList<>();
        lista = getIntent().getStringArrayListExtra("dat");

        mAdapter = new ConsultaMaquilerosAdapter(listA);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Administrador movie = listA.get(position);
                Intent intentActualizar = new Intent(getApplicationContext(), ActualizarMaquilerosActivity.class);
                startActivity(intentActualizar);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
    }
}



