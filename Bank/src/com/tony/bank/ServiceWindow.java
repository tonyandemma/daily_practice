package com.tony.bank;

import java.util.Random;
import java.util.concurrent.Executors;

public class ServiceWindow {
	// 客户类型
	private CustomerType type = CustomerType.COMMON;
	// 服务窗口号
	private int windowId = 1;
	
	
	public void setType(CustomerType type) {
		this.type = type;
	}


	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}

	/**
	 * 开始办理业务
	 */
	public void start() {
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
				while(true) {
					switch(type) {
					case COMMON:
						commonService();
						break;
					case EXPRESS:
						expressService();
						break;
					case VIP:
						vipService();
						break;
					}
				}
			}

		});
	}
	
	/**
	 * 普通服务
	 */
	public void commonService() {
		String windowName = "第" + windowId + "号" + type + "窗口";
		Integer number = NumberMachine.getInstance().getCommonManager().fetchServiceNumber();
		System.out.println(windowName + "正在获取任务...");
		
		if(number != null) {
			System.out.println(windowName + "开始为第" + number + "个" + "普通" + "客户服务");
			long beginTime = System.currentTimeMillis();
			// 服务时间
			int maxRand = Constants.MAX_SERVICE_TIME - Constants.MIN_SERVICE_TIME;
			long serveTime = new Random().nextInt(maxRand) + 1 + Constants.MIN_SERVICE_TIME;
			try {
				Thread.sleep(serveTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long costTime = System.currentTimeMillis() - beginTime;
			System.out.println(windowName + "为第" + number + "个" + "普通" + "客户完成服务，耗时" + costTime/1000);
		}else {
			System.out.println(windowName + "没有取到" + type + "服务任务, 空闲1秒");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 快速服务
	 */
	public void expressService() {
		String windowName = "第" + windowId + "号" + type + "窗口";
		Integer number = NumberMachine.getInstance().getExpressManager().fetchServiceNumber();
		System.out.println(windowName + "正在获取任务...");
		
		if(number != null) {
			System.out.println(windowName + "开始为第" + number + "个" + type + "客户服务");
			long beginTime = System.currentTimeMillis();
			// 服务时间
			long serveTime = Constants.MIN_SERVICE_TIME;
			try {
				Thread.sleep(serveTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long costTime = System.currentTimeMillis() - beginTime;
			System.out.println(windowName + "为第" + number + "个" + type + "客户完成服务，耗时" + costTime/1000);
		}else {
			System.out.println(windowName + "没有取到" + type + "服务任务");
			commonService();
		}
	}
	
	/**
	 * VIP服务
	 */
	public void vipService() {
		String windowName = "第" + windowId + "号" + type + "窗口";
		Integer number = NumberMachine.getInstance().getVipManager().fetchServiceNumber();
		System.out.println(windowName + "正在获取任务...");
		
		if(number != null) {
			System.out.println(windowName + "开始为第" + number + "个" + type + "客户服务");
			long beginTime = System.currentTimeMillis();
			int maxRand = Constants.MAX_SERVICE_TIME - Constants.MIN_SERVICE_TIME;
			long serveTime = new Random().nextInt(maxRand) + 1 + Constants.MIN_SERVICE_TIME;
			try {
				Thread.sleep(serveTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long costTime = System.currentTimeMillis() - beginTime;
			System.out.println(windowName + "为第" + number + "个" + type + "客户完成服务，耗时" + costTime/1000);
		}else {
			System.out.println(windowName + "没有取到" + type + "服务任务");
			commonService();
		}
	}
}
