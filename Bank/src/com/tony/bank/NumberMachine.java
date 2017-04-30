package com.tony.bank;

public class NumberMachine {
	private NumberManager commonManager = new NumberManager();
	private NumberManager expressManager = new NumberManager();
	private NumberManager vipManager = new NumberManager();
	
	public NumberManager getCommonManager() {
		return commonManager;
	}
	public NumberManager getExpressManager() {
		return expressManager;
	}
	public NumberManager getVipManager() {
		return vipManager;
	}
	
	/**
	 * 单例
	 */
	private NumberMachine() {}
	
	public static NumberMachine getInstance() {
		return instance;
	}
	private static NumberMachine instance = new NumberMachine();
}
