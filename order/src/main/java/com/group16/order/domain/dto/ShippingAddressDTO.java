package com.group16.order.domain.dto;

import lombok.Data;

@Data
public class ShippingAddressDTO {

    private Long shippingAddressId;
    private String fullName;
    private String phone;
    private String streetName;
    private String houseNumber;
    private String city;
    private String country;
    private Long userId;

}
