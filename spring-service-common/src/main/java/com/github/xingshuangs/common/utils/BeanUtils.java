package com.github.xingshuangs.common.utils;


import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author xingshuang
 * @date 2020/5/31
 */
public class BeanUtils {

    private BeanUtils() {

    }

    /**
     * 不在父类中的字段转换为Map
     *
     * @param obj 对象
     * @return map
     * @throws IllegalAccessException 访问异常
     */
    public static Map<String, Object> field2MapExcludeParent(Object obj) throws IllegalAccessException {
        Map<String, Object> map = Maps.newHashMap();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }
}
