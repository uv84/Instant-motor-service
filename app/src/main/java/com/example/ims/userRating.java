package com.example.ims;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class userRating extends AppCompatActivity {
    Button rate;
    RatingBar rating;
    EditText feedback;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rating);
        rate = findViewById(R.id.rate);
        feedback = findViewById(R.id.feedback);
        rating = (RatingBar) findViewById(R.id.rating);
        //String Feedback= feedback.getText().toString().trim();
        //String Ratingbar= rating.getRating();
        ImageView imageholder=findViewById(R.id.imagegholder);
        TextView nameholder=findViewById(R.id.nameholder);


        //ImageView imageholder= this.<ImageView>findViewById(R.id.imagegholder);
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference();
        String Uid = mDatabase.push().getKey();
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        String UID= "7715050395";
        String mecid= getIntent().getStringExtra("phone");


        mdatabase = FirebaseDatabase.getInstance().getReference().child("MechanicUsr");
        mdatabase.child(mecid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name1 = dataSnapshot.getValue(dataholder.class).getName();
                String image = dataSnapshot.getValue(dataholder.class).getPimage();

                nameholder.setText(name1);

                Glide.with(imageholder.getContext()).load(image).into(imageholder);






            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DatabaseReference root=db.getReference("Feedback");
                mdatabase = FirebaseDatabase.getInstance().getReference().child("MechanicUsr");
                String UID= "7715050395";
                mdatabase.child(UID).child("Rating").child(Uid).setValue(String.valueOf(rating.getRating()));
                mdatabase.child(UID).child("Feedback").child(Uid).setValue(String.valueOf(feedback.getText().toString()));


                Toast.makeText(getApplicationContext(),  "\n" + rating.getRating(), Toast.LENGTH_LONG).show();
                //root.child(Uid).setValue(String.valueOf(rating.getRating()));

            }
        });


    }
}