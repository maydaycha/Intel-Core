package com.intel.formosa.mqtt;

import com.intel.formosa.params.FIParams;

/**
*
* @author Shao-Wen Yang <shao-wen.yang@intel.com>
*
*/
public class FIMqttPowerSwitch extends FIMqttSink {

	public FIMqttPowerSwitch(String uri, String name, String source, FIParams params) {
		super(uri, name, params, source);
		
		// TODO: Use parameters to configure light switch
	}

	@Override
	public <T extends Number> void source(T number) {		
		// TODO: Read in light switch control.
		
		// TODO: Control the light switch accordingly.	
	}

}
