package com.example.polyfood.Controllers;

import com.example.polyfood.Config.GsonConfig;
import com.example.polyfood.Models.Account;
import com.example.polyfood.Services.RegisterServices;
import com.google.gson.Gson;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("register/")
@CrossOrigin
public class RegisterController {
    @Autowired
    private RegisterServices registerServices;
    private final Gson gson = GsonConfig.getGsonWithLocalDateTimeAdapter();

    @RequestMapping(value = "sendmail", method = RequestMethod.GET)
    public void sendMail(@RequestParam String email) throws MessagingException {
        registerServices.sendMailCode(email);
    }

    @RequestMapping(value = "verifycode", method = RequestMethod.GET)
    public String isRightCode(@RequestParam String code) {
        return registerServices.isRightCode(code);
    }

    @RequestMapping(value = "addaccount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Account addAccount(@RequestBody String account) {
        Account account1 = gson.fromJson(account, Account.class);
        return registerServices.addAccount(account1);
    }
}
