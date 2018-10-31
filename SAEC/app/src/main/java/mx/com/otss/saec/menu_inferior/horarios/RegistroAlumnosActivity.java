package mx.com.otss.saec.menu_inferior.horarios;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mx.com.otss.saec.R;
import mx.com.otss.saec.acceso.PortadaActivity;
import mx.com.otss.saec.menu_inferior.AdeudosActivity;
import mx.com.otss.saec.menu_inferior.HorariosActivity;
import mx.com.otss.saec.menu_inferior.ListadosActivity;
import mx.com.otss.saec.menu_inferior.PrincipalActivity;
import mx.com.otss.saec.request.ListadoAdeudosRequest;
import mx.com.otss.saec.request.RDAlumnoRequest;

import static mx.com.otss.saec.acceso.Red.verificaConexion;

public class RegistroAlumnosActivity extends AppCompatActivity {
    private ArrayList<String> lista;
    List<String> listas = null;
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_inferior_inicio:
                    Intent intent2=new Intent(getApplication(),PrincipalActivity.class);
                    finish();
                    startActivity(intent2);
                    return true;
                case R.id.menu_inferior_listados:
                    Intent intent1=new Intent(getApplication(),ListadosActivity.class);
                    finish();
                    startActivity(intent1);
                    return true;
                case R.id.menu_inferior_adeudos:
                    if (!verificaConexion(getApplication())) {
                        Toast.makeText(getBaseContext(),
                                "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                .show();
                    }else{
                        String user="";
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject = null;

                                    Log.i("recibo:", ""+ jsonArray.toString());
                                    ArrayList<String> listdata = new ArrayList<String>();

                                    if (jsonArray != null) {
                                        for (int i=0;i<jsonArray.length();i++){
                                            listdata.add(jsonArray.getString(i));
                                        }
                                    }

                                    Intent intentPrincipal=new Intent(getApplicationContext(), AdeudosActivity.class);
                                    intentPrincipal.putStringArrayListExtra("data", (ArrayList<String>) listdata);
                                    finish();
                                    startActivity(intentPrincipal);

                                    Log.i("Data receive: " , "" + listdata.toString());

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(RegistroAlumnosActivity.this, "No existen alumnos con adeudos", Toast.LENGTH_SHORT).show();

                                }
                            }
                        };

                        ListadoAdeudosRequest adeudosRequest = new ListadoAdeudosRequest(user, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(RegistroAlumnosActivity.this);
                        queue.add(adeudosRequest);
                    }
                    return true;
                case R.id.menu_inferior_registro:
                    Intent intent4=new Intent(getApplication(),HorariosActivity.class);
                    finish();
                    startActivity(intent4);
                    return true;
                case R.id.menu_inferior_salir:
                    Intent intent5=new Intent(getApplication(),PortadaActivity.class);
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
        setContentView(R.layout.activity_registro_alumnos);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (!verificaConexion(getApplication())) {
            Toast.makeText(getBaseContext(),
                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                    .show();
        }else{
            listas = new ArrayList<>();
            lista = getIntent().getStringArrayListExtra("data");
            int size = lista.size();
            JSONObject jsonObject = new JSONObject();
            String[] a = new String[lista.size()];
            a = lista.toArray(a);
            String x = null;
            String [] ab ;
            listas.add("Eliga una matricula");
            for (String b : a) {
                Log.i("Array", "" + b);
                b = b.replaceAll("\n", "\\n");

                try {
                    jsonObject = new JSONObject(b);
                    String n = jsonObject.getString("id_usuario");
                    listas.add(n);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            ab = new String[listas.size()];
            ab = listas.toArray(ab);
            Log.i("Tamaño",""+ab.length);  ;//end of for

            Spinner spinner = (Spinner) findViewById(R.id.spinner);
            String[] letra = ab;
            spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ab));
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id){
                    final String usuario = (String)  adapterView.getItemAtPosition(pos);
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = null;

                                Log.i("recibo:", ""+ jsonArray.toString());
                                ArrayList<String> listdata = new ArrayList<String>();

                                if (jsonArray != null) {
                                    for (int i=0;i<jsonArray.length();i++){
                                        listdata.add(jsonArray.getString(i));
                                    }
                                }

                                Intent intentPrincipal=new Intent(getApplicationContext(), SeleccionAlumnoActivity.class);
                                intentPrincipal.putStringArrayListExtra("data", (ArrayList<String>) listdata);
                                finish();
                                startActivity(intentPrincipal);

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplication(), "No hay registros", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };

                    RDAlumnoRequest loginRequest = new RDAlumnoRequest(usuario, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegistroAlumnosActivity.this);
                    queue.add(loginRequest);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent)
                {    }
            });
        }
    }
}
