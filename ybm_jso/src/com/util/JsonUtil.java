package com.util;

import java.sql.ResultSetMetaData;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {

	public static JSONArray formatRsToJsonArray(List list) throws Exception {
		int num = list.size();
		JSONArray array = new JSONArray();
			JSONObject mapOfColValues = new JSONObject();
			for (int i = 0; i < num; i++) {
				Object o = list.get(i);
				mapOfColValues.put(((ResultSetMetaData) list).getColumnName(i),o);
			}
			array.add(mapOfColValues);
		return array;
	}
}
