package com.example.pbudget;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BudgetActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Button viewbudget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        myDb = new DatabaseHelper(this);

        viewbudget = findViewById(R.id.btnviewbudget);

        viewAllBudget();
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
}
