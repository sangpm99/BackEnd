package com.example.polyfood.Controllers;

import com.example.polyfood.Config.GsonConfig;
import com.example.polyfood.Models.OrderDetail;
import com.example.polyfood.Models.Orders;
import com.example.polyfood.Services.OrderServices;
import com.example.polyfood.dto.InvoiceRequest;
import com.google.gson.Gson;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order/")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderServices orderServices;
    private final Gson gson = GsonConfig.getGsonWithLocalDateTimeAdapter();

    @RequestMapping(value = "addneworder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int addNewOrder(@RequestBody String orders) {
        Orders orders1 = gson.fromJson(orders, Orders.class);
        return orderServices.addNewOrder(orders1);
    }

    @RequestMapping(value = "addneworderdetail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addNewOrderDetail(@RequestParam int orderId, @RequestBody String orderDetail) {
        OrderDetail orderDetail1 = gson.fromJson(orderDetail, OrderDetail.class);
        orderServices.addNewOrderDetail(orderDetail1, orderId);
    }

    @RequestMapping(value = "sendinvoice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void sendInvoice(@RequestBody InvoiceRequest invoiceRequest) throws MessagingException {
        String email = invoiceRequest.getEmail();
        String body = invoiceRequest.getBody();
        orderServices.sendInvoice(email, body);
    }
}
