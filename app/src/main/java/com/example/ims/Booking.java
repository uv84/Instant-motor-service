package com.example.ims;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class  Booking extends AppCompatActivity {
    Button tpbutton;
    private DatabaseReference ud,uv;
    private String phone,userphone,name1,phone1;
    DatabaseReference mdatabase;
    TextView tvName;
    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        tpbutton=findViewById(R.id.button3);
        ud = FirebaseDatabase.getInstance().getReference().child("Users");
        tvName = (TextView)findViewById(R.id.textView2);
        String mecid= getIntent().getStringExtra("keyname");
        phone1="7715050395";
        phone=mecid;
       tpbutton.setOnClickListener(v -> MechanicNewActivity());

    }

    private void MechanicNewActivity() {
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference root=db.getReference("Booking");


        String msg= "Fix My vehicle";
        String UID= userId;

        mdatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mdatabase.child(UID).addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
              String name1 = dataSnapshot.getValue(User.class).getdisplayname();
              userphone = dataSnapshot.getValue(User.class).getcontact();
              tvName.setText(phone);


              GPSTracker gps = new GPSTracker(Booking.this);
              double latitude1 = gps.getLatitude();
              double longitude1 = gps.getLongitude();
              Double latitude = latitude1;
              Double longitude = longitude1;
              bookholder obj=new bookholder(name1,phone,userphone,userId,msg, latitude.doubleValue(),longitude.doubleValue());
              root.child(phone).setValue(obj);
              Intent it= new Intent(Booking.this,MechanicReach.class);
              it.putExtra("phone",phone);
              startActivity(it);

        }

         @Override
        public void onCancelled(DatabaseError databaseError) {

        }
        });







    }
}