package com.intel.formosa.mqtt;

import java.nio.ByteBuffer;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.intel.formosa.FIMessage;
import com.intel.formosa.params.FIParams;

/**
*
* @author Shao-Wen Yang <shao-wen.yang@intel.com>
*
*/
public class FIMqttPowerSwitch extends FIMqttSink {
	
	
	int i = 1;
	String topic =  null;
	
	
	public FIMqttPowerSwitch(String uri, String name, String source, FIParams params) {
		super(uri, name, params, source);
		
		// TODO: Use parameters to configure light switch
		topic = params.getParameter("ameliacreek", "");
	}

	@Override
	public <T extends Number> void source(T number) {	
		
		// TODO: Read in light switch control.
		// TODO: Control the light switch accordingly.
		FIMessage message = new FIMessage(topic, number);
		publish(message);
		
		System.out.println("Round["+i+"] done");
		i++;
	}

}
