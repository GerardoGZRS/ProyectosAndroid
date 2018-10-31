package mx.com.otss.c3.contenidos.menu_inferior;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import mx.com.otss.c3.R;
import mx.com.otss.c3.acceso.LoginnActivity;
import mx.com.otss.c3.acceso.Res_contrasenaActivity;
import mx.com.otss.c3.acceso.Res_usuarioActivity;
import mx.com.otss.c3.chat.ChatActivity;
import mx.com.otss.c3.contenidos.SalirActivity;

public class DictarActivity extends AppCompatActivity  implements TextToSpeech.OnInitListener  {
    private TextToSpeech tts;
    private ImageView btnplay,btnpause;
    private EditText seleccionado;
    private String textos;
    private String usuario;
    private Menu menu;
    private MenuItem menuItem;
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_inf_regresar:
                    Intent intent2=new Intent(getApplication(),LoginnActivity.class);
                    intent2.putExtra("usuario", usuario);
                    finish();
                    startActivity(intent2);
                    return true;
                case R.id.menu_inf_usuario:
                    Intent intent1=new Intent(getApplication(),UsuarioActivity.class);
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
        setContentView(R.layout.activity_dictar);

        Intent intent_receptor = getIntent();
        usuario = intent_receptor.getStringExtra("usuario");

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        seleccionado=(EditText)findViewById(R.id.txt_dictar_texto_leible3);
        tts=new TextToSpeech(this,this);
        btnplay=(ImageView)findViewById(R.id.btn_dictar_reproducir);
        btnpause=(ImageView)findViewById(R.id.btn_dictar_pausar);

        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hablarya();
            }
        });

        btnpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pararya();
            }
        });
    }


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

    private void hablarya() {
        textos=seleccionado.getText().toString();
        tts.speak(textos,TextToSpeech.QUEUE_FLUSH,null);
        Toast.makeText(getApplication(),"Recuerde subir el volumen de su dispositivo",Toast.LENGTH_LONG).show();
    }

    private void pararya(){
        tts.stop();
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        ;if(status==TextToSpeech.SUCCESS){
            int result=tts.setLanguage(Locale.getDefault());
            if(result==TextToSpeech.LANG_NOT_SUPPORTED||result==TextToSpeech.LANG_MISSING_DATA){
                Log.e("TTS","lenguaje no soportado");
            }
        }else{
            Log.e("TTS","Inicializacion de lectura fallo");
        }
    }
}
