package com.example.phonebookapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.phonebookapp.R;
import com.example.phonebookapp.model.DBHelper;

public class AddContact_Activity extends AppCompatActivity
{
    EditText ename,econtact;
    Button submit;

    int id;
    String name;
    String contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        ename=findViewById(R.id.ename);
        econtact=findViewById(R.id.econtact);
        submit=findViewById(R.id.submit);

        if(getIntent().getExtras()!=null)
        {
             id = getIntent().getIntExtra("id",0);
             name = getIntent().getStringExtra("contact");
             contact = getIntent().getStringExtra("contact");

             ename.setText(""+name);
             econtact.setText(""+contact);
        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String name=ename.getText().toString();
            String contact=econtact.getText().toString();

            if(getIntent().getExtras()==null)
            {
                DBHelper dbHelper=new DBHelper(AddContact_Activity.this);
                dbHelper.insertData(name,contact);
                Intent intent = new Intent(AddContact_Activity.this,ContactList_Activity_main.class);
                startActivity(intent);
                finish();
            }
            else {
                DBHelper dbHelper=new DBHelper(AddContact_Activity.this);
                dbHelper.updateData(id,name,contact);
                Intent intent = new Intent(AddContact_Activity.this,ContactList_Activity_main.class);
                startActivity(intent);
                finish();
            }

            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(AddContact_Activity.this,ContactList_Activity_main.class);
        startActivity(intent);
        finish();
    }

}