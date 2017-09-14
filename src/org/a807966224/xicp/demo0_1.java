package org.a807966224.xicp;

class Data1Producer1 implements Runnable{

	private Data1 Data1;//获取数据中心依赖
	public Data1Producer1(Data1 Data1) {
		this.Data1 = Data1;
	}
	@Override
	public void run() {
		for(int i=0;i<20;i++) {
			if(i%2 == 0) {
				this.Data1.set("zx", 25, "male");
			}else {
				this.Data1.set("wjj", 24, "female");
			}
		}
	}
	
}
class Data1Consumer1 implements Runnable{

	private Data1 Data1;//获取数据中心依赖
	public Data1Consumer1(Data1 Data1) {
		this.Data1 = Data1;
	}
	@Override
	public void run() {
		for(int i=0;i<20;i++) {
			System.out.println(this.Data1.get());
		}
	}
	
}
class Data1{
	private String name;
	private int age;
	private String sex;
	public synchronized void set(String name,int age,String sex) {
		this.name = name;
		this.age = age;
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.sex = sex;
	}
	public synchronized String get() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this.getName() + " = " + this.getAge() + " = " + this.getSex();
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
		return "Data1 [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
	
}
/**
 * 生产者/消费者  基础模型
 * @author zhaoxin
 * 本例代码:解决由于网络延迟带来的数据不同步问题，但是  出现了   获取数据的重复问题   
 */
public class demo0_1 {
	public static void main(String[] args) {
		Data1 Data1 = new Data1();
		new Thread(new Data1Producer1(Data1)).start();
		new Thread(new Data1Consumer1(Data1)).start();
	}
}
