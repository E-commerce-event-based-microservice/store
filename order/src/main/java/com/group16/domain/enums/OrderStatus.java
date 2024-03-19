package com.group16.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum OrderStatus {

        CANCELLED("CANCELLED"),
        PROCESSING("PROCESSING"),
        SHIPPED("SHIPPED"),
        DELIVERED("DELIVERED");

        @EnumValue
        private final String status;

        OrderStatus(String status) {
                this.status = status;
        }

        public String getStatus() {
                return this.status;
        }
}