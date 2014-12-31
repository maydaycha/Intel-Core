package com.intel.formosa.mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;

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
	
	public FIMqttSource(String uri, String name, FIParams params, String source) {
		super(uri, name, params);
		
		mSource = source;
		ACSource = params.getParameter("ameliacreek", "");
		
	}

	@Override
	public void start() {
		try {
			if (mMqttClient != null) {
				System.out.println("subscribe");
		        mMqttClient.subscribe(mSource);	 
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
		        mMqttClient.unsubscribe(ACSource);
			}
		} catch (MqttException e) {

		}
	}

	@Override
	public void onFIMessageArrived(FIMessage message) {
		System.out.println("onFIMessageArrived");
		
		if (mSource.equals(message.id)) {
			flag = true;
		}
		
		if (ACSource.equals(message.id)) {
			
			if(flag){
			sink(message.value(0.0f));
			flag = false;
			}
			
			System.out.println("ID : " + message.id);
			System.out.println("Content : " + message.payload);
		}
	}
	
}
