package com.resources.json;

import java.util.Map;

import com.google.gson.annotations.JsonAdapter;

public class Pe_HeartBeat {
	String value;
	String tenant;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getTenant() {
		return tenant;
	}
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	
}
