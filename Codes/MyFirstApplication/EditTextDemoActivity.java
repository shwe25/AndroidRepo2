package edu.jsp.myfirstapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditTextDemoActivity extends AppCompatActivity {

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_demo);
        editText = (EditText) findViewById(R.id.editText);
    }
    public void getToast(View vi) {
        String name = editText.getHint().toString();
        Toast.makeText(this,name,Toast.LENGTH_LONG).show();
    }
}
