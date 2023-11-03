package com.example.polyfood.Controllers;

import com.example.polyfood.Services.LoginServices;
import com.example.polyfood.dto.loginRequest;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login/")
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginServices loginServices;

    @RequestMapping(value = "login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void sendMail(@RequestBody loginRequest login) throws MessagingException {
        String userName = login.getUserName();
        String password = login.getPassword();
        loginServices.sendMailCode(userName, password);
    }

    @RequestMapping(value = "verifycode", method = RequestMethod.GET)
    public String isRightCode(@RequestParam String code) {
        return loginServices.isRightCode(code);
    }
}
