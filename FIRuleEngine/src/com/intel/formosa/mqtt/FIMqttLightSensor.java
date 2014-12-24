package com.intel.formosa.mqtt;

import com.intel.formosa.FIMessage;
import com.intel.formosa.params.FIParams;

/**
*
* @author Shao-Wen Yang <shao-wen.yang@intel.com>
*
*/
public class FIMqttLightSensor extends FIMqttSource {

	public FIMqttLightSensor(String uri, String name, String source, FIParams params) {
		super(uri, name, source, params);
		
		// TODO: Use parameters to configure light sensor
		params.getParameter("ac_topic", "");
	}

	@Override
	public void sink(FIMessage unused) {		
		// TODO: Read in light sensor reading here.

    	// TODO: Construct the message and send with publish().
		
		publish(12345);
	}

}
