package com.example.polyfood.Services;

import com.example.polyfood.Config.GsonConfig;
import com.example.polyfood.Config.SendMailConfig;
import com.example.polyfood.Models.Account;
import com.example.polyfood.Repositories.AccountRepository;
import com.example.polyfood.Validation.ValidationNumberGenerator;
import com.google.gson.Gson;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.polyfood.Validation.ValidationNumberGenerator.storeValidation;

@Service
public class ForgotPasswordServices {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SendMailConfig sendEmail;
    private final Gson gson = GsonConfig.getGsonWithLocalDateTimeAdapter();

    public void sendMail (String email) throws MessagingException {
        List<Account> accounts = accountRepository.findAll();
        for(Account acc: accounts) {
            if(acc.getEmail().equals(email)) {
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
                break;
            }
        }
    }

    public void forgotPassword(String email) throws MessagingException {
        sendMail(email);
        ValidationNumberGenerator.expireValidation(email);
    }

    public String isRightCode(String code) {
        for(String[] item: storeValidation) {
            if(item[1].equals(code)) {
                Account acc = new Account();
                List<Account> accounts = accountRepository.findAll();
                for(Account account: accounts) {
                    if(account.getEmail().equals(item[0])) {
                        acc = account;
                    }
                }
                acc.setStatus(2);
                accountRepository.save(acc);
                return "Đã xác minh địa chỉ email, hãy đổi mật khẩu mới";
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mã xác nhận không chính xác hoặc đã hết hạn");
    }

    public void setPassword (String email, String password, String rePassword) {
        if(!password.equals(rePassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mật khẩu và nhập lại mật khẩu không trùng khớp");
        }
        if(password.length() < 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mật khẩu quá ngắn");
        }

        List<Account> accounts = accountRepository.findAll();
        List<Account> accCounts = new ArrayList<>();

        for(Account acc: accounts) {
            if(acc.getStatus() == 2) {
                accCounts.add(acc);
            }
        }

        for(Account acc: accCounts) {
            if(email.equals(acc.getEmail())) {
                Account account = acc;
                BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
                String enCryptPassword = bCrypt.encode(password);

                account.setPassword(enCryptPassword);
                account.setStatus(1);
                account.setUpdateAt(LocalDateTime.now());
                accountRepository.save(account);
                return;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Chưa xác minh email");
    }
}
