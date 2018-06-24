package io.CR9C.github;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;

import org.junit.jupiter.api.Test;


public class SpringIoCMockTest {
	
	//模拟Spring IoC容器操作
	@Test
	void testIoCMock() throws Exception {
		String className = "cn.wolfcode.hello.HelloWorld";
		HelloWorld world = null;
		//------------------------------------------------------
		//使用反射创建对象
		Class clzz  = Class.forName(className);
		Constructor con = clzz.getConstructor();
		con.setAccessible(true);//设置构造器的可访问性
		Object obj =con.newInstance();
		//使用内省机制设置属性值
		BeanInfo beanInfo = Introspector.getBeanInfo(clzz,Object.class);
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor pd : pds) {
			//HelloWorld类中所有的属性名称
			String  propertyName = pd.getName();
			if("username".equals(propertyName)) {
				//调用username的setter方法
				pd.getWriteMethod().invoke(obj, "Lucy");
			}else if("age".equals(propertyName)) {
				//调用age的setter方法
				pd.getWriteMethod().invoke(obj, 18);
			}
		}
		world = (HelloWorld)obj;
		//------------------------------------------------------
		world.sayHello();
	}
}
