package com.example.hassan.outdoor;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class CheckinLocation extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Marker marker;
    private String json;
    private String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin_location);
        setUpMapIfNeeded();
        Button btnSearch = (Button)findViewById(R.id.search);
        btnSearch.setOnClickListener(onSearch);
        Bundle bundle = getIntent().getExtras(); //modify later
        json = bundle.getString("json");
        status = bundle.getString("status");

        EditText et = (EditText)findViewById(R.id.address);
        
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                Location location = mMap.getMyLocation();
                if (location == null)
                    return true;
                if (marker != null) {
                    marker.remove();
                }
                marker = mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(),
                        location.getLongitude())).title("Location"));
                return true;
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker m) {
                if (marker.equals(m)) {

                    Intent it = new Intent(CheckinLocation.this, Home.class);
                    it.putExtra("lat", marker.getPosition().latitude);
                    it.putExtra("long", marker.getPosition().longitude);
                    it.putExtra("status", status);
                    it.putExtra("jsonObject", json);
                    startActivity(it);
                    finish();
                }
                return true;
            }
        });
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                if (marker != null) {
                    marker.remove();
                }
                marker = mMap.addMarker(new MarkerOptions().position(latLng).title("Location"));
            }
        });

    }

    View.OnClickListener onSearch = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            EditText et = (EditText)findViewById(R.id.address);
            String loc = et.getText().toString();
            if(!loc.equals("")){
                List<Address> ads = null;
                Geocoder geocoder = new Geocoder(CheckinLocation.this);
                try {
                    ads = geocoder.getFromLocationName(loc, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Address address = ads.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                if(marker != null)
                    marker.remove();
                marker = mMap.addMarker(new MarkerOptions().position(latLng).title("Location"));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }

        }

    };
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }



    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        //marker = mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));

        mMap.setMyLocationEnabled(true);
    }
}
