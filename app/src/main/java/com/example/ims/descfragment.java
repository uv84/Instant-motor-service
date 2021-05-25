  package com.example.ims;

 import android.app.Activity;
 import android.content.Intent;
 import android.net.Uri;
 import android.os.Bundle;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.fragment.app.Fragment;

 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.Button;
 import android.widget.ImageView;
 import android.widget.TextView;

 import com.bumptech.glide.Glide;
 import com.google.firebase.auth.FirebaseAuth;
 import com.google.firebase.database.DataSnapshot;
 import com.google.firebase.database.DatabaseError;
 import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;
 import com.google.firebase.database.ValueEventListener;

 public class descfragment extends Fragment {

     private static final String ARG_PARAM1 = "param1";
     private static final String ARG_PARAM2 = "param2";

     DatabaseReference mdatabase;

     String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();


     private String mParam1;
     private String mParam2;
     String name, Work, Email, pimage,Uvvid,mecid,contact,phone;
     Button button,call;
     public descfragment() {

     }



     public descfragment(String Uid, String name, String contact, String Work, String Email, String pimage, Double latitude, Double longitude) {
         this.Uvvid=Uid;
         this.name=name;
         this.Work=Work;
         this.Email=Email;
         this.pimage=pimage;
         this.contact=contact;
     }

     //public descfragment(String Uid, String name, String work, String Email,  String getpimage) {
     //}

     public static descfragment newInstance(String param1, String param2) {
         descfragment fragment = new descfragment();
         Bundle args = new Bundle();
         args.putString(ARG_PARAM1, param1);
         args.putString(ARG_PARAM2, param2);
         fragment.setArguments(args);
         return fragment;
     }

     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         if (getArguments() != null) {
             mParam1 = getArguments().getString(ARG_PARAM1);
             mParam2 = getArguments().getString(ARG_PARAM2);
         }
     }

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

         View view=inflater.inflate(R.layout.fragment_descfragment, container, false);

         ImageView imageholder=view.findViewById(R.id.imagegholder);
         TextView nameholder=view.findViewById(R.id.nameholder);
         TextView courseholder=view.findViewById(R.id.courseholder);
         TextView emailholder=view.findViewById(R.id.emailholder);
         call = view.findViewById(R.id.callbutton);
         button = view.findViewById(R.id.button4);
         button.setOnClickListener(v -> MechanicNewActivity());
         call.setOnClickListener(v -> CallNewActivity());

         nameholder.setText(name);
         courseholder.setText(Work);
         emailholder.setText(Email);
         Glide.with(getContext()).load(pimage).into(imageholder);
         phone=contact;




         return  view;
     }



     private void MechanicNewActivity() {
         //Intent i = new Intent(getActivity(), Booking.class);
         //i.putExtra("keyname",mecid);
         //startActivity(i);
         //((Activity) getActivity()).overridePendingTransition(0, 0);


         FirebaseDatabase db=FirebaseDatabase.getInstance();
         DatabaseReference root=db.getReference("Booking");
         //bookholder obj=new bookholder(name.getText().toString(),contact.getText().toString(),userId.getText().toString(), latitude.doubleValue(),longitude.doubleValue());
         //root.child(keyid).setValue(obj);

         String msg1= "Fix My Vehicle";
         String UID= userId;
         // tvName.setText(userId);
         mdatabase = FirebaseDatabase.getInstance().getReference().child("Users");
         mdatabase.child(UID).addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 String name1 = dataSnapshot.getValue(User.class).getdisplayname();
                 String userphone = dataSnapshot.getValue(User.class).getcontact();



                 GPSTracker gps = new GPSTracker(getActivity());
                 double latitude1 = gps.getLatitude();
                 double longitude1 = gps.getLongitude();
                 Double latitude = latitude1;
                 Double longitude = longitude1;
                 bookholder obj=new bookholder(name1,phone,userphone,userId,msg1, latitude.doubleValue(),longitude.doubleValue());
                 root.child(phone).setValue(obj);
                 Intent it= new Intent(getActivity(),MechanicReach.class);
                 it.putExtra("phone",phone);
                 startActivity(it);

             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });



     }

     private void CallNewActivity() {

         Intent callIntent = new Intent(Intent.ACTION_DIAL );
         callIntent.setData(Uri.parse("tel:"+ phone));
         startActivity(callIntent);


     }

     public void onBackPressed()
     {
         AppCompatActivity activity=(AppCompatActivity)getContext();
         activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new recfragment()).addToBackStack(null).commit();

     }
 }