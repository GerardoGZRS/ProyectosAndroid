package mx.com.otss.saec_registro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

import mx.com.otss.saec_registro.auxiliares.ListadoReporteActivity;

public class ReporteActivity extends AppCompatActivity {
    private PDFView pdfView;
    private File file;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        pdfView = (PDFView)findViewById(R.id.viewPdf);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            file = new File(bundle.getString("path", ""));
        }

        pdfView.fromFile(file)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .enableAntialiasing(true)
                .load();

    }
}


