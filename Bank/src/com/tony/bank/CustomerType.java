package com.tony.bank;

public enum CustomerType {
	COMMON, EXPRESS, VIP;
	
	public String toString() {
		switch(this) {
		case COMMON:
			return "普通";
		case EXPRESS:
			return "快速";
		case VIP:
			return name();
		}
		return null;
	}
}
