package com.intel.formosa;

import java.util.Arrays;

/**
*
* @author Shao-Wen Yang <shao-wen.yang@intel.com>
*
*/
public class FIMessage {

	public String id;

	public byte[] payload;
	
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
	
	@Override
	public String toString() {
		return payload.toString();
	}
	
	public <T> T value(T defaultValue) {
		String obj = payload.toString();
		if (obj != null) {
			try {
				if (defaultValue instanceof String) {
					@SuppressWarnings("unchecked")
					T value = (T) obj;
					return value;
				} else if (defaultValue instanceof Number) {
					if (defaultValue instanceof Integer) {
						@SuppressWarnings("unchecked")
						T value = (T) Integer.valueOf(obj);
						return value;
					} else if (defaultValue instanceof Double) {
						@SuppressWarnings("unchecked")
						T value = (T) Double.valueOf(obj);
						return value;						
					} else {
						return defaultValue;
					}
				} else if (defaultValue instanceof Boolean) {
					@SuppressWarnings("unchecked")
					T value = (T) Boolean.valueOf(obj);
					return value;
				} else {
					return defaultValue;
				}
			} catch (ClassCastException e) {
				return defaultValue;
			}
		}
		else {
			return defaultValue;
		}
	}
	
}