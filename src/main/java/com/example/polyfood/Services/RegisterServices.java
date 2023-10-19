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
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import static com.example.polyfood.Validation.ValidationNumberGenerator.storeValidation;

@Service
public class RegisterServices {
    @Autowired
    private SendMailConfig sendEmail;
    @Autowired
    private AccountRepository accountRepository;

    public boolean isEmailExist(String email) {
        List<Account> accounts = accountRepository.findAll();
        for(Account account: accounts) {
            if(account.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public void sendMail(String email) {
        if(isEmailExist(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email đã tồn tại");
        }
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
    }

    public void sendMailCode(String email) {
        sendMail(email);
        ValidationNumberGenerator.expireValidation(email);
    }

    public String isRightCode(String code) {
        for(String[] item: storeValidation) {
            if(item[1].equals(code)) {
                Account account = new Account();
                account.setUserName("");
                account.setEmail(item[0]);
                account.setPassword("");
                account.setStatus(0);
                account.setDecentralizationId(3);
                account.setResetPasswordToken("");
                account.setResetPasswordTokenExpiry("");
                account.setCreatedAt(LocalDateTime.now());
                account.setUpdateAt(LocalDateTime.now());
                accountRepository.save(account);
                return "Đã xác minh địa chỉ email, hãy đăng ký";
                }
            }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mã xác nhận không chính xác hoặc đã hết hạn");
    }

    public Account addAccount(Account account) {
        List<Account> accounts = accountRepository.findAll();
        List<Account> accCounts = new ArrayList<>();
        for(Account acc: accounts) {
            if(acc.getUserName().equals(account.getUserName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username đã tồn tại");
            }
        }

        for(Account acc: accounts) {
            if(acc.getStatus() == 0) {
                accCounts.add(acc);
            }
        }

        for(Account acc: accCounts) {
            if(account.getEmail().equals(acc.getEmail())) {
                BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
                String enCryptPassword = bCrypt.encode(account.getPassword());

                account.setPassword(enCryptPassword);
                account.setStatus(1);
                account.setAccountId(acc.getAccountId());
                account.setDecentralizationId(3);
                account.setCreatedAt(LocalDateTime.now());
                account.setUpdateAt(LocalDateTime.now());
                accountRepository.save(account);
                return account;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Chưa xác minh email");
    }
}
