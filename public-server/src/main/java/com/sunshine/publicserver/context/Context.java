package com.sunshine.publicserver.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Context {

	public static final String AGENT_ID = "agentId";

	private Map<String, Object> map = new HashMap<String, Object>();

	public String getString(String key) {
		return (String) map.get(key);
	}

	public int getInt(String key) {
		Object obj = map.get(key);
		if (obj instanceof String) {
			return Integer.parseInt((String) obj);
		} else {
			return (Integer) map.get(key);
		}
	}

	public int getInt(String key, int defaultIfNull) {
		Object obj = map.get(key);
		if (obj == null) {
			return defaultIfNull;
		}
		if (obj instanceof String) {
			return Integer.parseInt((String) obj);
		} else {
			return (Integer) map.get(key);
		}
	}

	public double getDouble(String key) {
		return (double) map.get(key);
	}

	public long getLong(String key) {
		Object obj = map.get(key);
		if (obj instanceof String) {
			return Long.parseLong((String) map.get(key));
		} else {
			return (Long) map.get(key);
		}
	}

	public boolean getBoolean(String key) {
		return (Boolean) map.get(key);
	}

	public boolean getBoolean(String key, boolean defaultIfNull) {
		Object obj = map.get(key);
		if (obj == null) {
			return defaultIfNull;
		}
		if (obj instanceof String) {
			return Boolean.parseBoolean((String) obj);
		} else {
			return (Boolean) map.get(key);
		}
	}

	public Object get(String key) {
		return map.get(key);
	}

	public void set(String key, Object val) {
		this.map.put(key, val);
	}

	public void setMap(Map<String, ? extends Object> map) {
		this.map.putAll(map);
	}

	public Map<String, Object> getAll() {
		return this.map;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Context [map=");
		for (Entry<String, Object> entry : map.entrySet()) {
			sb.append(entry.getKey()).append(":").append(entry.getValue())
					.append(";");
		}
		sb.append("]");
		return sb.toString();
	}

}
