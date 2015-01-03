package com.intel.formosa.mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;

import com.intel.formosa.FIMessage;
import com.intel.formosa.params.FIParams;

/**
*
* @author Shao-Wen Yang <shao-wen.yang@intel.com>
*
*/
public class FIMqttACSensor extends FIMqttSource {
	
	private final String mACSource;

	public FIMqttACSensor(String uri, String name, FIParams params, String source) {
		super(uri, name, params, source);
		
		mACSource = params.getParameter("ameliacreek", "");
	}

	@Override
	public void start() {
		super.start();
		
		try {
			if (mMqttClient != null) {	
		        if(!mACSource.isEmpty()) {
		        	mMqttClient.subscribe(mACSource);
		        }
			}
		} catch (MqttException e) {
			
		}
	}

	@Override
	public void stop() {   
		super.stop();
		
		try {
			if (mMqttClient != null) {  	        
		        if(!mACSource.isEmpty()) {
		        	mMqttClient.unsubscribe(mACSource);
		        }
			}
		} catch (MqttException e) {

		}
	}

	@Override
	public void onFIMessageArrived(FIMessage message) {	
		if (mACSource.equals(message.id)) {
			sink(message.value(0.0));
		} else {
			super.onFIMessageArrived(message);
		}
	}

	@Override
	public <T extends Number> void sink(T number) {
		publish(number);		
	}

}
