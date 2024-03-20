package com.group16.controller;

import com.group16.domain.dto.ShippingAddressDTO;
import com.group16.service.IShippingAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@Tag(name = "Address Management Interfaces")
@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {
    private final IShippingAddressService addressService;

    @GetMapping("/shippingAddress")
    @Operation(summary = "Query ShippingAddress for current user")
    public List<ShippingAddressDTO> findMyAddresses() {
//
//        // 1.search list
//        List<ShippingAddress> list = addressService.query().eq("user_id", currentUsername).list();
//
//        // 2.judge empty
//        if (list == null || list.isEmpty()) {
//            return Collections.emptyList();
//        }
//
//        // 3.convert to DTO
//        return list.stream().map(address -> {
//            ShippingAddressDTO dto = new ShippingAddressDTO();
//            org.springframework.beans.BeanUtils.copyProperties(address, dto);
//            return dto;
//        }).collect(Collectors.toList());
        return Collections.emptyList();
    }
}
