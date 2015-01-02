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
public class FIMqttLightSensor extends FIMqttSource {
	
    
	public FIMqttLightSensor(String uri, String name, FIParams params, String source) {
		super(uri, name, params, source);

		// TODO: Use parameters to configure light sensor

		start(); 

	}

	@Override
	public <T extends Number> void sink(T number) {		
		
		// TODO: Read in light sensor reading here.
			if(ACmessage != null)
			{
		/*		// TODO: Construct the message and send with publish().
		 * 		
				ByteBuffer b = ByteBuffer.allocateDirect(4);
				b.putInt((int)number);
				// TODO: Control the light switch accordingly.
				
				System.out.println("OOXX : " + b.array()); */
				System.out.println("sensor reading : " + ACmessage);
				publish(ACmessage);
				
			}
	}

}
