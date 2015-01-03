package com.intel.formosa.mqtt;

import com.intel.formosa.params.FIParams;

public class FIMqttNumber extends FIMqttSource {
	
	private final float mNumber;

	public FIMqttNumber(String uri, String name,  FIParams params, String source) {
		super(uri, name, params, source);
		
		mNumber = params.getParameter("constant", 2);
		
	}

	@Override
	public <T extends Number> void sink(T unused) {	
		
		publish(mNumber);
	}

}
