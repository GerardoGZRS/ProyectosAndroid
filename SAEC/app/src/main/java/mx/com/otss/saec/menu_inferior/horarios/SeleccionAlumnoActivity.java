package mx.com.otss.saec.menu_inferior.horarios;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mx.com.otss.saec.R;
import mx.com.otss.saec.acceso.PortadaActivity;
import mx.com.otss.saec.adapdator.RecyclerTouchListener;
import mx.com.otss.saec.adapdator.RegistroDiarioAlumnoAdapter;
import mx.com.otss.saec.menu_inferior.AdeudosActivity;
import mx.com.otss.saec.menu_inferior.HorariosActivity;
import mx.com.otss.saec.menu_inferior.ListadosActivity;
import mx.com.otss.saec.menu_inferior.PrincipalActivity;
import mx.com.otss.saec.request.ListadoAdeudosRequest;
import mx.com.otss.saec.usuarios.Tabla_Alumnos;

import static mx.com.otss.saec.acceso.Red.verificaConexion;

public class SeleccionAlumnoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<String> lista;
    private RegistroDiarioAlumnoAdapter mAdapter;
    private ArrayList<Tabla_Alumnos> listA;
    private Tabla_Alumnos al= null;
    private EditText etSearchBox;
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
                                }
                            }
                        };

                        ListadoAdeudosRequest adeudosRequest = new ListadoAdeudosRequest(user, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(SeleccionAlumnoActivity.this);
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
        setContentView(R.layout.activity_seleccion_alumno);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        listA = new ArrayList<>();
        lista = getIntent().getStringArrayListExtra("data");
        int size = lista.size();
        JSONObject jsonObject = new JSONObject();
        String[] a = new String[lista.size()];
        a = lista.toArray(a);
        String x = null;

        for (String b : a) {
           Log.i("Array", "" + b);
            b = b.replaceAll("\n", "\\n");

            try {
                jsonObject = new JSONObject(b);
                al = new Tabla_Alumnos();
                String n = jsonObject.getString("id_usuario");
                String m  = jsonObject.getString("dia");
                String j = jsonObject.getString("hora_entrada");
                String i = jsonObject.getString("hora_salida");
                Log.i("Alumno", "" + n);
                al.setIdAlumno(n);
                al.setDia(m);
                al.setHora_entrada(j);
                al.setHora_salida(i);
                listA.add(al);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_registroxalumno);
        etSearchBox = (EditText) findViewById(R.id.etSearchBox_sel_alum);
        mAdapter = new RegistroDiarioAlumnoAdapter(listA);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        //filtrado
        etSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdapter.getFilter().filter(s.toString());

            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        /// row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Tabla_Alumnos movie = listA.get(position);
                Toast.makeText(getApplicationContext(), movie.getNombreAlumno() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

    }
}
