package com.myappartment.my_appartment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class requestsList extends AppCompatActivity {

    LinearLayout parent;
    int counter;
    public  ArrayList<reqnode> requests=new ArrayList<reqnode>();
    @Override
    public  void onCreate(Bundle savedInstanceState) {
        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_list);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Req/4");

        requests=new ArrayList<>();

        System.out.println("loooooooooooook:"+requests.size());

        mDatabase.addValueEventListener(new ValueEventListener()
        {
            @Override
            public  void onDataChange(DataSnapshot dataSnapshot) {
                //int size = (int)dataSnapshot.getChildrenCount();
                  counter=0;
                parent = (LinearLayout)findViewById(R.id.ll_parent);
                Button btn;

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                params.setMargins(0, 20, 0, 20);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        reqnode n=snapshot.getValue(reqnode.class);



                    requests.add(n);

                    btn = new Button(requestsList.this);
                    btn.setBackgroundResource(R.drawable.mybtn);

                    btn.setClickable(true);
                    btn.setId(counter);

                    btn.setText("Request:"+n.getTitle()+", Cost:"+n.getPrice());
                    btn.setTag(View.ROTATION_X.toString()+View.ROTATION_Y.toString());
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Status.title=requests.get(v.getId()).getTitle();
                            Status.price=requests.get(v.getId()).getPrice();

                            Intent goToReq = new Intent(requestsList.this,renterRrequest.class);
                            startActivity(goToReq);

                        }
                    });
                    counter++;
                    parent.addView(btn,params);


                }
                counter=0;
                requestsList.CreateReqlist();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Status.manager=false;
            }
        });

         //mako.CreateReqlist();
        ///CREATE DYNAMIC BUTTONS///



        System.out.println("loooooooooooook:"+requests.size());




    }



    public requestsList()
    {}
    public  static void CreateReqlist()
   {


       DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Req/4");


       mDatabase.addListenerForSingleValueEvent(new ValueEventListener()
               {
                   @Override
                   public  void onDataChange(DataSnapshot dataSnapshot) {
                       //int size = (int)dataSnapshot.getChildrenCount();

                       for (DataSnapshot snapshot : dataSnapshot.getChildren()) {


                            reqnode r=snapshot.getValue(reqnode.class);

                              //Status.requests2.add(r);
                         //  System.out.println(r.getTitle());

                       }


                   }
                   @Override
                   public void onCancelled(DatabaseError databaseError) {
                       // Status.manager=false;
                   }
               });
   }
}
