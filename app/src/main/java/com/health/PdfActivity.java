package com.health;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;


public class PdfActivity extends AppCompatActivity {
    public Button button_certificado;
    public Button button_vacunas;

    public Bitmap bmp,bmp_1, scaledBitmap,scaledBitmap_1;
    public String Nombre_afiliado,cedula,fecha = new String();
    String[] informationArray = new String[]{"VACUNAS      EDAD EN MESES             EDAD EN AÑOS",
            "                       0    2     4     6    12  15   21       5        12       c/10 ",
            "BGC              X                    ",
            "DTP-HB-Hi        X     X     X            X",
            "VPI                       X     X     X            X  ",
            "VARICELA                                 X",
            "NEUMO                            X     X             X",
            "DPA                                                            X",
            "dT                                                                       X",
            "dpaT                                                                                X",
            "COVID 19                                                                                     X"};

    public Calendar calendar= Calendar.getInstance();
    public String currentDate= DateFormat.getDateInstance().format(calendar.getTime());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        //FloatingActionButton fab = findViewById(R.id.fab);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });*/
        button_certificado = findViewById(R.id.button_certificado);
        button_vacunas = findViewById(R.id.button_vacunas);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.logo_para_pf);
        bmp_1 = BitmapFactory.decodeResource(getResources(), R.drawable.firma_tony);
        ////////////////variables necesarias para generar los pdf
        Nombre_afiliado = AccessActivity.patientH;
        cedula = AccessActivity.documentH;
        fecha = "11.01.2019";

        scaledBitmap = Bitmap.createScaledBitmap(bmp,50,50,false);
        scaledBitmap_1 = Bitmap.createScaledBitmap(bmp_1,50,50,false);

        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        createPDF_certificado();
        createPDF_vacunas();

    }

    private void createPDF_vacunas() {
        button_vacunas.setOnClickListener(new View.OnClickListener() {
            ////////
            //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                PdfDocument myPdfDocument= new PdfDocument();
                Paint myPaint= new Paint();
                ////////////////definir Pagina//////////////7
                PdfDocument.PageInfo myPageInfo1=new PdfDocument.PageInfo.Builder(250, 400,1).create();
                PdfDocument.Page myPage1= myPdfDocument.startPage(myPageInfo1);
                Canvas canvas = myPage1.getCanvas();
                canvas.drawBitmap(scaledBitmap,170,1,myPaint);

                myPaint.setTextAlign(Paint.Align.CENTER);
                myPaint.setTextSize(18.0f);
                canvas.drawText("Health & History", myPageInfo1.getPageWidth()/2,30,myPaint);
                myPaint.setTextSize(10.0f);
                myPaint.setColor(Color.rgb(122,119,119));
                canvas.drawText("Certificado de Vacunación",myPageInfo1.getPageWidth()/2,40,myPaint);


                myPaint.setTextAlign(Paint.Align.LEFT);
                myPaint.setTextSize(9.0f);
                myPaint.setColor(Color.rgb(122,119,119));
                canvas.drawText("Certificado de vacunación de:",10,70,myPaint);
                canvas.drawText(Nombre_afiliado,10,80,myPaint);

                myPaint.setTextAlign(Paint.Align.LEFT);
                myPaint.setTextSize(8.0f);
                myPaint.setColor(Color.BLACK);

                int startXPosition = 10;
                int endXPosition = myPageInfo1.getPageWidth()-10;
                int startYPosition = 100;

                myPaint.setStyle(Paint.Style.STROKE);
                myPaint.setStrokeWidth(1);

                for (String s : informationArray) {
                    canvas.drawText(s, startXPosition, startYPosition, myPaint);
                    canvas.drawLine(startXPosition, startYPosition + 3, endXPosition, startYPosition + 3, myPaint);
                    startYPosition += 20;

                }
                canvas.drawLine(52,90,52,300,myPaint);
                canvas.drawLine(65,103,65,300,myPaint);
                canvas.drawLine(80,103,80,300,myPaint);
                canvas.drawLine(95,103,95,300,myPaint);
                ////////////////////////////////////////////////////7
                canvas.drawLine(110,103,110,300,myPaint);
                canvas.drawLine(125,103,125,300,myPaint);
                canvas.drawLine(140,103,140,300,myPaint);
                canvas.drawLine(155,90,155,300,myPaint);
                //////////////////////////////////////

                canvas.drawLine(180,103,180,300,myPaint);
                canvas.drawLine(210,103,210,300,myPaint);

                canvas.drawLine(240,90,240,300,myPaint);
                myPaint.setStyle(Paint.Style.STROKE);
                myPaint.setStrokeWidth(2);
                //////////////crear rectangulos
                canvas.drawRect(10,90,myPageInfo1.getPageWidth()-10,302,myPaint);

                myPaint.setStrokeWidth(0);
                myPaint.setStyle(Paint.Style.FILL);



                canvas.drawText("Note",10,320,myPaint);
                canvas.drawLine(35,325,myPageInfo1.getPageWidth()-10,325,myPaint);
                canvas.drawLine(10,345,myPageInfo1.getPageWidth()-10,345,myPaint);
                canvas.drawLine(10,365,myPageInfo1.getPageWidth()-10,365,myPaint);


                canvas.drawText("Generado el día "+currentDate,myPageInfo1.getPageWidth()-130,380,myPaint);

                myPdfDocument.finishPage(myPage1);


                File file = new File (Environment.getExternalStorageDirectory(),"/Download/CarneDeVacunas.pdf");
                //File fileA = new File (Environment.getExternalStorageDirectory(),"/Downloads/CarneDeVacunas.pdf");
                try {
                    myPdfDocument.writeTo(new FileOutputStream(file));
                    myPdfDocument.close();
                    AlertDialog.Builder alerta = new AlertDialog.Builder(PdfActivity.this);
                    alerta.setMessage("Se encuentra en Almacenamiento interno, carpeta Descargas")
                            .setCancelable(true);
                    AlertDialog titulo  = alerta.create();
                    titulo.setTitle("Certificado Creado");
                    titulo.show();
                }catch (IOException e ){
                    e.printStackTrace();
                    AlertDialog.Builder alerta = new AlertDialog.Builder(PdfActivity.this);
                    alerta.setMessage("Ha ocurrido un problema, por favor comuníquese con atención al cliente")
                            .setCancelable(true);
                    AlertDialog titulo  = alerta.create();
                    titulo.setTitle("Error");
                    titulo.show();
                }
            }
        });
    }

    private void createPDF_certificado() {
        button_certificado.setOnClickListener(new View.OnClickListener() {
           ////////
            //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                PdfDocument myPdfDocument= new PdfDocument();
                Paint myPaint= new Paint();
                ////////////////definir Pagina//////////////7
                PdfDocument.PageInfo myPageInfo1=new PdfDocument.PageInfo.Builder(260, 400,1).create();
                PdfDocument.Page myPage1= myPdfDocument.startPage(myPageInfo1);
                Canvas canvas = myPage1.getCanvas();
                canvas.drawBitmap(scaledBitmap,200,0,myPaint);
                canvas.drawBitmap(scaledBitmap_1,10,290,myPaint);

                myPaint.setTextAlign(Paint.Align.CENTER);
                myPaint.setTextSize(20.0f);
                canvas.drawText("Health & History", myPageInfo1.getPageWidth()/2,30,myPaint);
                myPaint.setTextSize(10.0f);
                myPaint.setColor(Color.BLACK);
                canvas.drawText("CERTIFICA QUE:",myPageInfo1.getPageWidth()/2,60,myPaint);


                myPaint.setTextAlign(Paint.Align.LEFT);
                myPaint.setTextSize(9.0f);
                myPaint.setColor(Color.rgb(122,119,119));

                //Nombre_afiliado="Pepito Perez";
                //cedula="1010515250";
               // fecha="11.01.2019";

                canvas.drawText("El (La) señor(a):",10,100,myPaint);
                canvas.drawText(Nombre_afiliado,10,110,myPaint);
                canvas.drawText("identificado(a) con:",10,120,myPaint);
                canvas.drawText("C.C "+cedula+" se encuentra afiliado(a) a la EPS ",10,130,myPaint);
                canvas.drawText("en condición de COTIZANTE con covertura ACTIVA",10,140,myPaint);

                myPaint.setTextAlign(Paint.Align.LEFT);
                myPaint.setTextSize(8.0f);
                myPaint.setColor(Color.BLACK);

                canvas.drawText("Fecha de Activación de Servicios ",10,160,myPaint);
                canvas.drawText(fecha+" ",160,160,myPaint);

                canvas.drawText("Estado de la  Afiliación",10,180,myPaint);
                canvas.drawText("Activo ",160,180,myPaint);

                myPaint.setTextAlign(Paint.Align.LEFT);
                myPaint.setTextSize(9.0f);
                myPaint.setColor(Color.rgb(122,119,119));

                canvas.drawText("La presente certificación se  expide  a solicitud del  ",10,200,myPaint);
                canvas.drawText("interesado a traves de la APP Health & History para  ",10,210,myPaint);
                canvas.drawText("QUIEN INTERESE,se expide el "+currentDate,10,220,myPaint);

                canvas.drawText("La certificación tiene validez de un mes  a la fecha ",10,250,myPaint);
                canvas.drawText("de generación del presente certificado.",10,260,myPaint);


                myPaint.setColor(Color.BLACK);
                canvas.drawText("Cordialmente",10,280,myPaint);



                canvas.drawText("Anthony Edward Stark",10,350,myPaint);
                canvas.drawText("Gerente de Operaciones",10,360,myPaint);




                myPdfDocument.finishPage(myPage1);


                File file = new File (Environment.getExternalStorageDirectory(),"/Download/CERTIFICADO_AFILIACIÓN.pdf");

                try {
                    myPdfDocument.writeTo(new FileOutputStream(file));
                    AlertDialog.Builder alerta = new AlertDialog.Builder(PdfActivity.this);
                    alerta.setMessage("Se encuentra en Almacenamiento interno, carpeta Descargas")
                                .setCancelable(true);
                    AlertDialog titulo  = alerta.create();
                    titulo.setTitle("Certificado Generado");
                    titulo.show();
                }catch (IOException e ){
                    e.printStackTrace();
                    AlertDialog.Builder alerta = new AlertDialog.Builder(PdfActivity.this);
                    alerta.setMessage("Ha ocurrido un problema, por favor comuníquese con atención al cliente")
                            .setCancelable(true);
                    AlertDialog titulo  = alerta.create();
                    titulo.setTitle("Error");
                    titulo.show();
                }
                myPdfDocument.close();





            }
        });
    }
}
