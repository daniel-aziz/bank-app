package com.jb.bankapp.Controllers;

import com.jb.bankapp.Beans.Account;
import com.jb.bankapp.Repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountRepository accountRepository;


    @GetMapping("/{id}")
    public Account getOneAccount(@PathVariable String id) {
        return accountRepository.findAccountById(id);
    }

}
