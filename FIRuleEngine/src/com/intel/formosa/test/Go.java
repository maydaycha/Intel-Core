package com.intel.formosa.test;

import com.intel.formosa.mqtt.FIMqttACActuator;
import com.intel.formosa.mqtt.FIMqttACSensor;
import com.intel.formosa.mqtt.FIMqttLessThanOperator;
import com.intel.formosa.mqtt.FIMqttLooper;
import com.intel.formosa.mqtt.FIMqttNumber;
import com.intel.formosa.mqtt.FIMqttObject;
import com.intel.formosa.params.FIConfigParams;


public class Go implements Runnable {

	
	FIMqttLooper looper;
	
	FIMqttObject lightSensor;
	FIMqttObject number;
	FIMqttObject lessThanOperator;
	FIMqttObject powerSwitch;
	/*
	public Go(String sessionId) {
		FIConfigParams parameter = new FIConfigParams(); 
		looper = new FIMqttLooper("tcp://192.168.184.129:1883", "/formosa/1/Looper/", parameter.setParameter("", ""), "/Gateway1/Illuminance/Illuminance2");
		lightSensor = new FIMqttLightSensor("tcp://192.168.184.129:1883", "/formosa/1/Illuminance/", parameter.setParameter("ameliacreek", "/Gateway1/Illuminance/Illuminance"), "/formosa/1/Looper/");
		number = new FIMqttNumber("tcp://192.168.184.129:1883", "/formosa/1/Number/", "/formosa/1/Looper/", parameter.setParameter("", ""));
		lessThanOperator = new FIMqttLessThanOperator("tcp://192.168.184.129:1883", "/formosa/1/LessThanOperator/", parameter.setParameter("", ""), "/formosa/1/Number/", "/formosa/1/Illuminance/");
		powerSwitch = new FIMqttPowerSwitch("tcp://192.168.184.129:1883", "/formosa/1/PowerSwitch", "/formosa/1/LessThanOperator/", parameter.setParameter("ameliacreek", "/Gateway1/Illuminance/Illuminance2"));
	}
	
	public Go() {
		FIConfigParams parameter = new FIConfigParams(); 
		looper = new FIMqttLooper("tcp://192.168.184.129:1883", "/formosa/1/Looper/", parameter.setParameter("", ""), "/Gateway1/Illuminance/Illuminance2");
		lightSensor = new FIMqttLightSensor("tcp://192.168.184.129:1883", "/formosa/1/Illuminance/", parameter.setParameter("ameliacreek", "/Gateway1/Illuminance/Illuminance"), "/formosa/1/Looper/");
		number = new FIMqttNumber("tcp://192.168.184.129:1883", "/formosa/1/Number/", "/formosa/1/Looper/", parameter.setParameter("", ""));
		lessThanOperator = new FIMqttLessThanOperator("tcp://192.168.184.129:1883", "/formosa/1/LessThanOperator/", parameter.setParameter("", ""), "/formosa/1/Number/", "/formosa/1/Illuminance/");
		powerSwitch = new FIMqttPowerSwitch("tcp://192.168.184.129:1883", "/formosa/1/PowerSwitch", "/formosa/1/LessThanOperator/", parameter.setParameter("ameliacreek", "/Gateway1/Illuminance/Illuminance2"));
	} */
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//FIConfigParams parameter = new FIConfigParams(); 
		looper = new FIMqttLooper(
				"tcp://192.168.184.129:1883",
				"/formosa/1/Looper",
				new FIConfigParams(),
				"/formosa/1/PowerSwitch");
		lightSensor = new FIMqttACSensor(
				"tcp://192.168.184.129:1883",
				"/formosa/1/Illuminance", 
				new FIConfigParams().setParameter("ameliacreek", "/Gateway1/Illuminance/Illuminance"),
				"/formosa/1/Looper");
		number = new FIMqttNumber(
				"tcp://192.168.184.129:1883",
				"/formosa/1/Number",
				new FIConfigParams(), 
				"/formosa/1/Looper");
		lessThanOperator = new FIMqttLessThanOperator(
				"tcp://192.168.184.129:1883",
				"/formosa/1/LessThanOperator",
				new FIConfigParams(),
				"/formosa/1/Number",
				"/formosa/1/Illuminance");
		powerSwitch = new FIMqttACActuator(
				"tcp://192.168.184.129:1883",
				"/formosa/1/PowerSwitch",
				new FIConfigParams().setParameter("ameliacreek", "/Gateway1/OnOff/OnOff"),
				"/formosa/1/LessThanOperator");

	/*	while (true) {
			
			try {
				
				System.out.println("finish");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} */
		looper.start();
		lightSensor.start();
		number.start();
		lessThanOperator.start();
		powerSwitch.start();
		looper.run();
	} 
	
}
