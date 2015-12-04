package com.econoom;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.econoom.localizacao.UltimoLocal;

public class MapaEndereco extends FragmentActivity implements OnMapReadyCallback, OnMapLongClickListener {
	
	private GoogleMap map;
	private UltimoLocal ultimoLocal;
	private GoogleApiClient mGoogleApiClient;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);    
        
        ultimoLocal = new UltimoLocal();
        
        buildGoogleApiClient();
                
    }
	
	protected synchronized void buildGoogleApiClient() {
	    mGoogleApiClient = new GoogleApiClient.Builder(this)
	        .addConnectionCallbacks(ultimoLocal)
	        .addOnConnectionFailedListener(ultimoLocal)
	        .addApi(LocationServices.API)
	        .build();
	}

    @Override
    public void onMapReady(GoogleMap map) {
    	
    	this.map = map;
    	
    	this.map.setOnMapLongClickListener(this);
    	
    	this.map.setMyLocationEnabled(true);
        //LatLng ultimolocal = new LatLng(local.getmLastLocation().getLatitude(), local.getmLastLocation().getLongitude());
        //this.map.moveCamera(CameraUpdateFactory.newLatLngZoom(ultimolocal,10));
    }

	@Override
	public void onMapLongClick(LatLng local) {
		
		this.map.addMarker(new MarkerOptions()
        .position(local)
        .title("Hello world"));
		
	}

}
