package com.example.assitant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Button save;
    private EditText name;
    private EditText how;
    private EditText pillsnumber;
    private EditText phonenumber;
    private TextView from;
    private TextView to;
    private String fromdb;
    private String todb;
    DatabaseReference reff;
    todo todo;
    private DatePickerDialog.OnDateSetListener mdatesetlistenerf;
    private DatePickerDialog.OnDateSetListener mdatesetlistenert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = (Button) findViewById(R.id.save);
        this.name = (EditText) findViewById(R.id.nameofdrag);
        how = (EditText) findViewById(R.id.how);
        pillsnumber = (EditText) findViewById(R.id.pills);
        phonenumber = (EditText) findViewById(R.id.phonenumber);
        from = (TextView) findViewById(R.id.From11);
        to = (TextView) findViewById(R.id.to);
        reff = FirebaseDatabase.getInstance().getReference().child("Notif");
        todo = new todo();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(v);
            }
        });

        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal;
                cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth, mdatesetlistenerf, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                dialog.show();
            }
        });
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal;
                cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth, mdatesetlistenert, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                dialog.show();
            }
        });

        mdatesetlistenerf = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fromdb = year + "/" + month + "/" + dayOfMonth;
            }
        };
        mdatesetlistenert = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                todb = year + "/" + month + "/" + dayOfMonth;
            }
        };


    }


    public void add(View view) {

        String n = name.getText().toString();
        String h = how.getText().toString();
        String ph = phonenumber.getText().toString();
        int p = Integer.parseInt(pillsnumber.getText().toString());

        todo.setFirstthink(n);
        todo.setSecondthink(h);
        todo.setNumberr3(ph);
        todo.setNumber4(p);
        todo.setFrom(fromdb);
        todo.setTo(todb);
        reff.child(n).setValue(todo);
        Toast.makeText(getApplicationContext(), "Data inserted !", Toast.LENGTH_LONG).show();
    }







    
}