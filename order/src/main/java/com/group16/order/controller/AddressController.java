package com.group16.order.controller;

import com.group16.common.utils.BeanUtils;
import com.group16.common.utils.CollUtils;
import com.group16.common.utils.UserContext;
import com.group16.order.domain.dto.ShippingAddressDTO;
import com.group16.order.domain.po.ShippingAddress;
import com.group16.order.service.IShippingAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Address Management Interfaces") // Updated
@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {
    private final IShippingAddressService addressService;

    @GetMapping("/shippingAddress")
    @Operation(summary = "Query ShippingAddress for current user")
    public List<ShippingAddressDTO> findMyAddresses() {
        // 1.search list
        List<ShippingAddress> list = addressService.query().eq("user_id", UserContext.getUser()).list();
        // 2.judge empty
        if (CollUtils.isEmpty(list)) {
            return CollUtils.emptyList();
        }
        // 3.convert to vo
        return BeanUtils.copyList(list, ShippingAddressDTO.class);
    }
}
