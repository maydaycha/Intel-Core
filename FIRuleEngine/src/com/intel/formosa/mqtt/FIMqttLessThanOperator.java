package com.intel.formosa.mqtt;

import com.intel.formosa.FIMessage;
import com.intel.formosa.params.FIParams;

/**
*
* @author Shao-Wen Yang <shao-wen.yang@intel.com>
*
*/
public class FIMqttLessThanOperator extends FIMqttBinaryOperator {

	public FIMqttLessThanOperator(String uri, String name, String lhs, String rhs, FIParams params) {
		super(uri, name, lhs, rhs, params);
	}

	@Override
	public void run(FIMessage ... messages) {		
		assert messages.length >= 2;
		
		// TODO: Develop FSM to determine whether or not all parameters are received.
		
		// TODO: Implement less than logic rule.

		// TODO: Replace the following placeholder.
		
    	publish(1);
	}

}
