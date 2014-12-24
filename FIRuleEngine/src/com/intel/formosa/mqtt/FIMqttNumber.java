package com.intel.formosa.mqtt;

import com.intel.formosa.FIMessage;
import com.intel.formosa.params.FIParams;

public class FIMqttNumber extends FIMqttSource {
	
	private final float mNumber;

	public FIMqttNumber(String uri, String name, String source, FIParams params) {
		super(uri, name, source, params);
		
		mNumber = params.getParameter("constant", 0.0f);
	}

	@Override
	public void sink(FIMessage unused) {		
		publish(mNumber);
	}

}
