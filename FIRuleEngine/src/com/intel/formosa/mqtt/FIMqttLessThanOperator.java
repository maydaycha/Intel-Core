package com.intel.formosa.mqtt;

import org.eclipse.paho.client.mqttv3.MqttMessage;




import com.intel.formosa.FIMessage;
import com.intel.formosa.params.FIParams;

/**
*
* @author Shao-Wen Yang <shao-wen.yang@intel.com>
*
*/
public class FIMqttLessThanOperator extends FIMqttBinaryOperator {

	
	
	
	public FIMqttLessThanOperator(String uri, String name, FIParams params, String lhs, String rhs) {
		super(uri, name, params, lhs, rhs);
		start();
		
	}

	@Override
	public <T extends Number> void run(T ... numbers) {
		assert numbers.length >= 2;
		
		float output = 0;
			// TODO: Implement less than logic rule.
			if(lhs_v < rhs_v){
				output  = 1;				
			}
			else
				output = 0;
		// TODO: Replace the following placeholder.
		
    	publish(output);
	}

}
