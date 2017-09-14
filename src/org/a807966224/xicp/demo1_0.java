package org.a807966224.xicp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Book{
	private static Book book = null;
	public Book() {}
	public static Book getInstance() {
		try {
			if(book != null) {
				
			}else {
				Thread.sleep(300);
				synchronized(Book.class) {
					if(book == null) {//二次检查   如果不加的话   同步锁不起作用  还是会出现不同步实例化多个对象的问题
						book = new Book();
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return book;
	}
}

/**
 * 探讨单例模式
 * 懒汉式   实现方式
 * @author zhaoxin
 *
 */
public class demo1_0 extends Thread{
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+ "==" + Book.getInstance().hashCode());
	}

	public static void main(String[] args) {
//		Book book1 = Book.getInstance();
//		Book book2 = Book.getInstance();
//		System.out.println(book1 == book2);
//		  
//		
//		//我现在想 测试  使用线程池   固定大小里面有10个线程跑  让他们去获取Book实例
//		//我想知道  在出现同步的情况下  是否会得到  实例化不唯一的情况
		//  搞了半天    之前上面的输出语句 一直都没注释  导致  下面的线程池中的线程跑实例化方法的时候  根本不能走如为空的判断  导致 无法显现实例化不同步的问题
		//将上面的输出去掉后   即可出现多线程操作同一个代码块导致 实例化多个对象出现
//		
//		demo1_0[] demo1_0s = new demo1_0[10];
//		for(int i=0;i<demo1_0s.length;i++) {
//			demo1_0s[i] = new demo1_0();
//		}
//		for(int i=0;i<demo1_0s.length;i++) {
//			demo1_0s[i].start();
//		}
		
		ExecutorService executorService  = Executors.newFixedThreadPool(10);
		for(int i=0;i<30;i++) {
			executorService.submit(()->{
				System.out.println(Thread.currentThread().getName()+ "==" + Book.getInstance());
			});
		}
		executorService.shutdown();
	}

}
