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
	protected Boolean[] mSources_get;
	int sum = 0;
	String Name = null;
	
	public FIMqttOperator(String uri, String name, FIParams params, String ... sources) {
		super(uri, name, params);
		
		Name = name;

		mSources = new String[sources.length];
		mSources_get = new Boolean[sources.length];
		
		for (int j = 0; j < mSources.length; ++j) {
			mSources_get[j] = false;
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
		// TODO: Replace the following placeholder.
		
		for (int i = 0; i < mSources.length; ++i) {
	        
			if(message.id.equals(mSources[i]))
				mSources_get[i] = true;	     
			
			if(mSources_get[i])
				sum = sum + 1;
	    }
		
		if(sum == mSources.length){
				run();			
				sum = 0;
		}
	}

}
