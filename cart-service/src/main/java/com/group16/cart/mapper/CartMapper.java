package com.group16.cart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group16.cart.domain.po.Cart;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * This interface serves as a mapper for the Cart entity, allowing for direct
 * interactions with the database for operations specific to Cart objects.
 */
public interface CartMapper extends BaseMapper<Cart> {

    /**
     * Updates the quantity (num) of a specific item in the cart by incrementing it.
     * This update is conditioned on matching both user ID and item ID.
     *
     * @param itemId The ID of the item in the cart whose quantity is to be updated.
     * @param userId The ID of the user owning the cart.
     */
    @Update("UPDATE cart SET num = num + 1 WHERE user_id = #{userId} AND item_id = #{itemId}")
    void updateNum(@Param("itemId") Long itemId, @Param("userId") Long userId);
}
