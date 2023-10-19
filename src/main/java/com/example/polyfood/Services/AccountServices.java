package com.example.polyfood.Services;

import com.example.polyfood.Models.Account;
import com.example.polyfood.Models.User;
import com.example.polyfood.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServices {
    @Autowired
    private AccountRepository accountRepository;

    public Account updateAccount(Account account) {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        String enCryptPassword = bCrypt.encode(account.getPassword());

        Optional<Account> account1 = accountRepository.findById(account.getAccountId());
        if(account1.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại ID tài khoản");
        }
        account1.get().setAccountId(account.getAccountId());
        account1.get().setUserName(account.getUserName());
        account1.get().setPassword(enCryptPassword);
        account1.get().setEmail(account.getEmail());
        account1.get().setUpdateAt(LocalDateTime.now());
        accountRepository.save(account1.get());
        return account1.get();
    }

    @DeleteMapping("deleteaccount")
    public Account deleteAccount(int id) {
        Optional<Account> account = accountRepository.findById(id);
        if(account.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại ID tài khoản");
        }
        accountRepository.delete(account.get());
        return account.get();
    }

    public Account findAccountByUserName(String userName) {
        List<Account> accounts = accountRepository.findAll();
        for(Account account: accounts) {
            if(account.getUserName().equals(userName)) {
                return account;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tồn tại username");
    }
}