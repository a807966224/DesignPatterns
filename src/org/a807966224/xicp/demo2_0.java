package org.a807966224.xicp;

//抽象角色
abstract class Subject{
	public abstract void request();
}

//真实角色
class RealSubject extends Subject{

	@Override
	public void request() {
		System.out.println("房费：1000元！");
	}
	
}

/**
 * 静态代理，对具体真实对象有引用
 * 代理角色，代理角色对真实角色的引用
 * 代理做真实角色想做的事情
 * @author zhaoxin
 *
 */
class ProxySubject extends Subject{

	private RealSubject realSubject = null;
	
	
	/**
	 * 除了代理真实角色，代理角色也可以提供附加操作
	 */
	@Override
	public void request() {
		preRequest();//真实角色操作前的附加操作
		
		if(realSubject == null) {
			realSubject = new RealSubject();
		}
		
		realSubject.request();
		
		afterRequest();//真实角色操作后的附加操作
	}
	
	public void preRequest() {
		System.out.println("中介费：200元！～押金：500元！");
	}
	
	public void afterRequest() {
		System.out.println("退还押金：500元！～");
	}
	
}
public class demo2_0 {

	/**
	 * 静态代理模式
	 * 
	 * 以卖房(Subject的request方法)为例，
	 * 卖房者(RealSubject)想卖房，
	 * 但是不想搞那么多麻烦事，不想天天招买房的骚扰，
	 * 就委托中介(ProxySubject)去帮忙卖房，中介帮忙卖房，
	 * 当然不会简单的卖房，
	 * 可能还需要收取中介费(preRequest()和postRequest()方法)等等附加收费，
	 * 最终买房者(客户端Main)买房，找到的是中介，
	 * 不会与房主接触，与房主接触的是中介，买房者最终跟中介买房，完成操作。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Subject subject = new ProxySubject();
		subject.request();
	}

}
