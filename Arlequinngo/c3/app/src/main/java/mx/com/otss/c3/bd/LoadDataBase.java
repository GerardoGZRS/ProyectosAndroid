package mx.com.otss.c3.bd;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import mx.com.otss.c3.model.NivelDos;
import mx.com.otss.c3.model.NivelUno;
import mx.com.otss.c3.model.Reglamentos;

public class LoadDataBase extends SQLiteOpenHelper {

    //Ruta por defecto de las bases de datos en el sistema Android
    private static String DB_PATH = "/data/data/mx.com.otss.c3/databases/";
    private static String DB_NAME = "arlequinngo";
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    public static final String TABLA_NIVEL_UNO = "niveluno";
    public static final String idNivelUno = "idNivelUno";
    public static final String nombreTitulo = "nombreTitulo";
    public static final String contenidoNU = "contenidoNU";
    public static final String TABLA_NIVEL_DOS = "niveldos";
    public static final String idNivelDos = "idNivelDos";
    public static final String nombreIndex = "nombreIndex";
    public static final String contenidoND = "contenidoND";
    public static final String idNivelUnoF = "idNivelUno";
    public static final String TABLA_REGLAMENTOS_COMPLETOS = "reglamentos_completos";
    public static final String idReglamentoCompleto = "idReglamento";
    public static final String nombre_reglamento = "nombre_reglamento";
    public static final String contenido = "contenido";

    public LoadDataBase(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    public void createDataBase() throws IOException{

        boolean dbExist = checkDataBase();

        if(dbExist){
            Log.i("La base existe!!", "");
        }else{

            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copiando Base de Datos");
            }
        }

    }
    /**
     * Comprueba si la base de datos existe para evitar copiar siempre el
     * fichero cada vez que se abra la aplicación.
     * @return true si existe, false si no existe
     */
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{

            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase
                    .openDatabase(myPath,
                            null,
                            SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){
            Toast.makeText(myContext, "Error, no existe la base!!!!!", Toast.LENGTH_SHORT).show();
            }

        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    /**
     * Copia nuestra base de datos desde la carpeta assets a la recién creada
     * base de datos en la carpeta de sistema, desde dónde podremos acceder a
     * ella.
     * Esto se hace con bytestream.
     * */
    private void copyDataBase() throws IOException{

        //Abrimos el fichero de base de datos como entrada
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        //Ruta a la base de datos vacía recién creada
        String outFileName = DB_PATH + DB_NAME;

        //Abrimos la base de datos vacía como salida
        OutputStream myOutput = new FileOutputStream(outFileName);

        //Transferimos los bytes desde el fichero de entrada al de salida
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void open() throws SQLException{

        try {
            createDataBase();
        } catch (IOException e) {
            throw new Error("Ha sido imposible crear la Base de Datos");
        }

        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase
                .openDatabase(myPath,
                        null,
                        SQLiteDatabase.OPEN_READONLY);
        }

    @Override
    public synchronized void close() {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public ArrayList loadNivelUno() {

        ArrayList list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] campos = new String[]{idNivelUno, nombreTitulo, contenidoNU};
        Cursor c = db.query("niveluno", campos, null, null, null, null, null);

        try {
            while (c.moveToNext()) {
                NivelUno colaborador = new NivelUno();
                colaborador.setIdNivelUno(c.getInt(0));
                colaborador.setNombre(c.getString(1));
                colaborador.setContenido(c.getString(2));
                list.add(colaborador);
            }
        } finally {
            c.close();
        }
        return list;
    }

    public ArrayList loadNivelDos(int id) {
        ArrayList list = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String[] campos = new String[]{idNivelDos, nombreIndex, contenidoND, idNivelUnoF};
        String where = idNivelUnoF + "= ?";
        int whereArgs = id;
        String[] args = {String.valueOf(whereArgs)};
        Cursor c = db.query("niveldos", null, where, args, null, null, null);

        try{
            while (c.moveToNext()){
                NivelDos colaborador = new NivelDos();
                colaborador.setIdNivelDos(c.getInt(0));
                colaborador.setNombreIndex(c.getString(1));
                colaborador.setContenido(c.getString(2));
                colaborador.setIdNivelUno(c.getString(3));
                list.add(colaborador);
            }
        } finally {
            c.close();
        }
        return list;
    }

    public ArrayList loadReglamentos() {

        ArrayList list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] campos = new String[]{nombre_reglamento, contenido};
        Cursor c = db.query("reglamentos_completos", campos, null, null, null, null, null);

        try {

            while (c.moveToNext()) {
                Reglamentos contenido = new Reglamentos();
                contenido.setNombreReglamento(c.getString(0));
                contenido.setContenido(c.getString(1));
                list.add(contenido);
            }
        } finally {
            c.close();
        }
        return list;
    }

}