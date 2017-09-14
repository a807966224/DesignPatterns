package org.a807966224.xicp;

class Data2Producer2 implements Runnable{

	private Data2 Data2;//获取数据中心依赖
	public Data2Producer2(Data2 Data2) {
		this.Data2 = Data2;
	}
	@Override
	public void run() {
		for(int i=0;i<20;i++) {
			if(i%2 == 0) {
				this.Data2.set("zx", 25, "male");
			}else {
				this.Data2.set("wjj", 24, "female");
			}
		}
	}
	
}
class Data2Consumer2 implements Runnable{

	private Data2 Data2;//获取数据中心依赖
	public Data2Consumer2(Data2 Data2) {
		this.Data2 = Data2;
	}
	@Override
	public void run() {
		for(int i=0;i<20;i++) {
			this.Data2.get();
		}
	}
	
}
class Data2{
	private String name;
	private int age;
	private String sex;
	
	//添加标志位：   true 代表可以生产 不可以取走
	//			   false 代表可以取走 不可以生产
	private boolean flag = true;
	
	public synchronized void set(String name,int age,String sex) {
		if(this.flag == false) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			this.name = name;
			this.age = age;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.sex = sex;
			this.flag = false;
			super.notify();
	}
	public synchronized void get() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(this.flag == true) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(this.toString());
		this.flag = true;
		super.notify();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "Data2 [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
	
}
/**
 * 生产者/消费者  基础模型
 * @author zhaoxin
 * 本例代码:解决由于网络延迟带来的数据不同步问题，但是  出现了   获取数据的重复问题 
 * 为了解决数据重复的问题    我们需要去    找寻   Object类为我们提供的关于线程的方法
 * public final void wait() throws InterruptedException  实现线程等待机制
 * public final void notify()   唤醒当前第一个线程
 * public final void notifyAll()  唤醒所有休眠的线程
 * 
 * 总结：
 * 首先需要明白的是   这些问题的发生  是由于线程的先后顺序无法确定的原因
 *  想要实现    供给关系   
 *  必须做到一个线程进来设置后  另一个线程才可取走
 *  代码块或方法体上上锁（synchornized修饰） 只是能保证  一个线程进入  但是并不能保证  交替的两个线程的顺序
 *  所以  我们需要  加上  标志位 配合上object类提供的线程休眠与唤醒机制  用来处理线程的先后顺序  达到最后的供给关系   
 * 
 */
public class demo0_2 {
	public static void main(String[] args) {
		Data2 Data2 = new Data2();
		new Thread(new Data2Producer2(Data2)).start();
		new Thread(new Data2Consumer2(Data2)).start();
	}
}
