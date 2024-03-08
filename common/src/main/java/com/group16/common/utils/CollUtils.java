package com.group16.common.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.util.NumberUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A collection utility class that extends from Hutool's CollectionUtil.
 */
public class CollUtils extends CollectionUtil {

    // Returns an immutable empty list.
    public static <T> List<T> emptyList() {
        return Collections.emptyList();
    }

    // Returns an immutable empty set.
    public static <T> Set<T> emptySet() {
        return Collections.emptySet();
    }

    // Returns an immutable empty map.
    public static <K, V> Map<K, V> emptyMap() {
        return Collections.emptyMap();
    }

    // Returns an immutable set containing only the specified object.
    public static <T> Set<T> singletonSet(T t) {
        return Collections.singleton(t);
    }

    // Returns an immutable list containing only the specified object.
    public static <T> List<T> singletonList(T t) {
        return Collections.singletonList(t);
    }

    // Converts a list of strings to a list of integers.
    public static List<Integer> convertToInteger(List<String> originList){
        return CollUtils.isNotEmpty(originList) ? originList.stream().map(NumberUtil::parseInt).collect(Collectors.toList()) : null;
    }

    // Converts a list of strings to a list of longs.
    public static List<Long> convertToLong(List<String> originList){
        return CollUtils.isNotEmpty(originList) ? originList.stream().map(NumberUtil::parseLong).collect(Collectors.toList()) : null;
    }

    /**
     * Converts a collection to a string with a given delimiter.
     * If the collection's elements are arrays, Iterable, or Iterator,
     * it recursively joins them into a string.
     *
     * @param collection The collection to be joined.
     * @param conjunction The delimiter to separate the elements.
     * @param <T> The type of elements in the collection.
     * @return A string resulting from the joining of the collection elements.
     * See Also: IterUtil.join(Iterator, CharSequence)
     */
    public static <T> String join(Collection<T> collection, CharSequence conjunction) {
        if (null == collection || collection.isEmpty()) {
            return null;
        }
        return IterUtil.join(collection.iterator(), conjunction);
    }

    // Joins a collection into a string with a given delimiter, ignoring null elements.
    public static <T> String joinIgnoreNull(Collection<T> collection, CharSequence conjunction) {
        if (null == collection || collection.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (T t : collection) {
            if (t == null) continue; // Ignore null elements
            sb.append(t).append(conjunction);
        }
        if (sb.length() <= 0) {
            return null;
        }
        // Remove the last delimiter
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}
