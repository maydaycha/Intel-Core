package com.intel.formosa;

/**
*
* @author Shao-Wen Yang <shao-wen.yang@intel.com>
*
*/
public interface FISink extends FIObject {
	
	public void source(FIMessage message);
	
}
