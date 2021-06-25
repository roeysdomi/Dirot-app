package com.myappartment.my_appartment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class createRequest extends AppCompatActivity {
    EditText title, price;
    Button create, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);

        title = (EditText) findViewById(R.id.editText);
        price = (EditText) findViewById(R.id.editText2);
        int counter=0;
        while(counter!=10) {
            title.setText("A"+counter++);
            price.setText("40");
            Status.dira = "4";
            Status.name = "liyyyna";
            createRequest cr = new createRequest();
            cr.createreq(title.getText().toString(), price.getText().toString());

        }
    }

    ;
    public createRequest()
    {}
    public  void createreq(String title,String price) {


        reqnode req = new reqnode(title, Integer.valueOf(price), Status.dira);
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Req");
        db.child(Status.dira).child(title).setValue(req);
    }


}


