package com.group16.common.utils;

import cn.hutool.core.bean.BeanUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Extends from hutool's BeanUtil, adding functionality to customize conversions during bean transformation.
 */
public class BeanUtils extends BeanUtil {

    /**
     * Transforms the source object into a target object, using a converter for fields that do not match directly.
     *
     * @param source  The source object.
     * @param clazz   The class of the target object.
     * @param convert The converter function to apply additional transformations.
     * @param <R>     The type of the source object.
     * @param <T>     The type of the target object.
     * @return The transformed target object.
     */
    public static <R, T> T copyBean(R source, Class<T> clazz, Convert<R, T> convert) {
        T target = copyBean(source, clazz); // Convert basic fields from source to target
        if (convert != null) {
            convert.convert(source, target); // Apply custom conversion logic
        }
        return target;
    }

    /**
     * Transforms the source object into a target object. This is a basic transformation that directly maps fields.
     *
     * @param source The source object.
     * @param clazz  The class of the target object.
     * @param <R>    The type of the source object.
     * @param <T>    The type of the target object.
     * @return The transformed target object.
     */
    public static <R, T> T copyBean(R source, Class<T> clazz){
        if (source == null) {
            return null; // Return null if source is null to avoid NullPointerException.
        }
        return toBean(source, clazz); // Utilize Hutool's method to convert source to target type.
    }

    /**
     * Copies a list of source objects to a list of target objects without custom transformations.
     *
     * @param list  The list of source objects.
     * @param clazz The class of the target objects.
     * @param <R>   The type of the source objects.
     * @param <T>   The type of the target objects.
     * @return The list of transformed target objects.
     */
    public static <R, T> List<T> copyList(List<R> list, Class<T> clazz) {
        if (list == null || list.isEmpty()) {
            return CollUtils.emptyList(); // Return an empty list if source is null or empty.
        }
        return copyToList(list, clazz); // Convert each item in the source list to the target type.
    }

    /**
     * Copies a list of source objects to a list of target objects, applying a custom converter for each element.
     *
     * @param list    The list of source objects.
     * @param clazz   The class of the target objects.
     * @param convert The converter function to apply additional transformations for each element.
     * @param <R>     The type of the source objects.
     * @param <T>     The type of the target objects.
     * @return The list of transformed target objects.
     */
    public static <R, T> List<T> copyList(List<R> list, Class<T> clazz, Convert<R, T> convert) {
        if (list == null || list.isEmpty()) {
            return CollUtils.emptyList(); // Return an empty list if source is null or empty.
        }
        return list.stream().map(r -> copyBean(r, clazz, convert)).collect(Collectors.toList()); // Apply custom conversion to each item.
    }
}
