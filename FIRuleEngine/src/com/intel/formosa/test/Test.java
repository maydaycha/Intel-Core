package com.intel.formosa.test;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Test {

	public static void main(String[] args){
  

		  try {
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(new FileReader("C:\\Users\\renjiewu\\Downloads\\result1.json"));
			Mapper main = new Mapper();
			JSONObject result = main.run(jsonObj.toJSONString());
			
			System.out.println("" + result);
			
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
