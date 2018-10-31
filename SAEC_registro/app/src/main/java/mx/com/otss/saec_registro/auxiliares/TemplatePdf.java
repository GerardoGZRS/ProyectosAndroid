package mx.com.otss.saec_registro.auxiliares;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import mx.com.otss.saec_registro.R;
import mx.com.otss.saec_registro.ReporteActivity;

public class TemplatePdf {
    private Context contexto;
    private File pdfFiles;
    private Document document;
    private PdfWriter pdfWriter;
    private Paragraph parrafo;

    public TemplatePdf(Context contexto){
        this.contexto=contexto;
    }

    public void openDocument(){
        crearArchivo();
        try{
        document = new Document(PageSize.A4);
        pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfFiles));
        document.open();
        }catch (Exception e){
            Log.i("Template", "error: " + e);
        }
    }

    private void crearArchivo(){
        File folder  = new File(Environment.getExternalStorageDirectory().toString(), "ARCHIVOS");
        if (!folder.exists())
            folder.mkdirs();
        pdfFiles = new File(folder, "Reporte.pdf");
    }

    public void contentido(ArrayList<String> lista) throws DocumentException, IOException {
        Font fuente1= new Font();
        fuente1.setSize(12);
        Font fuente2 = new Font();
        fuente2.setSize(10);
        Font fuente3 = new Font();
        fuente3.setSize(9);

        try {

            Drawable d = contexto.getResources().getDrawable(R.drawable.logo);
            BitmapDrawable bitDw = ((BitmapDrawable) d);
            Bitmap bmp = bitDw.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image image = Image.getInstance(stream.toByteArray());
            image.setAbsolutePosition(40, 750);
            image.scalePercent(30);
            document.add(image);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar fecha = Calendar.getInstance();
        int año=fecha.get(Calendar.YEAR);
        int mes=fecha.get(Calendar.MONTH)+1;
        int dia=fecha.get(Calendar.DAY_OF_MONTH);
        String CondicionFech=String.valueOf((año+"-"+mes+"-"+dia));

        Calendar calendario = Calendar.getInstance();
        int hora, minutos, segundos;
        hora =calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);

        Paragraph a=new Paragraph(  "\n\t"+ "Tizayuca, Hidalgo. "+CondicionFech+ " " + hora + ":" + minutos + ":\t" + segundos, fuente3);
        a.setAlignment(Element.ALIGN_CENTER);

        try {
            document.add(a);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        Paragraph espacio = new Paragraph(""
                + "\n"
                + "\n");

        try {
            document.add(espacio);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        Paragraph ac=new Paragraph("SAEC", fuente3);
        ac.setAlignment(Element.ALIGN_CENTER);

        try {
            document.add(ac);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        Paragraph d = new Paragraph(""
                + "\n"
                + "\n");

        try {
            document.add(d);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        Font F1 = new Font(Font.FontFamily.COURIER,12,Font.BOLDITALIC,BaseColor.BLACK);//fuente1
        Font F2 = new Font(Font.FontFamily.COURIER,12,Font.BOLDITALIC,BaseColor.BLACK);//fuente2
        Font F3 = new Font(Font.FontFamily.COURIER,12,Font.BOLDITALIC,BaseColor.BLACK);//fuente3
        Font fuente5 = new Font(Font.FontFamily.COURIER,12, Font.BOLDITALIC, BaseColor.BLACK);

        float[] medidaCeldas = {1.5f, 4.0f,2.0f, 2.0f,2.0f};
        PdfPTable tabla=new PdfPTable(5);

        try {
            tabla.setWidths(medidaCeldas);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        PdfPCell celda=new PdfPCell(new Phrase("Reporte de Registro \n ",F1));
        celda.setColspan(6);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);

        tabla.addCell(new Phrase("Matricula", F2));
        tabla.addCell(new Phrase("Nombre del alumno",F2));
        tabla.addCell(new Phrase("Fecha",F2));
        tabla.addCell(new Phrase("Hora Entrada",F2));
        tabla.addCell(new Phrase("Hora Salida",F2));

        int size = lista.size();
        JSONObject jsonObject = new JSONObject();
        String[] z = new String[lista.size()];
        z = lista.toArray(z);
        String x = null;

        for (String b : z) {
            b = b.replaceAll("\n", "\\n");

            try {

                jsonObject = new JSONObject(b);
                tabla.addCell(new Phrase(jsonObject.getString("id_usuario"),F3));
                tabla.addCell(new Phrase(jsonObject.getString("nombre"),F3));
                tabla.addCell(new Phrase(jsonObject.getString("dia"),F3));
                tabla.addCell(new Phrase(jsonObject.getString("hora_entrada"),F3));
                tabla.addCell(new Phrase(jsonObject.getString("hora_salida"),F3));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            document.add(tabla);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    public void closeDocument(){
        document.close();
    }

    public void viewPdf(){
        Intent intent = new Intent(contexto, ReporteActivity.class);
        intent.putExtra("path", pdfFiles.getAbsolutePath());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        contexto.startActivity(intent);
    }

}
