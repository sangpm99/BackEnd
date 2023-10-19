package com.example.polyfood.Controllers;

import com.example.polyfood.Config.GsonConfig;
import com.example.polyfood.Models.Account;
import com.example.polyfood.Services.AccountServices;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("account/")
@CrossOrigin
public class AccountController {
    @Autowired
    private AccountServices accountServices;
    private final Gson gson = GsonConfig.getGsonWithLocalDateTimeAdapter();

    @RequestMapping(value = "updateaccount", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Account updateAccount(@RequestBody String account) {
        Account account1 = gson.fromJson(account, Account.class);
        return accountServices.updateAccount(account1);
    }

    @RequestMapping(value = "deleteaccount", method = RequestMethod.DELETE)
    @DeleteMapping("deleteaccount")
    public Account deleteAccount(@RequestParam int id) {
        return accountServices.deleteAccount(id);
    }

    @RequestMapping(value = "findaccountbyusername", method = RequestMethod.GET)
    public Account findAccount(@RequestParam String userName) {
        return accountServices.findAccountByUserName(userName);
    }
}
