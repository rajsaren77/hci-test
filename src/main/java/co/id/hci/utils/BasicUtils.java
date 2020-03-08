package co.id.hci.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Saren Raj
 * 
 * Basic Utils
 *
 */

public class BasicUtils {
	
	static Logger log = LoggerFactory.getLogger(BasicUtils.class);

    public static List<Map<String, Object>> createListOfMapFromObjectArray(List<Object[]> list, String ... columnNames) throws Exception{
    	
		if (list == null) {
			return new ArrayList<Map<String, Object>>();
		}
		
		if (list.size() > 0) {
			if(list.get(0).length > columnNames.length) {
				throw new Exception("Invalid Argument");
			}
		}
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (Object[] item : list) {
			Map<String, Object> temp = new HashMap<String, Object>();
			for (int i = 0; i < columnNames.length; i++) {
				temp.put(columnNames[i], item[i]);
			}
			result.add(temp);
		}
		
		return result;
    }
}
