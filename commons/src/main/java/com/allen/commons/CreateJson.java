package com.allen.commons;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateJson {
	
	public JSONObject create() {
		
		List<JSONObject> json_list = new ArrayList<>();
		Random random = new Random();
		String[] capabilities = {"Python", "JAVA", "C", "Ruby"};
		String[] degree = {"Familiar", "Proficient", "Common"};
		JSONObject json_obj = new JSONObject();
		JSONArray json_array = new JSONArray();
		json_obj.put("Name", "Daniel");
		json_obj.put("Position", "PerformanceTestEngineer");
		json_obj.put("Company", "XXX");
		json_obj.put("Sex", "Male");
		JSONObject jsn = null;
		Integer ID = 0;
		for(String str : capabilities) {
			int n = random.nextInt(degree.length);
			ID++;
			jsn = new JSONObject();
			jsn.put("capability", str);
			jsn.put("ID", ID);
			jsn.put("Degree", degree[n]);
			json_list.add(jsn);
		}
		
		for(JSONObject j : json_list) {
			json_array.add(j);
		}
		
		json_obj.putIfAbsent("Skills", json_array);
		
//		System.out.println(json_obj);
		return json_obj;
	}
	

}
