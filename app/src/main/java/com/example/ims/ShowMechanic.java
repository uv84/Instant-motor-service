 package com.example.ims;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import android.os.Bundle;

 import com.firebase.ui.database.FirebaseRecyclerOptions;
 import com.google.firebase.database.FirebaseDatabase;

 import androidx.appcompat.app.AppCompatActivity;

 import android.os.Bundle;
 import android.view.WindowManager;

 public class ShowMechanic extends AppCompatActivity
 {
     @Override
     protected void onCreate(Bundle savedInstanceState)
     {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_show_mechnic);
         getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

         getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new recfragment()).commit();

     }
 }