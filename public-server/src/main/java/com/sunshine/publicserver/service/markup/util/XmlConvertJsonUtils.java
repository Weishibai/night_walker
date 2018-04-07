package com.sunshine.publicserver.service.markup.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.List;

/**
 * XML转换JSON
 * @author Administrator
 *
 */
public class XmlConvertJsonUtils {
	
	@SuppressWarnings("unchecked")
	public static String parseJSON(String str) throws DocumentException {
		Document doc = DocumentHelper.parseText(str);
		Element root = doc.getRootElement();
		List<Element> elements = root.elements();
		JSONObject json = new JSONObject();
		for (Element element : elements) {
			json.put(element.getName(), getObject(element));
		}
		return json.toJSONString();
	}

	@SuppressWarnings("unchecked")
	private static Object getObject(Element element) {
		List<Element> list = element.elements();
		if (null == list || 0 == list.size()) {
			return element.getText();
		}
		if ((1 == list.size() && null != list.get(0).elements() && list.get(0)
				.elements().size() > 0)
				|| (list.size() > 1 && list.get(0).getName()
						.equals(list.get(1).getName()))) {
			JSONArray arr = new JSONArray();
			for (Element child : list) {
				JSONObject jsonChild = new JSONObject();
				List<Element> eleList = child.elements();
				for (Element cc : eleList) {
					jsonChild.put(cc.getName(), getObject(cc));
				}
				arr.add(jsonChild);
			}
			return arr;
		} else {
			JSONObject jsonChild = new JSONObject();
			for (Element child : list) {
				jsonChild.put(child.getName(), getObject(child));
			}
			return jsonChild;
		}
	}
}
