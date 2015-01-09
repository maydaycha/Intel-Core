package com.intel.formosa.mqtt;

import com.intel.formosa.FIMessage;
import com.intel.formosa.params.FIParams;
import com.intel.formosa.test.parameters;

/**
*
* @author Shao-Wen Yang <shao-wen.yang@intel.com>
*
*/
public class FIMqttACActuator extends FIMqttSink {

	private final String mACSink;
	private Double output = 0.0;
	
	public FIMqttACActuator(String uri, String name, FIParams params, String source) {
		super(uri, name, params, source);
		
		mACSink = params.getParameter("ameliacreek", "");
	}

	@Override
	public <T extends Number> void source(T number) {

		if(parameters.Alarm){

			if(output.equals(number)){
				output = (Double) number;
			}
			else{
				publish(new FIMessage(mACSink, number));
				output = (Double)number;
			}
		}
		else
		publish(new FIMessage(mACSink, number));
	}

}
