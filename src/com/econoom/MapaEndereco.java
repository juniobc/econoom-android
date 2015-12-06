package com.econoom;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.econoom.localizacao.UltimoLocal;

public class MapaEndereco extends FragmentActivity implements OnMapReadyCallback, OnMapLongClickListener{
	
	private static GoogleMap map;
	private UltimoLocal ultimoLocal;
	private static final String TAG = "MapaEndereco";
	private Marker marker;
	public static final String RESPOSTA_LATITUDE = "com.econoom.resposta_latitude";
	public static final String RESPOSTA_LONGITUDE = "com.econoom.resposta_longitude";
	private Button btn_gravar;
	private static LatLng ultimolocal;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Log.d(TAG, "onCreate");
        setContentView(R.layout.maps);
        
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);  
        
        ultimoLocal = new UltimoLocal(this);
        
        btn_gravar = (Button) findViewById(R.id.btn_gravar);
        
        btn_gravar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
					setAnswerShownResult();
				
		}});
                
    }	
	
	private void setAnswerShownResult() {
		
		Log.d(TAG, "setAnswerShownResult");
		
		if(ultimolocal != null){
		
			Intent data = new Intent();
			data.putExtra(RESPOSTA_LATITUDE, ultimolocal.latitude);
			data.putExtra(RESPOSTA_LONGITUDE, ultimolocal.longitude);
			setResult(RESULT_OK, data);
			finish();			
		
		}
		
	}
	

    @Override
    public void onMapReady(GoogleMap gMap) {
    	
    	Log.d(TAG, "onMapReady");
    	
    	map = gMap;
    	
    	map.setOnMapLongClickListener(this);
    	
    	map.setMyLocationEnabled(true);
    	
    	LatLng latLng = new LatLng(getIntent().getDoubleExtra(RESPOSTA_LATITUDE, 0), 
    	getIntent().getDoubleExtra(RESPOSTA_LONGITUDE, 0));
    	
    	if(latLng.latitude != 0 && latLng.longitude != 0)
    		marker = map.addMarker(new MarkerOptions().position(latLng));
    	
    	ultimoLocal.mGoogleApiClient.connect();    	
        
    }

	@Override
	public void onMapLongClick(LatLng local) {
		
		Log.d(TAG, "onMapLongClick");
		
		ultimolocal = local;
		
		if(marker != null)
			marker.remove();
		
		marker = map.addMarker(new MarkerOptions().position(local));
		
	}

	public static void setMapaLocal(Location location) {
		
		Log.d(TAG, "setMapaLocal");

		LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
		
	}

}
