package org.a807966224.xicp;




class Data0Producer0 implements Runnable{

	private Data0 Data0;//获取数据中心依赖
	public Data0Producer0(Data0 Data0) {
		this.Data0 = Data0;
	}
	@Override
	public void run() {
		for(int i=0;i<20;i++) {
			if(i%2 == 0) {
				this.Data0.setName("zx");
				this.Data0.setSex("male");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.Data0.setAge(25);
			}else {
				this.Data0.setName("wjj");
				this.Data0.setSex("female");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.Data0.setAge(24);
			}
		}
	}
	
}
class Data0Consumer0 implements Runnable{

	private Data0 Data0;//获取数据中心依赖
	public Data0Consumer0(Data0 Data0) {
		this.Data0 = Data0;
	}
	@Override
	public void run() {
		for(int i=0;i<20;i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(this.Data0.toString());
		}
	}
	
}
class Data0{
	private String name;
	private int age;
	private String sex;
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
		return "Data0 [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
	
}

/**
 * 生产者/消费者  基础模型
 * @author zhaoxin
 * 本例代码：验证了由于网络延迟问题，导致数据生产和数据取出的不一致问题，即导致了不同步数据
 */
public class demo0_0 {
	public static void main(String[] args) {
		Data0 Data0 = new Data0();
		new Thread(new Data0Producer0(Data0)).start();
		new Thread(new Data0Consumer0(Data0)).start();
	}
}
