package com.intel.formosa.mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;

import com.intel.formosa.FIMessage;
import com.intel.formosa.FIOperator;
import com.intel.formosa.params.FIParams;

/**
*
* @author Shao-Wen Yang <shao-wen.yang@intel.com>
*
*/
public abstract class FIMqttOperator extends FIMqttObject implements FIOperator {
	
	protected final String[] mSources;
	protected final String[] mSources_get;
	int sum = 0;
	
	public FIMqttOperator(String uri, String name, FIParams params, String ... sources) {
		super(uri, name, params);
		
		mSources = new String[sources.length];
		mSources_get = new String[sources.length];
		
		
		for (int j = 0; j < mSources.length; ++j) {
			mSources_get[j] = "0";
		}
		

		System.arraycopy(sources, 0, mSources, 0, sources.length);
	}

	@Override
	public void start() {
		try {
			if (mMqttClient != null) {
		        for (String source : mSources) {
		        	mMqttClient.subscribe(source);
		        }
			}
		} catch (MqttException e) {
			
		}
	}

	@Override
	public void stop() {        
		try {
			if (mMqttClient != null) {   
		        for (String source : mSources) {
		        	mMqttClient.unsubscribe(source);
		        }
			}
		} catch (MqttException e) {

		}		
	}

	@Override
	public void onFIMessageArrived(FIMessage message) {		
		// TODO: Develop FSM to determine whether or not all parameters are received.
		
		
		for (int i = 0; i < mSources.length; ++i) {
	        
			if(message.id == mSources[i])
				mSources_get[i] = "1";	        
		
	    }
		
		// TODO: Replace the following placeholder.
		
		for (int i = 0; i < mSources.length; ++i) {
			sum = Integer.parseInt(mSources_get[i]);
		}	
		
		
		if(sum == mSources.length){
			
			float[] f = new float[sum];
			
			for (int i = 0; i < mSources.length; ++i) {
				
				f[i] = Integer.parseInt(mSources_get[i]);
				run(f[i]);
				
			}				
		}		
		
		sum =0;
	}

		
		
	

}
