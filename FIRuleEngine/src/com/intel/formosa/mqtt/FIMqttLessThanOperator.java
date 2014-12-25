package com.intel.formosa.mqtt;

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
		
		// TODO: Develop FSM to determine whether or not all parameters are received.
		
		// TODO: Implement less than logic rule.

		// TODO: Replace the following placeholder.
		
    	publish(1);
	}

}
