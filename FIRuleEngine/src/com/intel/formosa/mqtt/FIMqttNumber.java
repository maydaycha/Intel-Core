package com.intel.formosa.mqtt;

import com.intel.formosa.params.FIParams;

public class FIMqttNumber extends FIMqttSource {
	
	private final float mNumber;

	public FIMqttNumber(String uri, String name, String source, FIParams params) {
		super(uri, name, params, source);
		
		mNumber = params.getParameter("constant", 0.0f);
	}

	@Override
	public <T extends Number> void sink(T unused) {		
		publish(mNumber);
	}

}
