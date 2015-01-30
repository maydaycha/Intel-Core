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

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


public class Go implements Runnable {


    FIMqttLooper looper = null;

    FIMqttObject Illuminance = null;
    FIMqttObject Temperature = null;
    FIMqttObject number = null;
    FIMqttObject lessThanOperator = null;
    FIMqttObject lessEqualThanOperator = null;
    FIMqttObject EqualOperator = null;
    FIMqttObject powerSwitch = null;
    FIMqttObject WarningDevice = null;
    JSONArray flow = null;
    Parameters parameters = null;
    String topic = null;
    String broker = "tcp://localhost:1883";

    String role = null;
    String sessionId = null;
    String hostIpAddress = null;

	
    public Go(JSONArray flow, String role, String sessionId) {
        // TODO Auto-generated constructor stub
        this.flow = flow;
        this.role = role;
        this.sessionId = sessionId;
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

        try {
            hostIpAddress = getHostIpAddress();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        // TODO Auto-generated method stub
        if (flow != null) {
            //FIConfigParams parameter = new FIConfigParams();
            for (Object o : flow) {
                //System.out.println(o);
                JSONObject object = (JSONObject) o;
                JSONArray a = (JSONArray) object.get("wires");
                String operator = null;
                boolean Alarm = false;

                switch (Nodes.getByName(object.get("deviceType").toString())) {

                    case ONOFF_S:
                        if (object.get("runningHost").toString().equals(hostIpAddress)) {
                            System.out.println("case ONOFF_S");
                            Alarm = false;
                            topic = "/formosa/"+ sessionId +"/finish";

                            powerSwitch = new FIMqttACActuator(
                                    broker,
                                    "/formosa/"+ sessionId +"/"+object.get("id"),
                                    new FIConfigParams().setParameter("ameliacreek", "/sub"+object.get("deviceName")+"/"),
                                    Alarm,
                                    "/formosa/"+ sessionId +"/"+ a.get(0).toString().substring(2, a.get(0).toString().length()-2));

                            powerSwitch.start();
                        }

                        /** Only master can create the looper */
                        if (role.equals("master")) {
                            looper = new FIMqttLooper(
                                    broker,
                                    "/formosa/"+ sessionId +"/Looper",
                                    new FIConfigParams(),
                                    "/formosa/"+ sessionId +"/"+object.get("id"));
                            looper.start();
                        }

                        break;

                    case IASWD_S:

                        if (object.get("runningHost").toString().equals(hostIpAddress)) {
                            System.out.println("case IASWD_S");
                            Alarm = true;
                            topic = "/formosa/"+ sessionId +"/finish";

                            WarningDevice = new FIMqttACActuator(
                                    broker,
                                    "/formosa/"+ sessionId +"/"+object.get("id"),
                                    new FIConfigParams().setParameter("ameliacreek", "/sub"+object.get("deviceName")+"/"),
                                    Alarm,
                                    "/formosa/"+ sessionId +"/"+ a.get(0).toString().substring(2, a.get(0).toString().length()-2));

                            WarningDevice.start();
                        }

                        /** Only master can create the looper */
                        if (role.equals("master")) {
                            looper = new FIMqttLooper(
                                    broker,
                                    "/formosa/"+ sessionId +"/Looper",
                                    new FIConfigParams(),
                                    "/formosa/"+ sessionId +"/"+object.get("id"));
                            looper.start();
                        }

                        break;

                    case LessEqualThan:

                        System.out.println("case LessEqualThan");
                        operator = "LessEqualThan";
                        lessEqualThanOperator = new FIMqttLessThanOperator(
                        		broker,
                                "/formosa/"+ sessionId +"/"+object.get("id"),
                                new FIConfigParams(),
                                operator,
                                "/formosa/"+ sessionId +"/"+ a.get(0).toString().substring(2, a.get(0).toString().length()-2),
                                "/formosa/"+ sessionId +"/"+ a.get(1).toString().substring(2, a.get(1).toString().length()-2));

                        lessEqualThanOperator.start();
                        break;

                    case LessThan:

                        System.out.println("case LessThan");
                        operator = "LessThan";
                        lessThanOperator = new FIMqttLessThanOperator(
                        		broker,
                                "/formosa/"+ sessionId +"/"+object.get("id"),
                                new FIConfigParams(),
                                operator,
                                "/formosa/"+ sessionId +"/"+ a.get(0).toString().substring(2, a.get(0).toString().length()-2),
                                "/formosa/"+ sessionId +"/"+ a.get(1).toString().substring(2, a.get(1).toString().length()-2));

                        lessThanOperator.start();
                        break;

                    case Equal:

                        System.out.println("case Equal");
                        operator = "Equal";
                        EqualOperator = new FIMqttLessThanOperator(
                        		broker,
                                "/formosa/"+ sessionId +"/"+object.get("id"),
                                new FIConfigParams(),
                                operator,
                                "/formosa/"+ sessionId +"/"+ a.get(0).toString().substring(2, a.get(0).toString().length()-2),
                                "/formosa/"+ sessionId +"/"+ a.get(1).toString().substring(2, a.get(1).toString().length()-2));

                        EqualOperator.start();
                        break;

                    case Number:

                        System.out.println("case Number");

                        number = new FIMqttNumber(
                        		broker,
                                "/formosa/"+ sessionId +"/"+object.get("id"),
                                new FIConfigParams().setParameter("constant", object.get("value")),
                                "/formosa/"+ sessionId +"/Looper");
                        number.start();
                        break;

                    case ILLUMINANCEMEASURE_S:

                        System.out.println("case ILLUMINANCEMEASURE_S");

                        Illuminance = new FIMqttACSensor(
                        		broker,
                                "/formosa/"+ sessionId +"/"+object.get("id"),
                                new FIConfigParams().setParameter("ameliacreek", "/pub"+object.get("deviceName")+"/"),
                                "/formosa/"+ sessionId +"/Looper");
                        Illuminance.start();
                        break;

                    case TEMPERATUREMEASURE_S:

                        System.out.println("case TEMPERATUREMEASURE_S");

                        Temperature = new FIMqttACSensor(
                        		broker,
                                "/formosa/"+ sessionId +"/"+object.get("id"),
                                new FIConfigParams().setParameter("ameliacreek", "/pub"+object.get("deviceName")+"/"),
                                "/formosa/"+ sessionId +"/Looper");
                        Temperature.start();
                        break;
                }
            }

            /** Only master can run the looper */
			if (role.equals("master") && looper != null) {
                looper.run();
            }

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

    private String getHostIpAddress() throws SocketException {
        /** get the host ip address */
        Enumeration<NetworkInterface> networkInterface = NetworkInterface.getNetworkInterfaces();

        String hostIpAddress = "";

        while (networkInterface.hasMoreElements()) {
            NetworkInterface e = networkInterface.nextElement();

            Enumeration<InetAddress> inetAddress = e.getInetAddresses();
            while (inetAddress.hasMoreElements()) {
                InetAddress addr = inetAddress.nextElement();
                String candicateIp = addr.getHostAddress();

                /** ignore mac address */
                if (candicateIp.split("\\.").length == 4) {
                    if (!candicateIp.equals("127.0.0.1")) {
                        hostIpAddress = candicateIp;
                    }
                }
            }
        }

        return hostIpAddress;
    }

}
