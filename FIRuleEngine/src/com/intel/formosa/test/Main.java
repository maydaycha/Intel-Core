package com.intel.formosa.test;

public class Main {	

	
		
    public static void main(String args[]) {
    	
    	Thread t1 = new Thread(new Go());
    	t1.start();
    }
}
