package mx.com.otss.saec_registro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mx.com.otss.saec_registro.auxiliares.ListadoReporteActivity;
import mx.com.otss.saec_registro.request.ReporteRegistrarRequest;
import mx.com.otss.saec_registro.request.RegistrarSalidaRequest;
import mx.com.otss.saec_registro.auxiliares.IntentIntegrator;
import mx.com.otss.saec_registro.auxiliares.IntentResult;

import static mx.com.otss.saec_registro.auxiliares.Red.verificaConexion;

public class RegistrarSalidaActivity extends AppCompatActivity  implements View.OnClickListener {

    private Button scanBoton;
    private TextView formatoTxt, matriculatxt,detalle;
    private String scanContent,user;
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
                    if (!verificaConexion(getApplication())) {
                        Toast.makeText(getBaseContext(),
                                "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                .show();
                    }else {
                        final ArrayList<String> arrayList = new ArrayList<>();
                        String user = "";
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject = null;
                                    Log.i("Recibo", "" + jsonArray);
                                    if (jsonArray == null) {
                                        Toast.makeText(RegistrarSalidaActivity.this, jsonArray.toString(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Log.i("recibo:", "" + jsonArray.toString());
                                        ArrayList<String> listdata = new ArrayList<String>();

                                        if (jsonArray != null) {
                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                listdata.add(jsonArray.getString(i));
                                            }
                                        }
                                        Intent intentPrincipal = new Intent(getApplicationContext(), ListadoReporteActivity.class);
                                        intentPrincipal.putStringArrayListExtra("data", (ArrayList<String>) listdata);
                                        finish();
                                        startActivity(intentPrincipal);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(RegistrarSalidaActivity.this, "No hay registros", Toast.LENGTH_SHORT).show();
                                }
                            }
                        };

                        ReporteRegistrarRequest reporteRegistrarRequest = new ReporteRegistrarRequest(user, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(RegistrarSalidaActivity.this);
                        queue.add(reporteRegistrarRequest);
                    }
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
        setContentView(R.layout.activity_registrar_salida);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        scanBoton = (Button)findViewById(R.id.btn_registrar_salida_scaneo);
        detalle = (TextView)findViewById(R.id.registrar_salida_detalles);
        matriculatxt = (TextView)findViewById(R.id.registrar_salida_matricula);
        scanBoton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_registrar_salida_scaneo){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
       if (!verificaConexion(getApplication())) {
            Toast.makeText(getBaseContext(),
                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                    .show();
        }else {
        if (scanningResult != null) {
            scanContent = scanningResult.getContents();
            detalle.setText(getText(R.string.detalles));
            matriculatxt.setText("Matricula: " + scanContent);
            user=scanContent;
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse=new JSONObject(response);
                        Log.i("Info", ""+ jsonResponse.toString());
                        boolean success=jsonResponse.getBoolean("success");
                        if (success==true) {
                            Toast.makeText(getApplication(),"registro actualizado correcto",Toast.LENGTH_SHORT).show();
                        }else {
                            AlertDialog.Builder builder=new AlertDialog.Builder(RegistrarSalidaActivity.this);
                            builder.setMessage("error en el registro")
                                    .setNegativeButton("Retry",null)
                                    .create().show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(RegistrarSalidaActivity.this, "Error " + e, Toast.LENGTH_SHORT).show();
                    }
                }
            };

            RegistrarSalidaRequest registrarEntradaRequest = new RegistrarSalidaRequest(user, responseListener);
            RequestQueue queue = Volley.newRequestQueue(RegistrarSalidaActivity.this);
            queue.add(registrarEntradaRequest);

        }else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No se ha recibido datos del scaneo!", Toast.LENGTH_SHORT);
            toast.show();
        }
        }
    }
}

