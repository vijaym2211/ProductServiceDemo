package com.example.ProductService.Payment_Gateway;

import com.example.ProductService.models.Order;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Primary
@Component
public class StripePaymentGateway implements PaymentGatewayInterface{

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    public String createPaymentLink(Order order) throws StripeException {

        //We are calling Stripe to do our payment
        Stripe.apiKey = stripeSecretKey;

        // Calculate amount in cents
        long amountInCents = (long) (order.getTotalPrice() * 100); // Convert to cents

        // Create a price object dynamically
        Price price = Price.create(
                PriceCreateParams.builder()
                        .setCurrency("inr")  // Set currency to INR
                        .setUnitAmount(amountInCents) // Set amount in cents
                        .setProductData(
                                PriceCreateParams.ProductData.builder()
                                        .setName("Order #" + order.getId()) // Set product name
                                        .build()
                        )
                        .build()
        );

        // Create a payment link with the price object
        PaymentLinkCreateParams params = PaymentLinkCreateParams.builder()
                .addLineItem(
                        PaymentLinkCreateParams.LineItem.builder()
                                .setQuantity(order.getQuantity()) // Set quantity
                                .setPrice(price.getId()) // Use the created price object ID
                                .build()
                )
                .setAfterCompletion(
                        PaymentLinkCreateParams.AfterCompletion.builder()
                                .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                .setRedirect(
                                        PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                .setUrl("https://scaler.com") // Set redirect URL
                                                .build()
                                )
                                .build()
                )
                .build();
        /*
        {
            "line_items": [{
                    "price": "price_1Pzhi5LQWjpPGvlgfSCN3C72",
                    "qty": 1
                }
            ],
            "after_completion": {
                "type": "redirect",
                "redirect": {
                    "url": "https://scaler.com"
                }
            }
        }

         */

        PaymentLink paymentLink = PaymentLink.create(params);

        return paymentLink.getUrl();
    }
}
