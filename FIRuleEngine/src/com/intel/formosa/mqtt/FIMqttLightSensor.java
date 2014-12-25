package com.intel.formosa.mqtt;

import com.intel.formosa.params.FIParams;

/**
*
* @author Shao-Wen Yang <shao-wen.yang@intel.com>
*
*/
public class FIMqttLightSensor extends FIMqttSource {

	public FIMqttLightSensor(String uri, String name, FIParams params, String source) {
		super(uri, name, params, source);
		
		// TODO: Use parameters to configure light sensor
		params.getParameter("ameliacreek", "");
	}

	@Override
	public <T extends Number> void sink(T unused) {		
		// TODO: Read in light sensor reading here.

    	// TODO: Construct the message and send with publish().
		
		publish(12345);
	}

}
