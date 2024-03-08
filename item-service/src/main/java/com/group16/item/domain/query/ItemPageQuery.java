package com.group16.item.domain.query;

import com.group16.common.domain.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Class representing the search and filtering criteria for querying items with pagination.
 */
@EqualsAndHashCode(callSuper = true) // Inherits equals and hashCode from PageQuery.
@Data // Lombok annotation for getter, setter, toString, equals, and hashCode methods.
@Schema(description = "Item pagination query conditions") // Documentation annotation for OpenAPI.
public class ItemPageQuery extends PageQuery {
    @Schema(description = "Search keyword") // Documentation for the field.
    private String key;

    @Schema(description = "Item category") // Documentation for the field.
    private String category;

    @Schema(description = "Item brand") // Documentation for the field.
    private String brand;

    @Schema(description = "Minimum price") // Documentation for the field.
    private Integer minPrice;

    @Schema(description = "Maximum price") // Documentation for the field.
    private Integer maxPrice;
}
