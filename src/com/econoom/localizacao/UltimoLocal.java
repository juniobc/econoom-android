package com.econoom.localizacao;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

public class UltimoLocal implements ConnectionCallbacks, OnConnectionFailedListener {

	private Location mLastLocation;
	private GoogleApiClient mGoogleApiClient;
	private final String TAG = "UltimoLocal";
	
	public UltimoLocal(){
		
	}
	
	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onConnectionFailed");
	}

	@Override
	public void onConnected(Bundle arg0) {
		
		mLastLocation = LocationServices.FusedLocationApi.getLastLocation(this.mGoogleApiClient);
		Log.d(TAG, "onConnected");
		
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub
		
	}

	public Location getmLastLocation() {
		return mLastLocation;
	}

	public void setmLastLocation(Location mLastLocation) {
		this.mLastLocation = mLastLocation;
	}

}
