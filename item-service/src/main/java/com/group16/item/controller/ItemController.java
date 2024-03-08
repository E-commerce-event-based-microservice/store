package com.group16.item.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group16.api.dto.ItemDTO;
import com.group16.api.dto.OrderDetailDTO;
import com.group16.common.domain.PageDTO;
import com.group16.common.domain.PageQuery;
import com.group16.common.utils.BeanUtils;
import com.group16.item.domain.po.Item;
import com.group16.item.service.IItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Item Management Interfaces") // Updated
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final IItemService itemService;

    @Operation(summary = "Query items by page")
    @GetMapping("/page")
    public PageDTO<ItemDTO> queryItemByPage(PageQuery query) {
        // 1. Perform paging query
        Page<Item> result = itemService.page(query.toMpPage("update_time", false));
        // 2. Wrap and return
        return PageDTO.of(result, ItemDTO.class);
    }

    @Operation(summary = "Query items by batch of IDs") // Updated
    @GetMapping
    public List<ItemDTO> queryItemByIds(@RequestParam("ids") List<Long> ids) {
        return itemService.queryItemByIds(ids);
    }

    @Operation(summary = "Query item by ID")
    @GetMapping("{id}")
    public ItemDTO queryItemById(@Parameter(description = "Item ID") @PathVariable("id") Long id) { // Updated
        return BeanUtils.copyBean(itemService.getById(id), ItemDTO.class);
    }

    @Operation(summary = "Add a new item")
    @PostMapping
    public void saveItem(@RequestBody ItemDTO item) {
        itemService.save(BeanUtils.copyBean(item, Item.class));
    }

    @Operation(summary = "Update item status")
    @PutMapping("/status/{id}/{status}")
    public void updateItemStatus(@Parameter(description = "Item ID") @PathVariable("id") Long id, // Updated
                                 @Parameter(description = "Item status") @PathVariable("status") Integer status) { // Updated
        Item item = new Item();
        item.setId(id);
        item.setStatus(status);
        itemService.updateById(item);
    }

    @Operation(summary = "Update item") // Updated
    @PutMapping
    public void updateItem(@RequestBody ItemDTO item) {
        item.setStatus(null); // Ensure status is not updated this way
        itemService.updateById(BeanUtils.copyBean(item, Item.class));
    }

    @Operation(summary = "Delete item by ID") // Updated
    @DeleteMapping("{id}")
    public void deleteItemById(@Parameter(description = "Item ID") @PathVariable("id") Long id) { // Updated
        itemService.removeById(id);
    }

    @Operation(summary = "Batch reduce stock") // Updated
    @PutMapping("/stock/deduct")
    public void deductStock(@RequestBody List<OrderDetailDTO> items) {
        itemService.deductStock(items);
    }
}
