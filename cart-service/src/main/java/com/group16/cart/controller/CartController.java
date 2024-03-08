package com.group16.cart.controller;

import com.group16.cart.domain.dto.CartFormDTO;
import com.group16.cart.domain.po.Cart;
import com.group16.cart.domain.vo.CartVO;
import com.group16.cart.service.ICartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cart-related interfaces")
@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {
    private final ICartService cartService;

    @Operation(summary = "Add product to shopping cart")
    @PostMapping
    public void addItem2Cart(@Valid @RequestBody CartFormDTO cartFormDTO) {
        cartService.addItem2Cart(cartFormDTO);
    }

    @Operation(summary = "Update shopping cart data")
    @PutMapping
    public void updateCart(@RequestBody Cart cart) {
        cartService.updateById(cart);
    }

    @Operation(summary = "Delete product from shopping cart")
    @DeleteMapping("{id}")
    public void deleteCartItem(@Parameter(description = "Shopping cart item id") @PathVariable("id") Long id) { // Updated from @Param
        cartService.removeById(id);
    }

    @Operation(summary = "Query shopping cart list")
    @GetMapping
    public List<CartVO> queryMyCarts() {
        return cartService.queryMyCarts();
    }

    @Operation(summary = "Batch delete products from shopping cart")
    @Parameter(name = "ids", description = "Collection of shopping cart item ids") // Updated from @ApiImplicitParam
    @DeleteMapping
    public void deleteCartItemByIds(@RequestParam("ids") List<Long> ids) {
        cartService.removeByItemIds(ids);
    }
}
