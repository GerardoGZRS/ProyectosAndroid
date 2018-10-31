package mx.com.otss.c3.contenidos.menu_inferior;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import mx.com.otss.c3.R;
import mx.com.otss.c3.acceso.Res_contrasenaActivity;
import mx.com.otss.c3.acceso.Res_usuarioActivity;
import mx.com.otss.c3.adaptores.NivelUnoAdapter;
import mx.com.otss.c3.adaptores.RecyclerTouchListener;
import mx.com.otss.c3.bd.LoadDataBase;
import mx.com.otss.c3.chat.ChatActivity;
import mx.com.otss.c3.contenidos.NivelDosActivity;
import mx.com.otss.c3.contenidos.SalirActivity;
import mx.com.otss.c3.model.NivelUno;

public class NivelUnoActivity extends AppCompatActivity {
    private LoadDataBase base;
    private RecyclerView recyclerView;
    private NivelUnoAdapter mAdapter;
    private String usuario;
    public final static String CLAVE_EXTRA_PASAR = "";
    private EditText etSearchBox;
    ArrayList<NivelUno> list = new ArrayList<>();
    private Menu menu;
    private MenuItem menuItem;
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_inf_regresar:
                    Intent intent2=new Intent(getApplication(),ReglamentosActivity.class);
                    intent2.putExtra("usuario", usuario);
                    finish();
                    startActivity(intent2);
                    return true;
                case R.id.menu_inf_usuario:
                    Intent intent1=new Intent(getApplication(),ReglamentosActivity.class);
                    intent1.putExtra("usuario", usuario);
                    finish();
                    startActivity(intent1);
                    return true;
                case R.id.menu_inf_dictar:
                    Intent intent3=new Intent(getApplication(),DictarActivity.class);
                    intent3.putExtra("usuario", usuario);
                    finish();
                    startActivity(intent3);
                    return true;
                case R.id.menu_inf_pdf:
                    Intent intent4=new Intent(getApplication(),EnviarPdfsActivity.class);
                    intent4.putExtra("usuario", usuario);
                    finish();
                    startActivity(intent4);
                    return true;
                case R.id.menu_inf_salir:
                    Intent intent5=new Intent(getApplication(),SalirActivity.class);
                    intent5.putExtra("usuario", usuario);
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
        setContentView(R.layout.activity_nivel_uno);
        etSearchBox = (EditText) findViewById(R.id.etSearchBox);

        Intent intent_receptor = getIntent();
        usuario = intent_receptor.getStringExtra("usuario");

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        base  = new LoadDataBase(this);
        list = base.loadNivelUno();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_nivelUno);
        mAdapter = new NivelUnoAdapter(list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

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

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                NivelUno nivelUno = list.get(position);
                Intent intentPrincipal=new Intent(getApplicationContext(), NivelDosActivity.class);
                int id = nivelUno.getIdNivelUno();
                intentPrincipal.putExtra(CLAVE_EXTRA_PASAR, id);
                finish();
                startActivity(intentPrincipal);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_superior, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_superior_usuario) {
            Intent intentt=new Intent(getApplication(),UsuarioActivity.class);
            intentt.putExtra("usuario", usuario);
            finish();
            startActivity(intentt);
        }
        if (id == R.id.menu_superior_chat) {
            Intent intentt=new Intent(getApplication(),ChatActivity.class);
            intentt.putExtra("usuario", usuario);
            finish();
            startActivity(intentt);
        }
        if (id == R.id.menu_superior_contraseña) {
            Intent intentt=new Intent(getApplication(),Res_contrasenaActivity.class);
            intentt.putExtra("usuario", usuario);
            finish();
            startActivity(intentt);
        }
        if (id == R.id.menu_superior_user) {
            Intent intentt=new Intent(getApplication(),Res_usuarioActivity.class);
            intentt.putExtra("usuario", usuario);
            finish();
            startActivity(intentt);
        }
        return super.onOptionsItemSelected(item);
    }
}
