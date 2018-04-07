package com.sunshine.publicserver.service.markup.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public class XmlUtils {
	@SuppressWarnings("rawtypes")
	public static String parseXml(Object obj) throws Exception {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement(obj.getClass().getSimpleName());
		Class clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			addElement(obj, root, field);
		}
		return doc.asXML();
	}

	@SuppressWarnings("unchecked")
	private static void addElement(Object obj, Element parent, Field field)
			throws IllegalAccessException {
		field.setAccessible(true);
		Object val = field.get(obj);
		if (null == val) {
			return;
		}
		Element child = parent.addElement(field.getName());
		if ("interface java.util.List".equals(field.getType().toString())) {
			List<Object> item = (List<Object>) val;
			for (Object o : item) {
				Element gg = child.addElement(o.getClass().getSimpleName());
				for (Field f : o.getClass().getDeclaredFields()) {
					addElement(o, gg, f);
				}
			}
		} else if ("interface java.util.Set".equals(field.getType().toString())) {
			Set<Object> item = (Set<Object>) val;
			for (Object o : item) {
				Element gg = child.addElement(o.getClass().getSimpleName());
				for (Field f : o.getClass().getDeclaredFields()) {
					addElement(o, gg, f);
				}
			}
		} else if ("class [Ljava.lang.String;".equals(field.getType().toString())
				|| "class [Ljava.lang.Double;".equals(field.getType().toString())
				|| "class [Ljava.lang.Integer;".equals(field.getType().toString())
				|| "class [Ljava.lang.Boolean;".equals(field.getType().toString())) {
			Object[] strArr = (Object[]) val;
			for (Object str : strArr) {
				child.addElement(str.getClass().getSimpleName()).setText(str.toString());
			}
		} else {
			child.setText(val.toString());
		}
	}
}
