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
		
	}

	@Override
	public <T extends Number> void run(T ... numbers) {
		assert numbers.length >= 2;
		// TODO: Replace the following placeholder.
		// TODO: Implement less than logic rule.
		
		int output = 0;
			
		if((float)lhs_v < (float)rhs_v)
				output  = 1;				
		else
				output = 0;
		
	//	System.out.println("(float)lhs_v = " + (float)lhs_v);
	//	System.out.println("(float)rhs_v = " + (float)rhs_v);
	
    	publish(output);
	}
}
