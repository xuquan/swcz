package com.jm.swcz.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * bean工厂
 */
public class BeanFactory {
	private static BeanFactory instance = new BeanFactory();
	
	//存放产品{key=产品编号, value=具体产品实例}
	private Map<String,Object> beans = new HashMap<String,Object>();
	
	private BeanFactory(){
		
	}
	
	public static BeanFactory getInstance(){
		return instance;
	}
	
	public synchronized Object getBean(Class<?> cls){
		String beanName = cls.getSimpleName();
		synchronized(beans){
			//如果在Map存在已经创建的产品则返回
			if(beans.containsKey(beanName)){
				return beans.get(beanName);
			}
			Object obj = null;
			try {
				obj = cls.newInstance();
				beans.put(beanName, obj);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return obj;
		}
	}
}
