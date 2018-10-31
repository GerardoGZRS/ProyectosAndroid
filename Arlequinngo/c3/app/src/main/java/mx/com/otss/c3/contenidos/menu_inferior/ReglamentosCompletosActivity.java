package mx.com.otss.c3.contenidos.menu_inferior;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import mx.com.otss.c3.R;
import mx.com.otss.c3.acceso.Res_contrasenaActivity;
import mx.com.otss.c3.acceso.Res_usuarioActivity;
import mx.com.otss.c3.chat.ChatActivity;
import mx.com.otss.c3.contenidos.SalirActivity;
import mx.com.otss.c3.contenidos.ReproductorActivity;

import static mx.com.otss.c3.acceso.Red.verificaConexion;

public class ReglamentosCompletosActivity extends AppCompatActivity {
   private Button b1, b2,b3, b4, b5, b6, b7, b8 ,b9 ,b10, b11, b12, b13,b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25, b26, b27,
                    b28, b29, b30, b31, b32, b33, b34, b35, b36, b37, b38, b39, b40, b41, b42, b43, b44, b45, b46, b47;

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
                    Intent intent2=new Intent(getApplication(),ReglamentosCompletosActivity.class);
                    intent2.putExtra("usuario", usuario);
                    finish();
                    startActivity(intent2);
                    return true;
                case R.id.menu_inf_usuario:
                    Intent intent1=new Intent(getApplication(),NivelUnoActivity.class);
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
        setContentView(R.layout.activity_reglamento_completo);

        Intent intent_receptor = getIntent();
        usuario = intent_receptor.getStringExtra("usuario");

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        b1 = (Button)findViewById(R.id.btn_listado_1);
        b2 = (Button)findViewById(R.id.btn_listado_2);
        b3 = (Button)findViewById(R.id.btn_listado_3);
        b4 = (Button)findViewById(R.id.btn_listado_4);
        b5 = (Button)findViewById(R.id.btn_listado_5);
        b6 = (Button)findViewById(R.id.btn_listado_6);
        b7 = (Button)findViewById(R.id.btn_listado_7);
        b8 = (Button)findViewById(R.id.btn_listado_8);
        b9 = (Button)findViewById(R.id.btn_listado_9);
        b10 = (Button)findViewById(R.id.btn_listado_10);
        b11 = (Button)findViewById(R.id.btn_listado_11);
        b12 = (Button)findViewById(R.id.btn_listado_12);
        b13 = (Button)findViewById(R.id.btn_listado_13);
        b14 = (Button)findViewById(R.id.btn_listado_14);
        b15 = (Button)findViewById(R.id.btn_listado_15);
        b16 = (Button)findViewById(R.id.btn_listado_16);
        b17 = (Button)findViewById(R.id.btn_listado_17);
        b18 = (Button)findViewById(R.id.btn_listado_18);
        b19 = (Button)findViewById(R.id.btn_listado_19);
        b20 = (Button)findViewById(R.id.btn_listado_20);
        b21 = (Button)findViewById(R.id.btn_listado_21);
        b22 = (Button)findViewById(R.id.btn_listado_22);
        b23 = (Button)findViewById(R.id.btn_listado_23);
        b24 = (Button)findViewById(R.id.btn_listado_24);
        b25 = (Button)findViewById(R.id.btn_listado_25);
        b26 = (Button)findViewById(R.id.btn_listado_26);
        b27 = (Button)findViewById(R.id.btn_listado_27);
        b28 = (Button)findViewById(R.id.btn_listado_28);
        b29 = (Button)findViewById(R.id.btn_listado_29);
        b30 = (Button)findViewById(R.id.btn_listado_30);
        b31 = (Button)findViewById(R.id.btn_listado_31);
        b32 = (Button)findViewById(R.id.btn_listado_32);
        b33 = (Button)findViewById(R.id.btn_listado_33);
        b34 = (Button)findViewById(R.id.btn_listado_34);
        b35 = (Button)findViewById(R.id.btn_listado_35);
        b36 = (Button)findViewById(R.id.btn_listado_36);
        b37 = (Button)findViewById(R.id.btn_listado_37);
        b38 = (Button)findViewById(R.id.btn_listado_38);
        b39 = (Button)findViewById(R.id.btn_listado_39);
        b40 = (Button)findViewById(R.id.btn_listado_40);
        b41 = (Button)findViewById(R.id.btn_listado_41);
        b42 = (Button)findViewById(R.id.btn_listado_42);
        b43 = (Button)findViewById(R.id.btn_listado_43);
        b44 = (Button)findViewById(R.id.btn_listado_44);
        b45 = (Button)findViewById(R.id.btn_listado_45);
        b46 = (Button)findViewById(R.id.btn_listado_46);
        b47 = (Button)findViewById(R.id.btn_listado_47);

                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1XI0GGwyTYwN8_ocBnVshi_ov53VlbgNs";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });


                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1Hn1p8BL107VWZQMng2vBCSseVbwp6aqA";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);

                        }
                    }
                });


                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1cSBDoFm7QrfE8UAVDBkgt3UjieV95Y93";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1D56YtYOsZ4jtP5FIOjUylmbV0ERzEX-o";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1j2H-8beTpYBjrXNspVvhlA5pBvtzX3vs";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                        }
                });

                b6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=11QzoflpMcKhOhhZQraG9rpc9MWgYQtl9";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1h0uhGfRMvVsAWZ-ugGrB9ioCdAIxSXM2";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {if (!verificaConexion(getApplication())) {
                        Toast.makeText(getBaseContext(),
                                "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                .show();
                    }else {
                        Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                        String strName = "https://drive.google.com/uc?export=download&id=1gtLUX8Q6cDIxZD8BaJxJ9GDtHtl5Cnxb";
                        i.putExtra("STRING_I_NEED", strName);
                        i.putExtra("usuario", usuario);
                        finish();
                        startActivity(i);
                    }
                    }
                });

                b9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1gmBluuUhYM4Fobp8vBQDPNfZyl2Yjuz2";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1VIVJtxPkWN48fXaykfMWqWypUifbTDaV";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=16_NSiaxuBWkrDBknwSUJKRlM5FVWGvIn";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1zYgyL-guJjs87u5k5w4hgn6mEK3VFygO";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        } }
                });

                b13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1cSMy_RNXBDKMnZ1zwNqymjsWJEXMC-9N";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b14.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1LqtLxJgO2Epts8V0mS3wv46RrAm4puOi";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b15.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1_Lavk_QT7LKX-AV0V2XJX8e39q3YEwSr";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b16.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1FfGK2_1JrdAA-Sb8RTUZg0S7nFGC9HeD";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });


                b17.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1jORawiYI79PxQT6g0CFzVnc-ofRCcHCX";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b18.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1FkPIJMVdL-h7PbZ2w_M8XeUqL2kbSdp9";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b19.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1L8Ao-lvSfS2rQKQjT-kXpXoOaI6H1-36";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b20.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1MQ90bKc-5QuCQd70WP7j60pwlR17TINy";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b21.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1kQ9gw3MDA8m_O18kGZag_sBFEg8JEfLM";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                        }
                });

                b22.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1ja9-9wquTw0NS4Vn9HfrLkqGyO7MOQcv";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b23.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1By-u554wdf-oXiJeH1A-f6m1Hh3ev4tA";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b24.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1tZSWFBkEdPNDW-bEptF8_5xUNdvX9iCh";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b25.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1p4DX1BpPtVxCKgFkofrJ13Vx0k3lfYQN";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b26.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1ydrPH5DP7v5Fr5JyeTKOHrxaraQiFIh0";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b27.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1MTI629WhY9z4XEHYP_qjw44X0aXp4wc0";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b28.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1-yEishIeWwrBusZVZ8XJMBGnF4YIM4wd";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b29.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1glCEwGPqz-wiMf58-iI-LIHokVnv1ZF5";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b30.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1IDwqO0BTzAj1e192exNwldALZxhgq85G";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b31.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1q3dCxiAu8sYHbJi_A2Cn_PLs09P5ThDi";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b32.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1o7rtIyyYVBfeERFUSjCwZ_h8_L-4_DfX";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b33.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1Dq3Uc_pgk8B8CsqxfACF0sec6xNAJfyB";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b34.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1ZtjHmXLAadqzxH9LKRfR-4gyxnHXtz4S";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b35.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1QOa2J0gbt1SBdZmv3ZPmWwZvCaoXAabJ";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b36.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1nRtkO5eiCMMtZ6NPLP3CDQLn3DMI7gjo";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b37.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1Bxh0qfs1HrxgK8HGYH0YV1tfYMM2MBtb";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b38.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1pooHR5fTDNEoPNvv9npb91f2Hl5k5xQq";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b39.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1CSJWI9IkAtQff03VqcLUMHXd-_Ot5k4B";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b40.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1A-uJOmKIYNgvWyn7Ob4_JYgNDN5dTG8w";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b41.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1Yi1jB3W2GrX5NuqIa7qcpkenOV1yACUq";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b42.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=18xDyTEUm5CKIAO8zI-erYYtFUFua0q0r";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b43.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=11r_s6O_i5yekV0_iOHlrLMK2qRQBaR5A";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b44.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1wqZwosqXrKoAVuOpgL4Y-NLEYiypONTy";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b45.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1WYR7Xhj5AjgjtYmTB2yqJNXcMlcsUVPS";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b46.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1jgrUFOIwqYmHILe9RolPCeZvuFRaZqN_";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });

                b47.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!verificaConexion(getApplication())) {
                            Toast.makeText(getBaseContext(),
                                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                    .show();
                        }else {
                            Intent i = new Intent(getApplicationContext(), ReproductorActivity.class);
                            String strName = "https://drive.google.com/uc?export=download&id=1lmPX9aqJkZ_VLy6mujMzpgvkUNFUVMgY";
                            i.putExtra("STRING_I_NEED", strName);
                            i.putExtra("usuario", usuario);
                            finish();
                            startActivity(i);
                        }
                    }
                });
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
            Intent intentt=new Intent(getApplication(),ReglamentosCompletosActivity.class);
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
