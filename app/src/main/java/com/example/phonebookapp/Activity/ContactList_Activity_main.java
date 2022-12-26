package com.example.phonebookapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.phonebookapp.Adapter.ContactListAdapter;
import com.example.phonebookapp.R;
import com.example.phonebookapp.model.DBHelper;
import com.example.phonebookapp.model.User;

import java.util.ArrayList;

public class ContactList_Activity_main extends AppCompatActivity {

    ListView listView;
    ArrayList<User> userList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        listView=findViewById(R.id.listView);
        getData();
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(ContactList_Activity_main.this,AddContact_Activity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    void getData()
    {
        DBHelper dbHelper=new DBHelper(ContactList_Activity_main.this);
        Cursor cursor= dbHelper.viewData();
        while (cursor.moveToNext())
        {
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String contact=cursor.getString(2);

            User user=new User(id,name,contact);
            userList.add(user);
        }
        ContactListAdapter contactListAdapter=new ContactListAdapter(this,userList);
        listView.setAdapter(contactListAdapter);

    }
}