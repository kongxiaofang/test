package test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
/**
 * 实体类相互赋值工具类
 * @author hasee
 *
 */
public class CopyUtil {
	/**
	 * 将原实体类中与目标实体类中类型相同的内容进行赋�??
	 * @param source 原实体类
	 * @param target 目标实体�?
	 * @throws Exception
	 */
	public static void copyObject(Object source,Object target) throws Exception{
		//获取bean
		BeanInfo sourceBean=Introspector.getBeanInfo(source.getClass(), Object.class);
		//字段内容
		PropertyDescriptor[] soruceProperty=sourceBean.getPropertyDescriptors();
		//目标bean内容
		BeanInfo targetBean=Introspector.getBeanInfo(target.getClass(), Object.class);
		//字段内容
		PropertyDescriptor[] targetProperty=targetBean.getPropertyDescriptors();
		//遍历两个bean
		try {
			for(int i=0;i<soruceProperty.length;i++){
				for(int j=0;j<targetProperty.length;j++){
					//获取两个bean中变量名以及类型都相同的
					if(soruceProperty[i].getName().equals(targetProperty[j].getName()) &&
							soruceProperty[i].getPropertyType()==targetProperty[j].getPropertyType()){
						//将原实体类的值�?�过原bean的get方法取出，�?�过目标bean的set方法塞入目标实体�?
						targetProperty[j].getWriteMethod().invoke(target, soruceProperty[i].getReadMethod().invoke(source));
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
