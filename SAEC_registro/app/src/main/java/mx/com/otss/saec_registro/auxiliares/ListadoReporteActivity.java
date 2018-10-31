package mx.com.otss.saec_registro.auxiliares;

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

import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.util.ArrayList;

import mx.com.otss.saec_registro.R;
import mx.com.otss.saec_registro.RegistrarActivity;
import mx.com.otss.saec_registro.RegistrarEntradaActivity;
import mx.com.otss.saec_registro.RegistrarSalidaActivity;
import mx.com.otss.saec_registro.SalirActivity;

public class ListadoReporteActivity extends AppCompatActivity {

    private ArrayList<AlumnoRegistro> listA ;
    private RecyclerView recyclerView;
    private ListadoReporteAdapter mAdapter;
    private ArrayList<String> lista;
    private AlumnoRegistro al;
    private Button btnObtenerReporte;
    private TemplatePdf pdf;
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
                case R.id.menu_inferior_inicio:
                    Intent intent1=new Intent(getApplication(),RegistrarActivity.class);
                    finish();
                    startActivity(intent1);
                    return true;
                case R.id.menu_inferior_entrada:
                    Intent intent2=new Intent(getApplication(),RegistrarEntradaActivity.class);
                    finish();
                    startActivity(intent2);
                    return true;
                case R.id.menu_inferior_salida:
                    Intent intent3=new Intent(getApplication(),RegistrarSalidaActivity.class);
                    finish();
                    startActivity(intent3);
                    return true;
                case R.id.menu_inferior_reporte:
                    Intent intent4=new Intent(getApplication(),ListadoReporteActivity.class);
                    finish();
                    startActivity(intent4);
                    return true;
                case R.id.menu_inferior_salir:
                    Intent intent5=new Intent(getApplication(),SalirActivity.class);
                    finish();
                    startActivity(intent5);
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
        setContentView(R.layout.activity_listado_reporte);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        lista = new ArrayList<>();
        btnObtenerReporte = (Button)findViewById(R.id.btnObtenerReporte);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        al = new AlumnoRegistro();
        listA = new ArrayList<>();
        lista = getIntent().getStringArrayListExtra("data");
        try {
                    pdf = new TemplatePdf(getApplicationContext());
                    pdf.openDocument();
                    pdf.contentido(lista);
                    pdf.closeDocument();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for( int i = 0 ; i < lista.size() ; i++ ){
            System.out.println( lista.get( 0 ) );
            String id = lista.get(0);
            al.setMatricula(id);
            String nombre = lista.get(1);
            al.setNombreAlummo(nombre);
            String dia = lista.get(2);
            al.setDia(dia);

            listA.add(al);
        }

        btnObtenerReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pdfView(v);
            }
        });

        mAdapter = new ListadoReporteAdapter(listA);
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
                AlumnoRegistro movie = listA.get(position);
                Toast.makeText(getApplicationContext(), movie.getNombreAlummo() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

    }

    /**
     *
     * @param v
     */
    private void pdfView(View v) {
        pdf.viewPdf();
    }
}
