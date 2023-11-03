package com.example.polyfood.Controllers;

import com.example.polyfood.Models.Account;
import com.example.polyfood.Services.ForgotPasswordServices;
import com.example.polyfood.dto.ForgotPasswordRequest;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("forgotpassword/")
@CrossOrigin
public class ForgotPasswordController {
    @Autowired
    private ForgotPasswordServices forgotPasswordServices;

    @RequestMapping(value = "sendmail", method = RequestMethod.GET)
    public void sendMail(@RequestParam String email) throws MessagingException {
        forgotPasswordServices.forgotPassword(email);
    }

    @RequestMapping(value = "verifycode", method = RequestMethod.GET)
    public String isRightCode(@RequestParam String code) {
        return forgotPasswordServices.isRightCode(code);
    }

    @RequestMapping(value = "setpassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void setPassword(@RequestBody ForgotPasswordRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        String rePassword = request.getRePassword();
        forgotPasswordServices.setPassword(email, password, rePassword);
    }
}
