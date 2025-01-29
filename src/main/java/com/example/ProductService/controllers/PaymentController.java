package com.example.ProductService.controllers;

import com.example.ProductService.dtos.CreatePaymentLinkRequestDto;
import com.example.ProductService.dtos.CreatePaymentLinkResponseDto;
import com.example.ProductService.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/paymentlink")
    public ResponseEntity<CreatePaymentLinkResponseDto> createPaymentLink(@RequestBody CreatePaymentLinkRequestDto requestDto){
        try{
            String url = paymentService.createPaymentLink(requestDto.getOrderId());
            CreatePaymentLinkResponseDto responseDto = new CreatePaymentLinkResponseDto();
            responseDto.setUrl(url);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            System.out.printf(e.getMessage());
            return null;
        }

    }



}
