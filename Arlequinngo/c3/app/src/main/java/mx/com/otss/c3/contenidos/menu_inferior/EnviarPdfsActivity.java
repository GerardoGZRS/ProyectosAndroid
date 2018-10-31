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

import mx.com.otss.c3.R;
import mx.com.otss.c3.acceso.Res_contrasenaActivity;
import mx.com.otss.c3.acceso.Res_usuarioActivity;
import mx.com.otss.c3.chat.ChatActivity;
import mx.com.otss.c3.contenidos.MostrarpdfsActivity;
import mx.com.otss.c3.contenidos.SalirActivity;

public class EnviarPdfsActivity extends AppCompatActivity {
    private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22,b23,b24,b25,b26,b27,b28,b29,b30,b31,b32,b33,b34,b35,b36,b37,b38,b39,b40,b41,b42,b43,b44,b45,b46,b47;
    private String url;
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
        setContentView(R.layout.activity_pdf);

        Intent intent_receptor = getIntent();
        usuario = intent_receptor.getStringExtra("usuario");

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(3);
        menuItem.setChecked(true);


        b1=(Button)findViewById(R.id.btn_listadopdf_1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1Te4jjJzTirTGuvgUl6YQpLd6Sls-F-zp/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b2=(Button)findViewById(R.id.btn_listadopdf_2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/18gLPxTi1RIEy8zABr4crt3wpSIb_ITw6/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b3=(Button)findViewById(R.id.btn_listadopdf_3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1iBMqOo_hJS1SLbTh_oHdhjCkp2qiJLxh/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b4=(Button)findViewById(R.id.btn_listadopdf_4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/16tTc3-33VsyvUFfz2A85YOuJVxpfLPub/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b5=(Button)findViewById(R.id.btn_listadopdf_5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1ew00FQih83EdAugmnbCWGQWzLnOP6OFh/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b6=(Button)findViewById(R.id.btn_listadopdf_6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/14FKh-OA3HnqATJvZD0tYSIOH1CoYYUiR/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b7=(Button)findViewById(R.id.btn_listadopdf_7);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1bm3UOoPGQnp4oCuMjY2tPSruZvAEzdwh/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b8=(Button)findViewById(R.id.btn_listadopdf_8);
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1a2CbDeqv9wlqTkHiGum6VYUMvCaIksiC/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b9=(Button)findViewById(R.id.btn_listadopdf_9);
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1bVkumBvIam1ZQOoa4j1mHPL-xvfe5frf/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b10=(Button)findViewById(R.id.btn_listadopdf_10);
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/15sSbpI-aqm6WvkHNPU8UUevilUWwW5Fp/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b11=(Button)findViewById(R.id.btn_listadopdf_11);
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1edhKlbEdnzu_vBJbce4HaMlbKL1AUmCI/view?usp=sharing;
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b12=(Button)findViewById(R.id.btn_listadopdf_12);
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1riFFB91FKJSu5ocpVwwXhqWTD9Rub5q0/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b13=(Button)findViewById(R.id.btn_listadopdf_13);
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1yQcrEWe1dIroRIKGmBpHSy6yVohiG567/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b14=(Button)findViewById(R.id.btn_listadopdf_14);
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1hWLSnj77fLFO8N4z6Kd3shNtkQnFk9EA/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b15=(Button)findViewById(R.id.btn_listadopdf_15);
        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1FZB3ZfBOF8DBl42jGkmQrbhe-y2qXBMZ/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b16=(Button)findViewById(R.id.btn_listadopdf_16);
        b16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1chGAVs2H7ZWhqvClWPdg-n2e6HL5mQe3/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b17=(Button)findViewById(R.id.btn_listadopdf_17);
        b17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1Eo9BvpDKU6zXltIHpx1ZqZGDu3YCTiFI/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b18=(Button)findViewById(R.id.btn_listadopdf_18);
        b18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/19Vm5I-TJQuZLggOrswYBQaj7j6fQYqwf/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b19=(Button)findViewById(R.id.btn_listadopdf_19);
        b19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1ux_RQkTOZkmWZ8fJRtNS-AxHyxedwjvb/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b20=(Button)findViewById(R.id.btn_listadopdf_20);
        b20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1gaxdwgkNoG6exV1KRXVOFnIfCYP52Km_/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b21=(Button)findViewById(R.id.btn_listadopdf_21);
        b21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1XxJzlpH6-ojr8wq8Mtq42iF4yzcQwk6x/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b22=(Button)findViewById(R.id.btn_listadopdf_22);
        b22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/195wIKb5A0hzE6_W00oqXy98Me2qEiIls/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b23=(Button)findViewById(R.id.btn_listadopdf_23);
        b23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/13KlTqWsffcARUfA5HBXnsaIUDhWluzBI/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b24=(Button)findViewById(R.id.btn_listadopdf_24);
        b24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1R8yBYTKrzzrkZw8Z4ribIEaYRxP_njuD/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b25=(Button)findViewById(R.id.btn_listadopdf_25);
        b25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1EecLwHdkBhSelab_Ga7bYgqgasTbp6Xn/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b26=(Button)findViewById(R.id.btn_listadopdf_26);
        b26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1a9NzJuEPXAu5KBByVxYRLD74cseNWY3P/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b27=(Button)findViewById(R.id.btn_listadopdf_27);
        b27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1S5VUOEswjbNK7zftV_bcw5XhOBshgHif/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b28=(Button)findViewById(R.id.btn_listadopdf_28);
        b28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1GUgpXBWqPGLWB9FEv4tiAAFyIsAWbUMP/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b29=(Button)findViewById(R.id.btn_listadopdf_29);
        b29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/11aXs2yZ_1FRga6g1WErExuSCQXt_Tc5v/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b30=(Button)findViewById(R.id.btn_listadopdf_30);
        b30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1Z4Ve_81BTbEKApK9YYVAAIJB8XunQa7I/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b31=(Button)findViewById(R.id.btn_listadopdf_31);
        b31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1JsEpdC5vJ-tMP1qrSXrRm68FAWeprEzL/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b32=(Button)findViewById(R.id.btn_listadopdf_32);
        b32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1mHBCi01dnEnjgDoRCaLrsiqaxrYy0yqo/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b33=(Button)findViewById(R.id.btn_listadopdf_33);
        b33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1I-3nxry_cCHwat-Hvok7Qjv8t6Ddapxy/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b34=(Button)findViewById(R.id.btn_listadopdf_34);
        b34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1JNVgrlmFxRLXD_W-ylJj47DK9bizn0T9/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b35=(Button)findViewById(R.id.btn_listadopdf_35);
        b35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1GNZQfPdUexp_F4R06M_GCETl4ytO_NQO/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b36=(Button)findViewById(R.id.btn_listadopdf_36);
        b36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/11Mz62GlZAWpvhme4QI1ICTA-W5q80Hrs/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b37=(Button)findViewById(R.id.btn_listadopdf_37);
        b37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1PMTqC_8qSyFNDhmEUmxwUUNWzCdW8wjj/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b38=(Button)findViewById(R.id.btn_listadopdf_38);
        b38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/17fYhd1yuF5vP4eISy99nvr_wZs-WRJ4A/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b39=(Button)findViewById(R.id.btn_listadopdf_39);
        b39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1c8BknkCxgTb45-rdOaUH3am2G5-mGTa-/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b40=(Button)findViewById(R.id.btn_listadopdf_40);
        b40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1cqcVJ5tLC0nbbbziRTFpgZTdjT4wivQY/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b41=(Button)findViewById(R.id.btn_listadopdf_41);
        b41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/19d7ey-EZU3dLEJa1eKat3vlszJlIm_9Z/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b42=(Button)findViewById(R.id.btn_listadopdf_42);
        b42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1gUU8ZhuiRdOfPsuaOkQkbJrRUOoKqzg_/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b43=(Button)findViewById(R.id.btn_listadopdf_43);
        b43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1i-ptocvTomKIhsVKA805SvlmmchMbBCF/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b44=(Button)findViewById(R.id.btn_listadopdf_44);
        b44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1_SVflmS8e-S3B1JhO2gPpnUrcb0j3Pcv/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b45=(Button)findViewById(R.id.btn_listadopdf_45);
        b45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1A5F24pg-ooswgqyjKzfvFj5K2j0QxTkj/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b46=(Button)findViewById(R.id.btn_listadopdf_46);
        b46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/19NGprZ91qa4bBG8O8lNcj1xJHi22NL6b/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
            }
        });

        b47=(Button)findViewById(R.id.btn_listadopdf_47);
        b47.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpdf=new Intent(getApplication(),MostrarpdfsActivity.class);
                url="https://drive.google.com/file/d/1vgLmD8StS3eZ1D3VmJgUioaUwy-6hvk_/view?usp=sharing";
                intentpdf.putExtra("pdf",url);
                finish();
                startActivity(intentpdf);
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
