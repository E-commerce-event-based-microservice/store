package com.group16.common.domain;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group16.common.utils.BeanUtils;
import com.group16.common.utils.CollUtils;
import com.group16.common.utils.Convert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data // Lombok annotation for getters, setters, toString, equals, and hashCode methods.
@NoArgsConstructor // Lombok annotation to generate a no-arguments constructor.
@AllArgsConstructor // Lombok annotation to generate a constructor with one argument per class field.
public class PageDTO<T> {
    protected Long total; // Total number of items across all pages.
    protected Long pages; // Total number of pages.
    protected List<T> list; // List of items for the current page.

    // Creates an empty PageDTO object with specified total number of items and pages.
    public static <T> PageDTO<T> empty(Long total, Long pages) {
        return new PageDTO<>(total, pages, CollUtils.emptyList());
    }

    // Creates an empty PageDTO object based on MyBatis Plus Page object.
    public static <T> PageDTO<T> empty(Page<?> page) {
        return new PageDTO<>(page.getTotal(), page.getPages(), CollUtils.emptyList());
    }

    // Converts a MyBatis Plus Page object to a PageDTO object.
    public static <T> PageDTO<T> of(Page<T> page) {
        if (page == null) {
            return new PageDTO<>();
        }
        if (CollUtils.isEmpty(page.getRecords())) {
            return empty(page);
        }
        return new PageDTO<>(page.getTotal(), page.getPages(), page.getRecords());
    }

    // Converts a MyBatis Plus Page object to a PageDTO object using a custom mapper function.
    public static <T, R> PageDTO<T> of(Page<R> page, Function<R, T> mapper) {
        if (page == null) {
            return new PageDTO<>();
        }
        if (CollUtils.isEmpty(page.getRecords())) {
            return empty(page);
        }
        return new PageDTO<>(page.getTotal(), page.getPages(), page.getRecords().stream().map(mapper).collect(Collectors.toList()));
    }

    // Creates a PageDTO object from a MyBatis Plus Page object and a list of items.
    public static <T> PageDTO<T> of(Page<?> page, List<T> list) {
        return new PageDTO<>(page.getTotal(), page.getPages(), list);
    }

    // Converts a MyBatis Plus Page object to a PageDTO object, copying properties to a new list of specified class type.
    public static <T, R> PageDTO<T> of(Page<R> page, Class<T> clazz) {
        return new PageDTO<>(page.getTotal(), page.getPages(), BeanUtils.copyList(page.getRecords(), clazz));
    }

    // Converts a MyBatis Plus Page object to a PageDTO object, copying properties using a specific conversion strategy.
    public static <T, R> PageDTO<T> of(Page<R> page, Class<T> clazz, Convert<R, T> convert) {
        return new PageDTO<>(page.getTotal(), page.getPages(), BeanUtils.copyList(page.getRecords(), clazz, convert));
    }
}
