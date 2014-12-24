package com.intel.formosa;

import java.util.Arrays;

/**
*
* @author Shao-Wen Yang <shao-wen.yang@intel.com>
*
*/
public class FIMessage {

	public final String id;

	public final byte[] payload;
	
	public FIMessage(String id, byte[] payload) {
		this.id = id;
		this.payload = Arrays.copyOf(payload, payload.length);
	}
	
	public FIMessage(String id, String message) {
		this(id, message.getBytes());
	}
	
	public <T extends Number> FIMessage(String id, T number) {
		this(id, String.valueOf(number));
	} 
	
}