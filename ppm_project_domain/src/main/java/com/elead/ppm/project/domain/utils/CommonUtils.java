package com.elead.ppm.project.domain.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 帮助类
 * @author yanghuayong
 *
 */
public class CommonUtils {
	
	/**
	 * string 转 list
	 * @param string(字符以","分割)
	 * @return boolean
	 */
	public static List<String> strToList(String str){
		List<String> list = new ArrayList<String>();
		if(StringUtils.isNotNull(str)){
			String [] tempStr = str.split(",");
			for (String string : tempStr) {
				if(StringUtils.isNotNull(str)){
					list.add(string);
				}
			}
		}
		return list;
	}
	
	/**
	 * string 转 Set
	 * @param string(字符以","分割)
	 * @return boolean
	 */
	public static Set<String> strToSet(String str){
		Set<String> setList = new HashSet<String>();
		if(StringUtils.isNotNull(str)){
			String [] tempStr = str.split(",");
			for (String string : tempStr) {
				if(StringUtils.isNotNull(str)){
					setList.add(string);
				}
			}
		}
		return setList;
	}
	


}
