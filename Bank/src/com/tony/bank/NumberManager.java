package com.tony.bank;

import java.util.ArrayList;
import java.util.List;

/**
 * 银行排队号管理
 * @Description 产生有序的排队号， 银行业务人员按号办理
 * @author lblhz
 * @Date 2017年4月30日
 */
public class NumberManager {
	private int lastNumber = 1;
	private List<Integer> queueNumber = new ArrayList<Integer>();
	
	/**
	 * 号码管理
	 */
	public synchronized Integer generateNewManager() {
		queueNumber.add(lastNumber);
		return lastNumber++;
	}
	
	/**
	 * 银行工作人员取办理业务人员手中的号
	 * @return 
	 */
	public synchronized Integer fetchServiceNumber() {
		Integer number = null;
		if(queueNumber.size() > 0) {
			number  = queueNumber.remove(0);
		}
		return number;
	}
}
