package com.example.polyfood.Controllers;

import com.example.polyfood.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/payment")
@CrossOrigin
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @RequestMapping(value = "geturlpayment", method = RequestMethod.GET)
    public String getUrlPayment (@RequestParam long amount) throws UnsupportedEncodingException {
        return paymentService.getUrlPayment(amount);
    }
}
