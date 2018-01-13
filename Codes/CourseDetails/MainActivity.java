package com.example.hima.coursedetailsapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CheckBox java,jee,sql,web,frame,andr,python;
    private RadioGroup group;
    private RadioButton show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        java = findViewById(R.id.java_cb);
        jee = findViewById(R.id.jee_cb);
        sql = findViewById(R.id.sql_cb);
        web = findViewById(R.id.web_cb);
        frame = findViewById(R.id.frame_cb);
        andr =  findViewById(R.id.andr_cb);
        python = findViewById(R.id.python_cb);
        group = findViewById(R.id.dispChoice);
    }

    public void showFee(View view){
        int radioBtnId =  group.getCheckedRadioButtonId();
        show = findViewById(radioBtnId);
        StringBuilder sb = new StringBuilder("Fee Structure\n");
        int total = 0,count = 0;
        if(java.isChecked()){
            count++;
            sb.append("\n");
            sb.append(getResources().getString(R.string.java));
            sb.append(" - "+getResources().getInteger(R.integer.javaf));
            total += getResources().getInteger(R.integer.javaf);
        }
        if(jee.isChecked()){
            count++;
            sb.append("\n");
            sb.append(getResources().getString(R.string.jee));
            sb.append(" - "+getResources().getInteger(R.integer.jeef));
            total += getResources().getInteger(R.integer.jeef);
        }
        if(sql.isChecked()){
            count++;
            sb.append("\n");
            sb.append(getResources().getString(R.string.sql));
            sb.append(" - "+getResources().getInteger(R.integer.sqlf));
            total += getResources().getInteger(R.integer.sqlf);
        }
        if(web.isChecked()){
            count++;
            sb.append("\n");
            sb.append(getResources().getString(R.string.web));
            sb.append(" - "+getResources().getInteger(R.integer.webf));
            total += getResources().getInteger(R.integer.webf);
        }
        if(frame.isChecked()){
            count++;
            sb.append("\n");
            sb.append(getResources().getString(R.string.frameworks));
            sb.append(" - "+getResources().getInteger(R.integer.framef));
            total += getResources().getInteger(R.integer.framef);
        }
        if(andr.isChecked()){
            count++;
            sb.append("\n");
            sb.append(getResources().getString(R.string.android));
            sb.append(" - "+getResources().getInteger(R.integer.andrf));
            total += getResources().getInteger(R.integer.andrf);
        }
        if(python.isChecked()){
            count++;
            sb.append("\n");
            sb.append(getResources().getString(R.string.python));
            sb.append(" - "+getResources().getInteger(R.integer.pythonf));
            total += getResources().getInteger(R.integer.pythonf);
        }
        if(count > 0) {
            sb.append("\n---------------------------");
            sb.append("\n\nTotal fee = " + total);
            if(show.getText().toString().equals("ShowToast"))
                showToast(sb.toString());
            else
                showAlertDialog(sb.toString());
        }
        else{
            sb.append("\n Please choose any of the course");
            showToast(sb.toString());
        }
    }

    private void showAlertDialog(String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selected Courses Fee ");
        builder.setMessage(msg);
        DialogInterface.OnClickListener okBtn = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.this.finish();
            }
        };
        builder.setPositiveButton("Ok",okBtn);
        DialogInterface.OnClickListener cancelBtn = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        };
        builder.setNegativeButton("Cancel",cancelBtn);

        //To get AlertDialog ref we make use Builder ref variable
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
