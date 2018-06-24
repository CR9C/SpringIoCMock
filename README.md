**Spring IoC 管理 bean 的原理：**

1、通过 Resource 对象加载配置文件

2、解析配置文件，得到指定名称的 bean

3、解析 bean 元素，id 作为 bean 的名字，class 用于反射得到 bean 的实例：

​	**注意：此时，bean 类必须存在一个无参数构造器(和访问权限无关)；**

4、调用 getBean 方法的时候，从容器中返回对象实例；

**结论：就是把代码从 JAVA 文件中转移到了 XML 中。** 

```
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

```

