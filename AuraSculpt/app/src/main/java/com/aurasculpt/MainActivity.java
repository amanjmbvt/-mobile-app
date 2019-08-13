package com.aurasculpt;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    SupportMapFragment mapFragment;
    TextView txtUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); // get the reference of Toolbar
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false); // hide the current title from the Toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        txtUrl= (TextView)findViewById(R.id.txt_site_url);
        txtUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent("android.intent.action.VIEW");
//                i.setData(Uri.parse("Www.aurasculpt.com"));
//                MainActivity.this.startActivity(i);
                Uri uri = Uri.parse("http://www.aurasculpt.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
//        toolbar.setLogo(R.mipmap.ic_launcher_round); // setting a logo in toolbar
//        toolbar.setTitle("Entranzz");
//        toolbar.setTitleTextColor(Color.WHITE);
//        ((TextView)toolbar.getChildAt(0)).setTextSize(20);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mMap.clear(); //clear old markers
                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(40.793307, -73.691856))
                        .zoom(10)
                        .bearing(0)
                        .tilt(45)
                        .build();
                mMap.addMarker(new MarkerOptions().position(new LatLng(40.793307, -73.691856)).title(getResources().getString(R.string.address)));
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);

            }
        });
    }
}
