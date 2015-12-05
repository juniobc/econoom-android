package com.econoom;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.econoom.localizacao.UltimoLocal;

public class MapaEndereco extends FragmentActivity implements OnMapReadyCallback, OnMapLongClickListener{
	
	private static GoogleMap map;
	private UltimoLocal ultimoLocal;
	private static final String TAG = "MapaEndereco";
	private Marker marker;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Log.d(TAG, "onCreate");
        
        setContentView(R.layout.maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);    
        
        ultimoLocal = new UltimoLocal(this);
                
    }	
	

    @Override
    public void onMapReady(GoogleMap gMap) {
    	
    	Log.d(TAG, "onMapReady");
    	
    	map = gMap;
    	
    	map.setOnMapLongClickListener(this);
    	
    	map.setMyLocationEnabled(true);
    	
    	ultimoLocal.mGoogleApiClient.connect();    	
        
    }

	@Override
	public void onMapLongClick(LatLng local) {
		
		Log.d(TAG, "onMapLongClick");
		
		if(marker != null)
			marker.remove();
		
		marker = map.addMarker(new MarkerOptions().position(local));
		
	}

	public static void setMapaLocal(Location location) {
		
		Log.d(TAG, "onMapLoaded");

		LatLng ultimolocal = new LatLng(location.getLatitude(), location.getLongitude());
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ultimolocal,15));
		
	}

}
