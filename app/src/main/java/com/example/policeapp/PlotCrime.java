package com.example.policeapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class PlotCrime extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int COLOR_BLACK_ARGB = 0xff120000;
    private static final int POLYLINE_STROKE_WIDTH_PX = 5;
    ArrayList<MarkerData> markersArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot_crime);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        // Hardcoded data for demonstration
        markersArray.add(new MarkerData("Chain Snatching", 13.0827, 80.2707));
        markersArray.add(new MarkerData("Vandalism", 13.0860, 80.2700));
        markersArray.add(new MarkerData("Pick Pocketing", 13.0800, 80.2750));
        markersArray.add(new MarkerData("Eve Teasing", 13.0840, 80.2720));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        addMarkersAndPolylines();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(13.0827, 80.2707), 13));
    }

    private void addMarkersAndPolylines() {
        for (int i = 0; i < markersArray.size(); i++) {
            mMap.addMarker(createMarker(markersArray.get(i).getLat(),
                    markersArray.get(i).getLon(),
                    markersArray.get(i).getCrime()));

            // Draw polyline between consecutive markers
            if (i != markersArray.size() - 1) {
                Polyline polyline = mMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .add(new LatLng(markersArray.get(i).getLat(), markersArray.get(i).getLon()),
                                new LatLng(markersArray.get(i + 1).getLat(), markersArray.get(i + 1).getLon())));
                polyline.setWidth(POLYLINE_STROKE_WIDTH_PX);
                polyline.setColor(COLOR_BLACK_ARGB);
            }
        }
    }

    protected MarkerOptions createMarker(double latitude, double longitude, String crime) {
        return new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title(crime)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
    }
}
