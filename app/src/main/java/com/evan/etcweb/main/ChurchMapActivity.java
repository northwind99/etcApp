package com.evan.etcweb.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.evan.etcweb.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChurchMapActivity extends AppCompatActivity {

    MapView mMapView;
    private GoogleMap googleMap;

    @BindView(R.id.church_map_name)
    TextView church_name;

    @BindView(R.id.church_map_city)
    TextView church_city;

    @BindView(R.id.church_map_info_btn)
    ImageView church_info;

    @BindView(R.id.church_map_nameCity_layout)
    RelativeLayout church_map_city_layout;

    static int churchID;
    String churchName;
    String churchAddress;
    String churchCity;
    String churchPhone;
    String churchFax;
    String churchWebsite;
    String churchEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_church_map);
        ButterKnife.bind(this);
        mMapView = (MapView) findViewById(R.id.mapViewID);
        mMapView.onCreate(savedInstanceState);

        initView();
    }

    private void initView(){
        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();
        // latitude and longitude
//        double latitude = 43.648867;
//        double longitude =  -79.925497;

        double latitude =  SelectCRC.church_name_lat.get(SelectCRC.church_name_selected);
        double longitude =  SelectCRC.church_name_long.get(SelectCRC.church_name_selected);

        Log.v("wtf", latitude +"");
        Log.v("wtf", longitude +"");

        // create marker
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(latitude, longitude)).title("");

        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        //adding marker
        googleMap.addMarker(marker);
        googleMap.addMarker(marker).showInfoWindow();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 14.0f));

        church_name.setText(SelectCRC.churchName);
        church_city.setText(SelectCRC.churchCity);
        church_info.setImageResource(R.drawable.ic_info_outline_black_24dp);

        setTitle(church_name.getText().toString());

        Intent intent=getIntent();
        churchName=intent.getStringExtra("churchName");
        churchAddress=intent.getStringExtra("churchAddress");
        churchCity=intent.getStringExtra("churchCity");
        churchPhone=intent.getStringExtra("churchPhone");
        churchFax=intent.getStringExtra("churchFax");
        churchWebsite=intent.getStringExtra("churchWebsite");
        churchEmail=intent.getStringExtra("churchEmail");
        churchID = intent.getIntExtra("churchID", 0);

        church_map_city_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!church_name.getText().toString().isEmpty()){
                    Intent intent = new Intent(getApplicationContext(), ChurchDetailActivity.class);
                    intent.putExtra("churchName", churchName);
                    intent.putExtra("churchAddress", churchAddress);
                    intent.putExtra("churchCity", churchCity);
                    intent.putExtra("churchPhone", churchPhone);
                    intent.putExtra("churchFax", churchFax);
                    intent.putExtra("churchWebsite",churchWebsite);
                    intent.putExtra("churchEmail", churchEmail);
                    intent.putExtra("churchID", churchID);
                    startActivity(intent);
                }
            }
        });












    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cancel_map, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.map_cancel:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
