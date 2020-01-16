package com.resources.json;

import java.util.ArrayList;


public class SlicingHeartBeat {

	private String LAST_SLICING_ms;
	private ArrayList<Last_Slicing> LAST_SLICING ;
	private String PE_HEARTBEAT_ms;
	private ArrayList<Pe_HeartBeat> PE_HEARTBEAT ;
	public String getLAST_SLICING_ms() {
		return LAST_SLICING_ms;
	}
	public void setLAST_SLICING_ms(String lAST_SLICING_ms) {
		LAST_SLICING_ms = lAST_SLICING_ms;
	}
	public ArrayList<Last_Slicing> getLAST_SLICING() {
		return LAST_SLICING;
	}
	public void setLAST_SLICING(ArrayList<Last_Slicing> lAST_SLICING) {
		LAST_SLICING = lAST_SLICING;
	}
	public String getPE_HEARTBEAT_ms() {
		return PE_HEARTBEAT_ms;
	}
	public void setPE_HEARTBEAT_ms(String pE_HEARTBEAT_ms) {
		PE_HEARTBEAT_ms = pE_HEARTBEAT_ms;
	}
	public ArrayList<Pe_HeartBeat> getPE_HEARTBEAT() {
		return PE_HEARTBEAT;
	}
	public void setPE_HEARTBEAT(ArrayList<Pe_HeartBeat> pE_HEARTBEAT) {
		PE_HEARTBEAT = pE_HEARTBEAT;
	}
	
	
}
