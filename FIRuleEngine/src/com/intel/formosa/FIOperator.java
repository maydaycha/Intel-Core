package com.intel.formosa;

/**
*
* @author Shao-Wen Yang <shao-wen.yang@intel.com>
*
*/
public interface FIOperator extends FIObject {

	public void run(FIMessage ... messages);
	
}
