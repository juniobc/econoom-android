package com.econoom.localizacao;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.econoom.MapaEndereco;

public final class UltimoLocal implements ConnectionCallbacks, OnConnectionFailedListener {

	private Location mLastLocation;
	public GoogleApiClient mGoogleApiClient;
	private final String TAG = "UltimoLocal";
	private Context contexto;
	
	public UltimoLocal(Context contexto){
		
		this.contexto = contexto;
		
		buildGoogleApiClient(this.contexto);
		
	}
	
	protected synchronized void buildGoogleApiClient(Context contexto) {
			
			Log.d(TAG, "buildGoogleApiClient");
			
		    mGoogleApiClient = new GoogleApiClient.Builder(contexto)
		        .addConnectionCallbacks(this)
		        .addOnConnectionFailedListener(this)
		        .addApi(LocationServices.API)
		        .build();
	}
	
	public void disconectar(){
		
		if(mGoogleApiClient.isConnected())
			mGoogleApiClient.disconnect();
		
	}
	
	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onConnectionFailed");
	}

	@Override
	public void onConnected(Bundle arg0) {
		Log.d(TAG, "onConnected");
		mLastLocation = LocationServices.FusedLocationApi.getLastLocation(this.mGoogleApiClient);
		
		if(mLastLocation != null){
			Log.d(TAG, "latitude:"+mLastLocation.getLatitude());
			Log.d(TAG, "longitude:"+mLastLocation.getLongitude());
			
			MapaEndereco.setMapaLocal(mLastLocation);
		}
		
	}

	@Override
	public void onConnectionSuspended(int arg0) {

		Log.d(TAG, "onConnectionFailed");
		
	}

	public Location getmLastLocation() {
		return mLastLocation;
	}

	public void setmLastLocation(Location mLastLocation) {
		this.mLastLocation = mLastLocation;
	}

}
