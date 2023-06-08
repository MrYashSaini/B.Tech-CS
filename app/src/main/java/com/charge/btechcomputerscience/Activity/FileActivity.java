package com.charge.btechcomputerscience.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.charge.btechcomputerscience.DownloandDB;
import com.charge.btechcomputerscience.R;
import com.charge.btechcomputerscience.databasemenu;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileActivity extends AppCompatActivity implements OnPageChangeListener {
    private int pageNumber = 0;
    private String filePath;
    String urls;
    PDFView pdfView;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        pdfView = findViewById(R.id.abc);

//        get intent
        Intent data = getIntent();
        String url = data.getStringExtra("url");
        boolean type = data.getBooleanExtra("type",true);

//        show progress dialog
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..");
        dialog.show();

        if (type)
            new RetrivePdfStream().execute(url);
        else
            showPdf(url);
//        loadPDF();
    }
//      show pdf using path
    private void showPdf(String url){

        pdfView.fromFile(new File(url)).defaultPage(1).enableSwipe(true).onPageChange((OnPageChangeListener) this).load();
        dialog.dismiss();
        File file = new File("notes/unit notes.pdf");
        if (file.exists()){
            Log.d("fileExists","yes");
        }
        else {
            Log.d("fileExists","no");
        }
    }
//    show pdf by external storage
    private void loadPDF() {
        filePath = "/storage/emulated/0/Android/data/com.charge.btechcomputerscience/files/notes/unit notes.pdf";
        File file = new File(filePath);
        if (file.exists()) {
            pdfView.fromFile(file)
                    .defaultPage(1)
                    .onPageChange(this)
                    .onLoad((OnLoadCompleteListener) this)
                    .load();
            Log.d("fileExists","yes");

        } else {
            Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
            finish();
            Log.d("fileExists","no");
        }
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", filePath, page + 1, pageCount));
    }

//    @Override
//    public void loadComplete(int nbPages) {
//        setTitle(String.format("%s %s / %s", filePath, pageNumber + 1, nbPages));
//    }




//    show pdf in screen using url
    class RetrivePdfStream extends AsyncTask<String, Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {

                // adding url
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                // if url connection response code is 200 means ok the execute
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }
            // if error return null
            catch (IOException e) {
                return null;
            }
            return inputStream;
        }

        @Override
        // Here load the pdf and dismiss the dialog box
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).load();
            dialog.dismiss();
        }
    }


}