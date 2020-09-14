package com.example.pbudget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateBudget extends AppCompatActivity {

    DatabaseHelper myDb;
 Button calllogin,createbudget;
    EditText etidnumber,etusername,etpassword,etconfirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_budget);
        // get database helper
            myDb = new DatabaseHelper(this);

        // create hooks for our captured data
        calllogin = findViewById(R.id.btncalllogin);
        createbudget = findViewById(R.id.btncreatebudget);
        etidnumber = findViewById(R.id.txtidnumber);
        etusername = findViewById(R.id.txtusername);
        etpassword = findViewById(R.id.txtpassword);
        etconfirmpassword = findViewById(R.id.txtconfirmpassword);



        AddData();

        calllogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateBudget.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
    public  void  AddData(){

        createbudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              boolean isinserted =  myDb.insertDate(etidnumber.getText().toString(),etusername.getText().toString(),
                        etpassword.getText().toString() );

              if (isinserted = true){
                  Toast.makeText(CreateBudget.this, "Inserted", Toast.LENGTH_SHORT).show();

                  Intent intent = new Intent(CreateBudget.this,BudgetActivity.class);
                  startActivity(intent);
                  finish();
              }else{
                  Toast.makeText(CreateBudget.this, "failed", Toast.LENGTH_SHORT).show();
              }
            }
        });
    }


}
