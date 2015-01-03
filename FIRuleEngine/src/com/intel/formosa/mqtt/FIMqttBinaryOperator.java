package com.intel.formosa.mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

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
	boolean flag_lhs = false;
	boolean flag_rhs = false;
	int state = 0;  // 0 or 1 or 2
	Float lhs_v = 0.0f;
	Float rhs_v = 0.0f;

	
	public FIMqttBinaryOperator(String uri, String name, FIParams params, String lhs, String rhs) {
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
		
		if (message != null && state == 0) {
			if(message.id.equals(mLhs)){

				state = 1;
			    lhs_v = Float.parseFloat(message.toString());
			}
			
			if(message.id.equals(mRhs)){

				state = 2;
				rhs_v = Float.parseFloat(message.toString());
			}
		}
		
		if (message != null && state == 1) {
			if(message.id.equals(mLhs)){

				state = 1;
			    lhs_v = Float.parseFloat(message.toString());
			}
			
			if(message.id.equals(mRhs)){
				
				state = 0;
				rhs_v = Float.parseFloat(message.toString());
				run(lhs_v, rhs_v);
			}
		}
		
		if (message != null && state == 2) {
			
			if(message.id.equals(mLhs)){

				state = 0;
			    lhs_v = Float.parseFloat(message.toString());
			    run(lhs_v, rhs_v);
			}
			
			if(message.id.equals(mRhs)){
				
				state = 2;
				rhs_v = Float.parseFloat(message.toString());
			}	
		}
	}
}
