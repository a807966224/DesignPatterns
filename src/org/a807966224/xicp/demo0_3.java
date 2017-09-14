package org.a807966224.xicp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的实现
 * @author zhaoxin
 *
 */
public class demo0_3 {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();//创建一个不定大小的线程池 只要工作没完成 就一直往里面加线程进行工作
		for(int i=0;i<10;i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int tmp = i;
			executorService.submit(()->{
				System.out.println(Thread.currentThread().getName() + ", i = " + tmp);
			});
		}
		executorService.shutdown();
		
		System.out.println(" ========================================== ");
		
		
		ExecutorService executorService1 = Executors.newSingleThreadExecutor();//创建单线程池  不管工作多大   都是一个线程进行完成
		for(int i=0;i<10;i++) {
			int tmp = i;
			executorService1.submit(()->{
				System.out.println(Thread.currentThread().getName() + ", i = " + tmp);
			});
		}
		executorService1.shutdown();
		
		System.out.println(" ========================================== ");
		
		
		ExecutorService executorService2 = Executors.newFixedThreadPool(3);//创建固定大小的线程池 
		for(int i=0;i<10;i++) {
			int tmp = i;
			executorService2.submit(()->{
				System.out.println(Thread.currentThread().getName() + ", i = " + tmp);
			});
		}
		executorService2.shutdown();
		
		
		System.out.println(" ========================================== ");
		
		
		ScheduledExecutorService executorService3 = Executors.newScheduledThreadPool(3);//创建三个线程的大小的调度线程池 
		for(int i=0;i<10;i++) {
			int tmp = i;
			executorService3.scheduleAtFixedRate(()->{
				System.out.println(Thread.currentThread().getName() + ", i = " + tmp);
			}, 3, 2, TimeUnit.SECONDS);//以秒为单位  三秒后开始执行 每两秒执行一次
		}
	}
}
