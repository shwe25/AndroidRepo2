package org.jsp.shwe.internalstorageapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
    }

    public void write(View view){
        File file;
        FileOutputStream outputStream = null;
        OutputStreamWriter streamWriter = null;
        try {
            file = getFilesDir();
            outputStream = openFileOutput("MyText.txt", Context.MODE_APPEND);
            streamWriter = new OutputStreamWriter(outputStream);
            streamWriter.write(editText.getText().toString());
            streamWriter.flush();
            Log.d("File Path",file.toString());
            Toast.makeText(this,"File Saved in the path "+file,Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                streamWriter.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void read(View view){

        try(FileInputStream  inputStream = openFileInput("MyText.txt");
            InputStreamReader reader = new InputStreamReader(inputStream)) {

            char[] ch = new char[100];
            String str = "";
            int chReader = 0;

            while((chReader = reader.read(ch))>0){
                str += String.copyValueOf(ch,0,chReader);
            }
            editText.setText(str);
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void next(View view){
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
}
