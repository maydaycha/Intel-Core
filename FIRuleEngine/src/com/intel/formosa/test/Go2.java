package com.intel.formosa.test;

/**
 * Created by Maydaycha on 1/9/15.
 */
public class Go2 implements Runnable {

    Parameters parameters = null;

    public Go2() {
        parameters = new Parameters();
    }
    @Override
    public void run() {
        System.out.println("init params five_s_alive: " + parameters.five_s_alive);
        while (true) {
            if (!parameters.five_s_alive) {
                System.out.println("Stop!!!");
                break;
            } else {
                System.out.println("stille run");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void setAliveFlag(boolean flag) {
        parameters.five_s_alive = flag;
        System.out.println("set flag: " + parameters.five_s_alive);
    }

    public boolean getAliveFlag() {
        return parameters.five_s_alive;
    }
}
