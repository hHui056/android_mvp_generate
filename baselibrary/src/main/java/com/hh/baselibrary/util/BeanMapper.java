package com.hh.baselibrary.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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
            R target = targetClazz.newInstance();
            Class sourceClazz = source.getClass();

            Field[] sourceFields = sourceClazz.getDeclaredFields();
            Field[] targetFields = target.getClass().getDeclaredFields();
            for (Field sourceField : sourceFields) {
                sourceField.setAccessible(true);
                for (Field targetField : targetFields) {
                    if (targetField.getName().equals(sourceField.getName()) && targetField.getType() == sourceField.getType()) {
                        int mod = targetField.getModifiers();
                        if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                            continue;
                        }
                        targetField.setAccessible(true);
                        targetField.set(target, sourceField.get(source));
                        break;
                    }
                }
            }
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

