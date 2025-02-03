package com.example.ProductService.dtos;


import com.example.ProductService.models.PGVendor;

public class CreatePaymentLinkRequestDto {
    private long orderId;
//    private PGVendor pgVendor;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

//    public PGVendor getPgVendor() {
//        return pgVendor;
//    }
//
//    public void setPgVendor(PGVendor pgVendor) {
//        this.pgVendor = pgVendor;
//    }
}
