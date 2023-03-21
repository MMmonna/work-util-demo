package com.example.demomonna;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author： Monna
 * @CreateTime:2023-02-08 14:13:23
 * @Descrption: 复制类缓存类
 */
public class BeanCopierWithCacheUtil {
    private static final Map<String, BeanCopier> BEAN_COPIERS = new HashMap<>();

    /**
     * @param srcObj 原对象
     * @param destObj 目标对象
     * @param useConverter useConverter 是否使用转换器 如果同类 同字段 false 否则需要自定义转换器
     * @param converter 如果使用自定义转换器 此处即为转换器
     * @description 由于copier生成费时，此处使用缓存技术
     * @author:Monna
     * @time: 2023/2/8 14:19
     */
    public static void copy(Object srcObj, Object destObj,boolean useConverter, Converter  converter) {
        String key = genKey(srcObj.getClass(), destObj.getClass());
        BeanCopier copier = null;
        if (!BEAN_COPIERS.containsKey(key)) {
            copier = BeanCopier.create(srcObj.getClass(), destObj.getClass(), useConverter);
            BEAN_COPIERS.put(key, copier);
        } else {
            copier = BEAN_COPIERS.get(key);
        }
        copier.copy(srcObj, destObj, converter);

    }
    private static String genKey(Class<?> srcClazz, Class<?> destClazz) {
        return srcClazz.getName()+":" + destClazz.getName();
    }

}
