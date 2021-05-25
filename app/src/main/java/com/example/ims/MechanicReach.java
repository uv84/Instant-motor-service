package com.example.ims;

import android.Manifest;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.widget.Button;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MechanicReach extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;



    private Button reach;

    DatabaseReference mdatabase;
    private String userId;
    private DatabaseReference ud;


    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    private PackageManager MockPackageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_reach);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        ud = FirebaseDatabase.getInstance().getReference().child("UserDriver");
        userId= FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission) != MockPackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{mPermission}, REQUEST_CODE_PERMISSION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        reach = (Button) findViewById(R.id.btn);
        reach.setOnClickListener(v -> reachDestination());




    }

    private void reachDestination() {
        String mecid= getIntent().getStringExtra("phone");
        Intent intent= new Intent (this,WorkDone.class);
        intent.putExtra("phone",mecid);
        startActivity((intent));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
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

        mMap = googleMap;
        String mecid= getIntent().getStringExtra("phone");
        mdatabase = FirebaseDatabase.getInstance().getReference("MechanicUsr");
        mdatabase.child(mecid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double lat = dataSnapshot.getValue(bookholder.class).getLatitude();
                Double lon = dataSnapshot.getValue(bookholder.class).getLongitude();
                LatLng latlng = new LatLng(lat, lon);
                mMap.addMarker(new MarkerOptions().position(latlng).title("Mechanic Location"));
                float zoomLevel = (float) 7;
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,zoomLevel));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        mMap.setMyLocationEnabled(true);
        final GPSTracker gps = new GPSTracker(MechanicReach.this);

        if (gps.canGetLocation()) {
            LatLng latlng = new LatLng(gps.getLatitude(), gps.getLongitude());
//
            mMap.addMarker(new MarkerOptions().position(latlng).title("You are here!"));
            float zoomLevel = (float) 7;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,zoomLevel));
        } else {
            gps.showSettingsAlert();
        }

    }








}

