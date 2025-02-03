//package com.example.ProductService.Payment_Gateway;
//
//import com.razorpay.PaymentLink;
//import com.razorpay.RazorpayClient;
//import com.stripe.exception.StripeException;
//import org.json.JSONObject;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class RazorpayPaymentGateway implements PaymentGatewayInterface{
//
//    public String createPaymentLink(long orderId, long amount) throws Exception{
//        RazorpayClient razorpay = new RazorpayClient("[YOUR_KEY_ID]", "[YOUR_KEY_SECRET]");
//        JSONObject paymentLinkRequest = new JSONObject();
//        paymentLinkRequest.put("amount", amount); //1000 => 10
//        // 1.0000001 = 0.99999998999
//        // 149.99 => 14999
//        // 200 => 20000
//        paymentLinkRequest.put("currency","INR");
//        paymentLinkRequest.put("accept_partial",true);
//        paymentLinkRequest.put("first_min_partial_amount",100);
//        paymentLinkRequest.put("expire_by",1726595439);
//        paymentLinkRequest.put("reference_id",String.valueOf(orderId));
//        paymentLinkRequest.put("description","Payment for policy no #23456");
//        JSONObject customer = new JSONObject();
//        customer.put("name","+919000090000");
//        customer.put("contact","Gaurav Kumar");
//        customer.put("email","gaurav.kumar@example.com");
//        paymentLinkRequest.put("customer",customer);
//        JSONObject notify = new JSONObject();
//        notify.put("sms",true);
//        notify.put("email",true);
//        paymentLinkRequest.put("notify",notify);
//        paymentLinkRequest.put("reminder_enable",true);
//        JSONObject notes = new JSONObject();
//        notes.put("policy_name","Jeevan Bima");
//        paymentLinkRequest.put("notes",notes);
//        paymentLinkRequest.put("callback_url","https://example-callback-url.com/");
//        paymentLinkRequest.put("callback_method","get");
//
//        PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);
//
//        return payment.get("short_url");
//    }
//
//}
