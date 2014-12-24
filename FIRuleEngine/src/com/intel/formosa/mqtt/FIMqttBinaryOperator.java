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
public abstract class FIMqttBinaryOperator extends FIMqttObject implements FIOperator {
	
	protected final String mLhs;
	protected final String mRhs;
	
	public FIMqttBinaryOperator(String uri, String name, String lhs, String rhs, FIParams params) {
		super(uri, name, params);
		
		mLhs = lhs;
		mRhs = rhs;
	}

	@Override
	public void start() {
		try {
			if (mMqttClient != null) {
		        mMqttClient.subscribe(mLhs);	        
		        mMqttClient.subscribe(mRhs);
			}
		} catch (MqttException e) {
			
		}
	}

	@Override
	public void stop() {        
		try {
			if (mMqttClient != null) {       
		        mMqttClient.unsubscribe(mLhs);	        
		        mMqttClient.unsubscribe(mRhs);
			}
		} catch (MqttException e) {

		}		
	}

	@Override
	public void onFIMessageArrived(FIMessage message) {		
		// TODO: Develop FSM to determine whether or not all parameters are received.
		
		// TODO: Replace the following placeholder.
		
		run(message, message);
	}
	
}
