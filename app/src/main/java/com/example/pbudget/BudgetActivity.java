package com.example.pbudget;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BudgetActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Button viewbudget,update;
    TextView txtid,txtidnumber,txtusername,txtpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        myDb = new DatabaseHelper(this);

        viewbudget = findViewById(R.id.btnviewbudget);
        update = findViewById(R.id.btnupdate);
        txtid = findViewById(R.id.txtconfirmpassword);
        txtidnumber = findViewById(R.id.txtidnumber);
        txtusername = findViewById(R.id.txtusername);
        txtpassword = findViewById(R.id.txtpassword);
       // viewAllBudget();
       // UpdateBudget();
    }

    public  void viewAllBudget(){
        viewbudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();

                if(res.getCount() == 0){
                    // error message
                    showMessage("Error","No Budget found");
                    return;
                }else{
                        StringBuffer buffer = new StringBuffer();

                        while (res.moveToNext()){
                            buffer.append("id :" + res.getString(0)+"\n");
                            buffer.append("idnumber :" + res.getString(1)+"\n");
                            buffer.append("username :" + res.getString(2)+"\n");
                            buffer.append("password :" + res.getString(3)+"\n\n");
                        }
                        // show all data
                    showMessage("Budgets",buffer.toString());
                }

            }
        });
    }
    public  void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public  void UpdateBudget(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean  isUpdated = myDb.updateBudget(txtid.getText().toString(),txtidnumber.getText().toString(),
                        txtusername.getText().toString(),txtpassword.getText().toString());

               if(isUpdated == true){
                    Toast.makeText(BudgetActivity.this, "updated", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(BudgetActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
