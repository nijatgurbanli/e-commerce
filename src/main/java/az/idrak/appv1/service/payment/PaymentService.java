package az.idrak.appv1.service.payment;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    public PaymentService() {
        Stripe.apiKey = "sk_test_51QhUqkCaljq3WKk7pbSooInsARC3WaUUNENFfw17Ir2FaX2i54o3xj31UCrUGjRTDheNMRg5aYh6EmetqRjy0a9s00NR9U08dr";
    }

    public PaymentIntent createPaymentIntent(Long amount, String currency) throws Exception {
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(amount) // Məbləğ sentlə (məsələn, 1000 = 10 AZN)
                        .setCurrency(currency)
//                        .setPaymentMethod(paymentMethodId)
//                        .setConfirm(true)
                       // .setPaymentMethod(List.of("card").toString())
                        .build();

        return PaymentIntent.create(params);
    }
}
