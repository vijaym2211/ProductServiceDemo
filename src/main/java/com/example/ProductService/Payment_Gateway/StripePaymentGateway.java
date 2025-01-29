package com.example.ProductService.Payment_Gateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.param.PaymentLinkCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Primary
@Component
public class StripePaymentGateway implements PaymentGatewayInterface{

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    public String createPaymentLink(long orderId, long amount) throws StripeException {

        //We are calling Stripe to do our payment
        Stripe.apiKey = stripeSecretKey;

        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice("price_1QlWPgCRZ8JKHRyFFgLjs8LC")
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                            .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                            .setRedirect(
                                    PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                            .setUrl("https://scaler.com")
                                            .build()
                            ).build()
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
