package com.hh.baselibrary.util;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.List;

/**
 * <对象转换工具类>
 *
 * @author : hHui
 * @date : 2021年3月24日13:49
 */
public final class BeanMapper {
    /**
     * throws InstantiationException, IllegalAccessException
     *
     * @param source      原对象
     * @param targetClazz 目标类
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> R map(T source, Class<R> targetClazz) {
        try {
            String sourceStr = JSON.toJSONString(source);
            R target = JSON.parseObject(sourceStr, targetClazz);
            return target;
        } catch (Exception e) {
            return null;
        }
    }


    public static <S, T> List<T> map2List(List<S> list, Class<T> targetClass) {
        try {
            List<T> listT = new LinkedList<>();
            for (S source : list) {
                T t = map(source, targetClass);
                listT.add(t);
            }
            return listT;
        } catch (Exception e) {
            return null;
        }
    }

    public static <S, T> List<T> map2List(S[] array, Class<T> targetClass) {
        try {
            List<T> listT = new LinkedList<>();
            for (S source : array) {
                T t = map(source, targetClass);
                listT.add(t);
            }
            return listT;
        } catch (Exception e) {
            return null;
        }
    }
}

