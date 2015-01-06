package com.intel.formosa.test;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.intel.formosa.mqtt.FIMqttACActuator;
import com.intel.formosa.mqtt.FIMqttACSensor;
import com.intel.formosa.mqtt.FIMqttLessThanOperator;
import com.intel.formosa.mqtt.FIMqttLooper;
import com.intel.formosa.mqtt.FIMqttNumber;
import com.intel.formosa.mqtt.FIMqttObject;
import com.intel.formosa.params.FIConfigParams;


public class Go implements Runnable {

	
	FIMqttLooper looper;
	
	FIMqttObject Illuminance;
	FIMqttObject number;
	FIMqttObject lessThanOperator;
	FIMqttObject powerSwitch;
	JSONArray jsonarray;
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
	
	
	
	public Go(JSONArray jsonarray) {
		// TODO Auto-generated constructor stub
		this.jsonarray = jsonarray;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(jsonarray != null){
		//FIConfigParams parameter = new FIConfigParams(); 
			
			for (Object o : jsonarray)
			  {
				
					JSONObject object = (JSONObject) o;
					JSONArray a = (JSONArray) object.get("wire");
			    
					switch(object.get("type").toString()){
					
					 
						case ("IlluminanceMeasurement_S"):
							
							
							
							Illuminance = new FIMqttACSensor(
									"tcp://192.168.184.129:1883",
									"/formosa/"+object.get("z")+"/"+object.get("id"), 
									new FIConfigParams().setParameter("ameliacreek", object.get("deviceName")),
									"/formosa/"+object.get("z")+"/Looper");
						Illuminance.start();
						
						case ("Meter_S"):
							
							powerSwitch = new FIMqttACActuator(
									"tcp://192.168.184.129:1883",
									"/formosa/"+object.get("z")+object.get("id"),
									new FIConfigParams().setParameter("ameliacreek", object.get("deviceName")),
									"/formosa/"+object.get("z")+"/"+a.get(0));
							powerSwitch.start();
							
							looper = new FIMqttLooper(
									"tcp://192.168.184.129:1883",
									"/formosa/"+object.get("z")+"/Looper",
									new FIConfigParams(),
									"/formosa/"+object.get("z")+object.get("id"));
							looper.start();
							
						case ("LessThan"):
							
							lessThanOperator = new FIMqttLessThanOperator(
									"tcp://192.168.184.129:1883",
									"/formosa/"+object.get("z")+"/"+object.get("id"),
									new FIConfigParams(),
									"/formosa/"+object.get("z")+"/"+a.get(0),
									"/formosa/"+object.get("z")+"/"+a.get(1));
							lessThanOperator.start();
							
							
						case("Number"):
							
							number = new FIMqttNumber(
									"tcp://192.168.184.129:1883",
									"/formosa/"+object.get("z")+"/"+object.get("id"),
									new FIConfigParams().setParameter("constant", object.get("value")), 
									"/formosa/"+object.get("z")+"/Looper");
							number.start();
					} 			
			  }
			  
			
			
	/*		looper = new FIMqttLooper(
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
				new FIConfigParams().setParameter("constant", 1), 
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

		while (true) {
			
			try {
				
				System.out.println("finish");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} */
	//	looper.start();
	//	Illuminance.start();
	//	number.start();
	//	lessThanOperator.start();
	//	powerSwitch.start();
		looper.run();
		} 
	}
	
}
