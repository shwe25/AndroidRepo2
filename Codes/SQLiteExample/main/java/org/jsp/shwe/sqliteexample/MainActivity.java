package org.jsp.shwe.sqliteexample;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText id,name,marks;
    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = (EditText) findViewById(R.id.studId);
        name = (EditText) findViewById(R.id.studName);
        marks = (EditText) findViewById(R.id.studMarks);

        database = openOrCreateDatabase("studentDb", Context.MODE_PRIVATE,null);
        database.execSQL("CREATE TABLE IF NOT EXISTS student(id VARCHAR,name VARCHAR,marks VARCHAR);");
    }

    public void add(View view){

        if(id.getText().toString().trim().length()== 0 ||
                name.getText().toString().trim().length()== 0 ||
                marks.getText().toString().trim().length()== 0){

            display("Error","All the fields are mandatory...");
        }else{
            ContentValues values = new ContentValues();
            values.put("id",id.getText().toString());
            values.put("name",name.getText().toString());
            values.put("marks",marks.getText().toString());

            database.insert("student",null,values);
            display("Success","Row inserted Successfully");
            clearField();
        }
    }
    public void view(View view){
        if(id.getText().toString().trim().length()==0){
            display("Error","Pls enter id to view student Record");
        }else{
            Cursor cursor = database.rawQuery("SELECT * FROM student WHERE id='"+id.getText()+"';",null);

            if(cursor.moveToNext()){
                StringBuilder st = new StringBuilder();
                st.append("ID = "+cursor.getString(0));
                st.append("\nName = "+cursor.getString(1));
                st.append("\nMarks = "+cursor.getString(2));
                display("Success",st.toString());
            }else{
                display("Error","No record found..");
            }
        }
    }
    public void delete(View view){
        if(id.getText().toString().trim().length()==0){
            display("Error","Pls enter id to delete student Record");
        }else{
            int i = database.delete("student","id='"+id.getText()+"'",null);
            if(i > 0)
                display("Success","Given Id record Deleted Successfully");
            else
                display("Error","No Record found to delete");
        }

    }
    public void update(View view){
        if(id.getText().toString().trim().length()== 0 ||
                name.getText().toString().trim().length()== 0 ||
                marks.getText().toString().trim().length()== 0) {

            display("Error", "All the fields are mandatory...");
        }else{
            ContentValues values = new ContentValues();
            values.put("name",name.getText().toString());
            values.put("marks",marks.getText().toString());
            int i = database.update("student",values,"id='"+id.getText()+"'",null);
            if(i > 0)
                display("Success","Successfully updated student record");
            else
                display("Error","No record updated");
        }
    }

    public void viewAll(View view){
        Cursor cursor = database.rawQuery("SELECT * FROM student;",null);
        StringBuilder st = new StringBuilder("Records");
        while(cursor.moveToNext()){
            st.append("\nID = "+cursor.getString(0));
            st.append("\nName = "+cursor.getString(1));
            st.append("\nMarks = "+cursor.getString(2));
            st.append("\n-----------------------");
        }
        if(st.toString().equals("Records"))
            display("Error","No Records found");
        else
            display("Success",st.toString());

    }
    private void display(String title, String body) {
        new AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(body)
                .show();
    }

    private void clearField() {
        id.setText("");
        name.setText("");
        marks.setText("");
    }
}
