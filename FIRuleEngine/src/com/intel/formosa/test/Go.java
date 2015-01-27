package com.intel.formosa.test;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
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

    FIMqttObject Illuminance = null;
    FIMqttObject Temperature = null;
    FIMqttObject number = null;
    FIMqttObject lessThanOperator = null;
    FIMqttObject lessEqualThanOperator = null;
    FIMqttObject EqualOperator = null;
    FIMqttObject powerSwitch = null;
    FIMqttObject WarningDevice = null;
    JSONArray jsonarray = null;
    Parameters parameters = null;
    String topic = null;
    String broker = "tcp://localhost:1883";

	
    public Go(JSONArray jsonarray) {
        // TODO Auto-generated constructor stub
        this.jsonarray = jsonarray;
        parameters = new Parameters();

    }

    public enum Nodes {
    	ONOFF_S("ONOFF_S"),
        IASWD_S("IASWD_S"),
        LessEqualThan("LessEqualThan"),
        LessThan("LessThan"),
        Equal("Equal"),
        Number("Number"),
        ILLUMINANCEMEASURE_S("ILLUMINANCEMEASURE_S"),
        OccupancySensing_S("OccupancySensing_S"),
        TEMPERATUREMEASURE_S("TEMPERATUREMEASURE_S");

        private String name;

        Nodes(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }

        public static Nodes getByName (final String name) {
            for (Nodes n: Nodes.values()) {
                if (n.name.equalsIgnoreCase(name)) {
                    return n;
                }
            }
            return null;
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        if(jsonarray != null){
            //FIConfigParams parameter = new FIConfigParams();
            for (Object o : jsonarray)
            {
                //System.out.println(o);
                JSONObject object = (JSONObject) o;
                JSONArray a = (JSONArray) object.get("wires");
                String operator = null;
                boolean Alarm = false;

                switch(Nodes.getByName(object.get("deviceType").toString())){

                    case ONOFF_S:

                        System.out.println("case ONOFF_S");
                        Alarm = false;
                        topic = "/formosa/"+(String) object.get("z")+"/finish";

                        powerSwitch = new FIMqttACActuator(
                        		broker,
                                "/formosa/"+object.get("z")+"/"+object.get("id"),
                                new FIConfigParams().setParameter("ameliacreek", "/sub"+object.get("deviceName")+"/"),
                                Alarm,
                                "/formosa/"+object.get("z")+"/"+ a.get(0).toString().substring(2, a.get(0).toString().length()-2));

                        powerSwitch.start();

                        looper = new FIMqttLooper(
                        		broker,
                                "/formosa/"+object.get("z")+"/Looper",
                                new FIConfigParams(),
                                "/formosa/"+object.get("z")+"/"+object.get("id"));
                        looper.start();
                        break;

                    case IASWD_S:

                        System.out.println("case IASWD_S");
                        Alarm = true;
                        topic = "/formosa/"+(String) object.get("z")+"/finish";

                        WarningDevice = new FIMqttACActuator(
                        		broker,
                                "/formosa/"+object.get("z")+"/"+object.get("id"),
                                new FIConfigParams().setParameter("ameliacreek", "/sub"+object.get("deviceName")+"/"),
                                Alarm,
                                "/formosa/"+object.get("z")+"/"+ a.get(0).toString().substring(2, a.get(0).toString().length()-2));

                        WarningDevice.start();

                        looper = new FIMqttLooper(
                        		broker,
                                "/formosa/"+object.get("z")+"/Looper",
                                new FIConfigParams(),
                                "/formosa/"+object.get("z")+"/"+object.get("id"));
                        looper.start();
                        break;

                    case LessEqualThan:

                        System.out.println("case LessEqualThan");
                        operator = "LessEqualThan";
                        lessEqualThanOperator = new FIMqttLessThanOperator(
                        		broker,
                                "/formosa/"+object.get("z")+"/"+object.get("id"),
                                new FIConfigParams(),
                                operator,
                                "/formosa/"+object.get("z")+"/"+ a.get(0).toString().substring(2, a.get(0).toString().length()-2),
                                "/formosa/"+object.get("z")+"/"+ a.get(1).toString().substring(2, a.get(1).toString().length()-2));

                        lessEqualThanOperator.start();
                        break;

                    case LessThan:

                        System.out.println("case LessThan");
                        operator = "LessThan";
                        lessThanOperator = new FIMqttLessThanOperator(
                        		broker,
                                "/formosa/"+object.get("z")+"/"+object.get("id"),
                                new FIConfigParams(),
                                operator,
                                "/formosa/"+object.get("z")+"/"+ a.get(0).toString().substring(2, a.get(0).toString().length()-2),
                                "/formosa/"+object.get("z")+"/"+ a.get(1).toString().substring(2, a.get(1).toString().length()-2));

                        lessThanOperator.start();
                        break;

                    case Equal:

                        System.out.println("case Equal");
                        operator = "Equal";
                        EqualOperator = new FIMqttLessThanOperator(
                        		broker,
                                "/formosa/"+object.get("z")+"/"+object.get("id"),
                                new FIConfigParams(),
                                operator,
                                "/formosa/"+object.get("z")+"/"+ a.get(0).toString().substring(2, a.get(0).toString().length()-2),
                                "/formosa/"+object.get("z")+"/"+ a.get(1).toString().substring(2, a.get(1).toString().length()-2));

                        EqualOperator.start();
                        break;

                    case Number:

                        System.out.println("case Number");

                        number = new FIMqttNumber(
                        		broker,
                                "/formosa/"+object.get("z")+"/"+object.get("id"),
                                new FIConfigParams().setParameter("constant", object.get("value")),
                                "/formosa/"+object.get("z")+"/Looper");
                        number.start();
                        break;

                    case ILLUMINANCEMEASURE_S:

                        System.out.println("case ILLUMINANCEMEASURE_S");

                        Illuminance = new FIMqttACSensor(
                        		broker,
                                "/formosa/"+object.get("z")+"/"+object.get("id"),
                                new FIConfigParams().setParameter("ameliacreek", "/pub"+object.get("deviceName")+"/"),
                                "/formosa/"+object.get("z")+"/Looper");
                        Illuminance.start();
                        break;

                    case TEMPERATUREMEASURE_S:

                        System.out.println("case TEMPERATUREMEASURE_S");

                        Temperature = new FIMqttACSensor(
                        		broker,
                                "/formosa/"+object.get("z")+"/"+object.get("id"),
                                new FIConfigParams().setParameter("ameliacreek", "/pub"+object.get("deviceName")+"/"),
                                "/formosa/"+object.get("z")+"/Looper");
                        Temperature.start();
                        break;
                }
            }
			  
            looper.run();

            parameters.five_s_alive = true;

            while(true) {

                System.out.println("[while] parameters.five_s_alive = " + parameters.five_s_alive);

                if (!parameters.five_s_alive){

                    /** Tell looper that no need to deploy again */
                    looper.setNeedReDeploy(false);

                    System.out.println("[in while in if] parameters.five_s_alive = " + parameters.five_s_alive);
                    if(Illuminance != null){
                        Illuminance.stop();
                        Illuminance.finalize();
                        Illuminance = null;
                    }
                    if(Temperature != null){
                        Temperature.stop();
                        Temperature.finalize();
                        Temperature = null;
                    }
                    if(number != null){
                        number.stop();
                        number.finalize();
                        number = null;
                    }
                    if(lessThanOperator != null){
                        lessThanOperator.stop();
                        lessThanOperator.finalize();
                        lessThanOperator = null;
                    }
                    if(lessEqualThanOperator != null){
                        lessEqualThanOperator.stop();
                        lessEqualThanOperator.finalize();
                        lessThanOperator = null;
                    }
                    if(EqualOperator != null){
                        EqualOperator.stop();
                        EqualOperator.finalize();
                        EqualOperator = null;
                    }
                    if(powerSwitch != null){
                        powerSwitch.stop();
                        powerSwitch.finalize();
                        powerSwitch = null;
                    }
                    if(WarningDevice != null){
                        WarningDevice.stop();
                        WarningDevice.finalize();
                        WarningDevice = null;
                    }

//                    String content  = "{123}";
//                    MqttClient mMqttClient;
//                    try {
//                        mMqttClient = new MqttClient(broker,MqttClient.generateClientId());
//
//                        MqttConnectOptions connOpts = new MqttConnectOptions();
//                        connOpts.setCleanSession(true);
//
//                        mMqttClient.connect(connOpts);
//
//                        System.out.println("finish topic: " + topic);
//                        MqttMessage message = new MqttMessage(content.getBytes());
//                        message.setQos(1);
//                        mMqttClient.publish(topic, message);
//
//                        mMqttClient.disconnect();
//                    } catch (MqttException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                        System.out.println("[exception]" + e);
//                    }

                    System.out.println("[Rule Engine] STOP!");
                    break;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("Thread sleep error: " + e);
                }
            }
        }
        System.gc();
    }

    public void setAliveFlag(boolean alive) {
        parameters.five_s_alive = alive;
        System.out.println("[setAliveFlag] parameters.five_s_alive = " + parameters.five_s_alive);
    }

    public boolean getAliveFlag() {
        return parameters.five_s_alive;
    }

}
