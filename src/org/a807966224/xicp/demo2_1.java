package org.a807966224.xicp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/** 
 * 抽象角色 
 * 这里应改为接口 
 */  
interface Subject1 {  
    void request();  
}  

/** 
 * 真实的角色 
 * 实现接口 
 */  
class RealSubject1 implements Subject1 {  
  
    @Override  
    public void request() {  
        // TODO Auto-generated method stub  
  
    }  
  
}  

/** 
 * 动态代理， 它是在运行时生成的class，在生成它时你必须提供一组interface给它， 然后该class就宣称它实现了这些interface。 
 * 你当然可以把该class的实例当作这些interface中的任何一个来用。 当然啦，这个Dynamic 
 * Proxy其实就是一个Proxy，它不会替你作实质性的工作， 在生成它的实例时你必须提供一个handler，由它接管实际的工作。 
 */  
class DynamicSubject implements InvocationHandler {  
  
    private Object sub; // 真实对象的引用  
  
    public DynamicSubject(Object sub) {  
        this.sub = sub;  
    }  
  
    @Override  
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {  
        System.out.println("before calling " + method);   
        method.invoke(sub,args);   
        System.out.println("after calling " + method);   
        return null;   
    }  
  
}  

public class demo2_1 {

	
	/**
	 * Java动态代理类位于Java.lang.reflect包下，一般主要涉及到以下两个类：
		(1). Interface InvocationHandler：
			该接口中仅定义了一个方法Object：invoke(Objectobj,Method method, Object[] args)。
			在实际使用时，第一个参数obj一般是指代理类，method是被代理的方法，
			如上例中的request()，args为该方法的参数数组。这个抽象方法在代理类中动态实现。
		(2).Proxy：该类即为动态代理类，作用类似于上例中的ProxySubject，其中主要包含以下内容：
			Protected Proxy(InvocationHandler h)：构造函数，估计用于给内部的h赋值。
			Static Class getProxyClass (ClassLoaderloader, Class[] interfaces)：获得一个代理类，其中loader是类装载器，interfaces是真实类所拥有的全部接口的数组。
			Static Object newProxyInstance(ClassLoaderloader, Class[] interfaces, InvocationHandler h)：返回代理类的一个实例，返回后的代理类可以当作被代理类使用(可使用被代理类的在Subject接口中声明过的方法)。
			所谓Dynamic Proxy是这样一种class：它是在运行时生成的class，
			在生成它时你必须提供一组interface给它，然后该class就宣称它实现了这些interface。
			你当然可以把该class的实例当作这些interface中的任何一个来用。
			当然啦，这个DynamicProxy其实就是一个Proxy，它不会替你作实质性的工作，
			在生成它的实例时你必须提供一个handler，由它接管实际的工作。
	 * @param args
	 */
	
	public static void main(String[] args) {
		RealSubject1 rs = new RealSubject1();  
        InvocationHandler handler = new DynamicSubject(rs);  
        Class cls = rs.getClass();  
        //以下是分解步骤  
        /* 
        Class c = Proxy.getProxyClass(cls.getClassLoader(), cls.getInterfaces()); 
        Constructor ct = c.getConstructor(new Class[]{InvocationHandler.class}); 
        Subject subject =(Subject) ct.newInstance(new Object[]{handler}); 
        */  
          
        //以下是一次性生成  
        Subject1 subject = (Subject1)Proxy.newProxyInstance(cls.getClassLoader(),cls.getInterfaces(), handler);  
        subject.request();  
	}
	
}
