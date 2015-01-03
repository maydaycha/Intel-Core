package com.intel.formosa.mqtt;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.intel.formosa.FIMessage;
import com.intel.formosa.FISource;
import com.intel.formosa.params.FIParams;

/**
*
* @author Shao-Wen Yang <shao-wen.yang@intel.com>
*
*/

public abstract class FIMqttSource extends FIMqttObject implements FISource {	
	
	protected final String mSource;
	protected final String ACSource;
	protected boolean flag = false;
	FIMessage  ACmessage = null;
	
	
	public FIMqttSource(String uri, String name, FIParams params, String source) {
		
		super(uri, name, params);
		mSource = source;
		ACSource = params.getParameter("ameliacreek", "");

	}

	@Override
	public void start() {
		try {
			if (mMqttClient != null) {
				
		        mMqttClient.subscribe(mSource);	
		        
		        if(!ACSource.isEmpty())
		        mMqttClient.subscribe(ACSource);
		       
			}
		} catch (MqttException e) {
			
		}
	}

	@Override
	public void stop() {       
		try {
			if (mMqttClient != null) {       
		        mMqttClient.unsubscribe(mSource);
		        
		        if(!ACSource.isEmpty())
		        mMqttClient.unsubscribe(ACSource);
			}
		} catch (MqttException e) {

		}
	}

	@Override
	public void onFIMessageArrived(FIMessage message) {
		
		if (mSource.equals(message.id)) { 

			sink(message.value(0.0f));
		}
		
		if (ACSource.equals(message.id)) {  
		
			ACmessage = message;
			sink(message.value(0.0f));
		}
	}
	
}
