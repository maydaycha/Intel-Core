package com.intel.formosa.mqtt;

import java.util.Timer;
import java.util.TimerTask;

import com.intel.formosa.FIMessage;
import com.intel.formosa.params.FIParams;
import com.intel.formosa.test.parameters;

/**
*
* @author Shao-Wen Yang <shao-wen.yang@intel.com>
*
*/
public class FIMqttLooper extends FIMqttOperator {
	
	Boolean alive = true;
	Timer timer = new Timer();
		
	public FIMqttLooper(String uri, String name, FIParams params, String ... sources) {
		super(uri, name, params, sources);
	}
	
	@Override
	public <T extends Number> void run(T ... unused) {
		
		publish();
		timer.cancel();
		timer = new Timer();
		parameters.five_s_alive = true;
		timer.schedule(new SayHello(), 5000, 100000);
	}
	
	class SayHello extends TimerTask {
	    public void run() {
	    	parameters.five_s_alive = false;
	    }
	 }
}