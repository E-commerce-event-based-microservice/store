package com.group16.order.controller;

import com.group16.order.domain.dto.ShippingAddressDTO;
import com.group16.order.domain.po.ShippingAddress;
import com.group16.order.service.IShippingAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Address Management Interfaces")
@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {
    private final IShippingAddressService addressService;

    @GetMapping("/shippingAddress")
    @Operation(summary = "Query ShippingAddress for current user")
    public List<ShippingAddressDTO> findMyAddresses() {
        // Assuming your security context holds the user details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName(); // Or another method to obtain the unique identifier

        // 1.search list
        List<ShippingAddress> list = addressService.query().eq("user_id", currentUsername).list();

        // 2.judge empty
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }

        // 3.convert to DTO
        return list.stream().map(address -> {
            ShippingAddressDTO dto = new ShippingAddressDTO();
            org.springframework.beans.BeanUtils.copyProperties(address, dto);
            return dto;
        }).collect(Collectors.toList());
    }
}
