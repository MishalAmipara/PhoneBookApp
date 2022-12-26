package com.example.phonebookapp.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.phonebookapp.Activity.AddContact_Activity;
import com.example.phonebookapp.Activity.ContactList_Activity_main;
import com.example.phonebookapp.R;
import com.example.phonebookapp.model.DBHelper;
import com.example.phonebookapp.model.User;

import java.util.ArrayList;

public class ContactListAdapter extends BaseAdapter
{
    Activity activity;
    ArrayList<User> userList;
    TextView textView_Name,textView_Contact;

    public ContactListAdapter(Activity activity, ArrayList<User> userList) {
        this.activity=activity;
        this.userList=userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(activity).inflate(R.layout.contact_list_item,viewGroup,false);
        textView_Name=view.findViewById(R.id.contact_list_item_name);
        textView_Contact=view.findViewById(R.id.contact_list_item_contact);
        User user=userList.get(i);
        int id=user.getId();
        String name=user.getName();
        String contact=user.getContact();
        textView_Name.setText(""+name);
        textView_Contact.setText(""+contact);

        ImageView imageView = view.findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(activity,imageView);

                activity.getMenuInflater().inflate(R.menu.update_delete, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        if(menuItem.getItemId()==R.id.update)
                        {
                            Intent intent = new Intent(activity, AddContact_Activity.class);
                            intent.putExtra("id",id);
                            intent.putExtra("name",name);
                            intent.putExtra("contact",contact);
                            activity.startActivity(intent);
                            activity.finish();
                        }
                        else if(menuItem.getItemId()==R.id.delete){

                            DBHelper dbHelper = new DBHelper(activity);

                            dbHelper.deleteData(id);

                            userList.remove(i);
                            notifyDataSetChanged();

                        }

                        return false;
                    }
                });



                popupMenu.show();
            }
        });

        return view;
    }
}
