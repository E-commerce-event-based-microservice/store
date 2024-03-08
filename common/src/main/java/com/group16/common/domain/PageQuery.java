package com.group16.common.domain;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Paging query conditions.
 */

@Data
@Schema(description = "Paging query conditions") // Updated section
@Accessors(chain = true)
public class PageQuery {
    public static final Integer DEFAULT_PAGE_SIZE = 20; // Default page size if not specified.
    public static final Integer DEFAULT_PAGE_NUM = 1; // Default page number if not specified.

    @Schema(description = "Page number", minimum = "1") // Corrected part
    @Min(value = 1, message = "Page number cannot be less than 1")
    private Integer pageNo = DEFAULT_PAGE_NUM;

    @Schema(description = "Page size", minimum = "1") // Corrected part
    @Min(value = 1, message = "Page size cannot be less than 1")
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    @Schema(description = "Is ascending order") // Updated section
    private Boolean isAsc = true;

    @Schema(description = "Sort by field") // Updated section
    private String sortBy;

    // Calculate the offset for SQL queries based on page number and size.
    public int from() {
        return (pageNo - 1) * pageSize;
    }

    // Convert to MyBatis Plus Page object with specified order items.
    public <T> Page<T> toMpPage(OrderItem... orderItems) {
        Page<T> page = new Page<>(pageNo, pageSize);
        // Check if manual ordering is specified.
        if (orderItems != null && orderItems.length > 0) {
            for (OrderItem orderItem : orderItems) {
                page.addOrder(orderItem);
            }
            return page;
        }
        // Check if there is a sorting field from the front end.
        if (StrUtil.isNotEmpty(sortBy)) {
            OrderItem orderItem = new OrderItem();
            orderItem.setAsc(isAsc);
            orderItem.setColumn(sortBy);
            page.addOrder(orderItem);
        }
        return page;
    }

    // Convert to MyBatis Plus Page object with default sorting if not specified.
    public <T> Page<T> toMpPage(String defaultSortBy, boolean isAsc) {
        if (StringUtils.isBlank(sortBy)) {
            sortBy = defaultSortBy;
            this.isAsc = isAsc;
        }
        Page<T> page = new Page<>(pageNo, pageSize);
        OrderItem orderItem = new OrderItem();
        orderItem.setAsc(this.isAsc);
        orderItem.setColumn(sortBy);
        page.addOrder(orderItem);
        return page;
    }

    // Default conversion to MyBatis Plus Page object, sorted by creation time descending.
    public <T> Page<T> toMpPageDefaultSortByCreateTimeDesc() {
        return toMpPage("create_time", false);
    }
}
