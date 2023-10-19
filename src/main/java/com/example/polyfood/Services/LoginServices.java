package com.example.polyfood.Services;

import com.example.polyfood.Config.SendMailConfig;
import com.example.polyfood.Models.Account;
import com.example.polyfood.Repositories.AccountRepository;
import com.example.polyfood.Validation.ValidationNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.example.polyfood.Validation.ValidationNumberGenerator.storeValidation;

@Service
public class LoginServices {
    @Autowired
    private SendMailConfig sendEmail;
    @Autowired
    private AccountRepository accountRepository;

    public String login(String userName, String passWord) {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

        List<Account> accounts = accountRepository.findAll();
        for(Account account: accounts) {
            if(account.getUserName().equals(userName) && bCrypt.matches(passWord, account.getPassword())) {
                return account.getEmail();
            }
        }
        System.out.println(passWord);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tài khoản hoặc mật khẩu không chính xác");
    }

    public String sendMail(String userName, String passWord) {
        String email = login(userName, passWord);
        ValidationNumberGenerator.generateValidationNumber(email);
        String code = "";
        for(String[] item: storeValidation) {
            if(item[0].equals(email)) {
                code = item[1];
            }
        }
        if(!(code.isEmpty())) {
            sendEmail.sendEmail(email,
                    "Verify Email Code",
                    "Verify Code: " + code);
        }
        return email;
    }

    public void sendMailCode(String userName, String passWord) {
        String email = sendMail(userName, passWord);
        if(!email.isEmpty()) {
            ValidationNumberGenerator.expireValidation(email);
        }
    }

    public String isRightCode(String code) {
        for(String[] item: storeValidation) {
            if(item[1].equals(code)) {
                return "Đã xác minh địa chỉ email, Đăng nhập thành công";
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mã xác nhận không chính xác hoặc đã hết hạn");
    }

}