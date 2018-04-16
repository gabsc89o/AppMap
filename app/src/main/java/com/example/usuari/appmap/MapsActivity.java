package com.example.usuari.appmap;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    public EditText latitud,longitud,nombre;
    //public EditText longitud;
    public double lat,longi,flag=0;
    private GoogleMap mMap;
    public Marker mark;
    public MarkerOptions mo=new MarkerOptions();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        nombre = this.findViewById(R.id.edtNombre);
        latitud = edtLatitud();
        longitud= edtLongitud();
            latitud.setText("0");
            longitud.setText("0");

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
        lat =Double.parseDouble(latitud.getText().toString());
        longi =Double.parseDouble(longitud.getText().toString());
        LatLng sydney = new LatLng(lat, longi);
        mark = mMap.addMarker(mo.position(sydney).title("Marker 0:0"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    public void localizar(View v){

        LatLng sydney;
        mark.remove();
        Geocoder geo = new Geocoder(this);
        int maxResultados = 1;
        if (nombre.getText().toString() == "") {
            List<Address> adress = null;
            try {
                adress = geo.getFromLocationName(nombre.getText().toString(), maxResultados);
            } catch (IOException e) {
                e.printStackTrace();
                //nombre.setText("");
            }
            sydney = new LatLng(adress.get(0).getLatitude(), adress.get(0).getLongitude());
        }else{
            lat =Double.parseDouble(latitud.getText().toString());
            longi =Double.parseDouble(longitud.getText().toString());
            sydney = new LatLng(lat, longi);
        }
        mark = mMap.addMarker(mo.position(sydney).title("Marker is here!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMinZoomPreference(6.0f);
        mMap.setMaxZoomPreference(20.0f);
    }
    private EditText edtLatitud(){
        return this.findViewById(R.id.edtLatitud);
    }
    private EditText edtLongitud(){
        return this.findViewById(R.id.edtLong);
    }
}
