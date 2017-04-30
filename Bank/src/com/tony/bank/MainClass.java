package com.tony.bank;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainClass {

	public static void main(String[] args) {
		// 普通窗口
		for(int i = 1; i < 5; i++) {
			ServiceWindow commonWindow = new ServiceWindow();
			commonWindow.setWindowId(i);
			commonWindow.start();
		}
		// 快速窗口
		ServiceWindow expressWindow = new ServiceWindow();
		expressWindow.setType(CustomerType.EXPRESS);
		expressWindow.start();
		// VIP窗口
		ServiceWindow vipWindow = new ServiceWindow();
		vipWindow.setType(CustomerType.VIP);
		vipWindow.start();
		
		// 调度线程池
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
				new Runnable(){
					@Override
					public void run() {
						Integer number = NumberMachine.getInstance().getCommonManager().generateNewManager();
						System.out.println(number + "号普通客户等待服务...");
					}
				}, 
				0, 
				Constants.COMMON_CUSTOMER_INTERVAL_TIME, 
				TimeUnit.SECONDS
		);
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
				new Runnable(){
					@Override
					public void run() {
						Integer number = NumberMachine.getInstance().getVipManager().generateNewManager();
						System.out.println(number + "号VIP客户等待服务...");
					}
				}, 
				0, 
				Constants.COMMON_CUSTOMER_INTERVAL_TIME * 6, 
				TimeUnit.SECONDS
		);
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
				new Runnable(){
					@Override
					public void run() {
						Integer number = NumberMachine.getInstance().getExpressManager().generateNewManager();
						System.out.println(number + "号快速客户等待服务...");
					}
				}, 
				0, 
				Constants.COMMON_CUSTOMER_INTERVAL_TIME * 2, 
				TimeUnit.SECONDS
				);
	}
}
