package com.example.ims;

import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
//import android.test.mock.MockPackageManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.google.android.gms.appdatasearch.GetRecentContextCall;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import android.Manifest;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button btnShowLocation;
    Button show;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    private PackageManager MockPackageManager;
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseAuth firebaseAuth;
    String nameddd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        btnShowLocation = (Button) findViewById(R.id.locate);
        show = (Button) findViewById(R.id.button2);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShowMechanic.class);
                view.getContext().startActivity(intent);}

        });

        //DatabaseReference nameddd = FirebaseDatabase.getInstance().getReference("Mechanic").child("uid").child("name");
        firebaseAuth = firebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("MechanicUsr");
        //DatabaseReference reference = mDatabase.getReference().child("Mechanic").child(firebaseAuth.getUid());




        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission) != MockPackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{mPermission}, REQUEST_CODE_PERMISSION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//         Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        // Add markers for all the mechanics

        // Toast "message"

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }





        mMap.setMyLocationEnabled(true);
        final GPSTracker gps = new GPSTracker(MapsActivity.this);

        if (gps.canGetLocation()) {
            LatLng latlng = new LatLng(gps.getLatitude(), gps.getLongitude());
//            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
//                    + gps.getLatitude() + "\nLong: " + gps.getLongitude(), Toast.LENGTH_LONG).show();
            mMap.addMarker(new MarkerOptions().position(latlng).title("You are here!"));
            float zoomLevel = (float) 7;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,zoomLevel));
        } else {
            gps.showSettingsAlert();
        }


        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!gps.canGetLocation()) {
                    Toast.makeText(getApplicationContext(), "Cannot get GPS location.", Toast.LENGTH_LONG).show();
                    return;
                }

                // Notify loading..
                Toast.makeText(getApplicationContext(), "Fetching mechanics nearby..", Toast.LENGTH_SHORT).show();
                mDatabaseReference.addChildEventListener(new ChildEventListener(){
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        LatLng newLocation = new LatLng(dataSnapshot.child("latitude").getValue(Double.class),dataSnapshot.child("longitude").getValue(Double.class));
                        nameddd=dataSnapshot.child("name").getValue().toString();
                        mMap.addMarker(new MarkerOptions().position(newLocation).title(nameddd));
                        Toast.makeText(getApplicationContext(), "Fetched successfully..", Toast.LENGTH_SHORT).show();
                }



                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @androidx.annotation.Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @androidx.annotation.Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}

                    });

            }

        });



    }
}
