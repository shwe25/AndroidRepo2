package org.jsp.shwe.internalstorageapp;

import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class Main2Activity extends AppCompatActivity {

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText = (EditText)findViewById(R.id.editText);
    }
    private void writeData(File file) {
        try(FileOutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream)){
            streamWriter.write(editText.getText().toString());
            streamWriter.flush();
            display("Data written Successfully "+file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void display(String s) {
        new AlertDialog.Builder(this)
                .setCancelable(true)
                .setMessage(s)
                .show();
    }

    public void saveCacheInternal(View view){
       // String data = editText.getText().toString();
        File folder = getCacheDir();
        File file = new File(folder,"MyData.txt");
        writeData(file);
    }
    public void saveCacheExternal(View view){

        File folder = getExternalCacheDir();
        File file = new File(folder,"MyData.txt");
        writeData(file);
    }

    public void savePrivateExternal(View view){

        File folder = getExternalFilesDir("Jsp");
        File file = new File(folder,"MyData.txt");
        writeData(file);
    }

    public void savePublicExternal(View view){

        File folder = Environment.getExternalStoragePublicDirectory("JSP");
        File file = new File(folder,"MyData.txt");
        writeData(file);
    }
}
