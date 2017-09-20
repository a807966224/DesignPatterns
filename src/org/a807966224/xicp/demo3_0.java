package org.a807966224.xicp;


/**
 * 继承了 这个类   都有  open 和 close方法
 * @author zhaoxin
 *
 */
abstract class Door{
	public abstract void open();
	public abstract void close();
	
	public void subHasMe() {
		System.out.println("只要是我的子类   都有我这个功能   ");
	}
}

/**
 * 接口中的变量 隐式的 public static final
 * 		  方法  public abstract   来修饰  其它修饰均会报错
 * 
 * @author zhaoxin
 *
 */
interface Alram{
	void alram();
}

class AlramDoor extends Door implements Alram{

	public AlramDoor() {
		this.alram();
		this.close();
		this.open();
		this.subHasMe();
		super.subHasMe();
	}
	
	@Override
	public void alram() {
		// TODO Auto-generated method stub
		System.out.println("特殊的行为！");
	}

	@Override
	public void open() {
		// TODO Auto-generated method stub
		System.out.println("是门   就具有的功能！");
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		System.out.println("是门   就具有的功能！");
	}

	@Override
	public void subHasMe() {
		// TODO Auto-generated method stub
		System.out.println("子类  重新 父类方法");
	}
	
}

public class demo3_0 {

	
	
	
	/**
	 * interface   和   abstract  的区别
	 * @param args
	 */
	public static void main(String[] args) {
		AlramDoor ad = new AlramDoor();
	}
	
}
