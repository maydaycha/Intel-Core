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
	Integer lhs_v = 0;
	Integer rhs_v = 0;

	
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
			
		System.out.println("BB : " + mLhs);
		System.out.println("DD : " + mRhs);
		System.out.println("message.id : " + message.id);
		
		if (message != null) {
			
			System.out.println("mLhs = "+message.id.equals(mLhs));
			System.out.println("mRhs = "+message.id.equals(mRhs));

			
			if(message.id.equals(mLhs)){
				flag_lhs = true;
			    lhs_v = Integer.valueOf(message.toString());
			    System.out.println("bb : " + lhs_v);
			}
			
			if(message.id.equals(mRhs)){
				flag_rhs = true;
				rhs_v = Integer.valueOf(message.toString());
				System.out.println("dd : " + rhs_v);
			}
			
			if(flag_lhs && flag_rhs)
				run(lhs_v, rhs_v);
		
		// TODO: Replace the following placeholder.
		}
		

	}
	
}
