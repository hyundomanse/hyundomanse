package com.devwork.common.map;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.map.ListOrderedMap;
import com.devwork.common.util.StringUtil;

/**
 * com.devwork.common.map 
 *    |_ CommonMap.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
public class CommonMap extends ListOrderedMap {
	private static final long serialVersionUID = 6723434363565852261L;

	public Object put(Object key, Object value) {
		return super.put(StringUtil.convertToCamelCase((String)key), value);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap<Object, Object> convertToMap(CommonMap ezMap) {
		Map hashMap 	= new HashMap();
		MapIterator map = ezMap.mapIterator();
		while (map.hasNext()) {
			Object key = map.next();
			Object value = map.getValue();
			hashMap.put(key, value);
		}
		return (HashMap)hashMap;
	}
}