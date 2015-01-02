import com.intel.formosa.mqtt.FIMqttLessThanOperator;
import com.intel.formosa.mqtt.FIMqttLightSensor;
import com.intel.formosa.mqtt.FIMqttLooper;
import com.intel.formosa.mqtt.FIMqttNumber;
import com.intel.formosa.mqtt.FIMqttPowerSwitch;
import com.intel.formosa.params.FIConfigParams;


public class Go implements Runnable {

	
	FIMqttLooper looper;
	FIMqttLightSensor lightSensor;
	FIMqttNumber number;
	FIMqttLessThanOperator lessThanOperator;
	FIMqttPowerSwitch powerSwitch;
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
		
		FIConfigParams parameter = new FIConfigParams(); 
		looper = new FIMqttLooper("tcp://192.168.184.129:1883", "/formosa/1/Looper", parameter.setParameter("", ""), "/Gateway1/Illuminance2/Illuminance2");
		lightSensor = new FIMqttLightSensor("tcp://192.168.184.129:1883", "/formosa/1/Illuminance", parameter.setParameter("ameliacreek", "/Gateway1/Illuminance/Illuminance"), "/formosa/1/Looper");
		number = new FIMqttNumber("tcp://192.168.184.129:1883", "/formosa/1/Number", "/formosa/1/Looper", parameter.setParameter("", ""));
		lessThanOperator = new FIMqttLessThanOperator("tcp://192.168.184.129:1883", "/formosa/1/LessThanOperator", parameter.setParameter("", ""), "/formosa/1/Number", "/formosa/1/Illuminance");
		powerSwitch = new FIMqttPowerSwitch("tcp://192.168.184.129:1883", "/formosa/1/PowerSwitch", "/formosa/1/LessThanOperator", parameter.setParameter("ameliacreek", "/Gateway1/Illuminance2/Illuminance2"));

	/*	while (true) {
			
			try {
				
				System.out.println("finish");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} */
	} 
	
}
