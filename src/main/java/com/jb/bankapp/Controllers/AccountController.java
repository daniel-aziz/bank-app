package com.jb.bankapp.Controllers;

import com.jb.bankapp.Beans.Account;
import com.jb.bankapp.Beans.Enums.Status;
import com.jb.bankapp.Beans.Enums.SysMsg;
import com.jb.bankapp.Repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountRepository accountRepository;

    @GetMapping("/{accountNumber}/currentAmount")
    public ResponseEntity<?> currentAmount(@PathVariable String accountNumber) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        if (account == null) return new ResponseEntity<>(SysMsg.NOT_FOUND, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(account.getCurrentAmount(), HttpStatus.OK);
    }

    @PutMapping("/{accountNumber}/deposit/{amount}")
    public ResponseEntity<?> deposit(@PathVariable String accountNumber, @PathVariable double amount) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        if (account == null) return new ResponseEntity<>(SysMsg.NOT_FOUND, HttpStatus.BAD_REQUEST);
        account.setCurrentAmount(account.getCurrentAmount() + amount);
        accountRepository.save(account);
        return new ResponseEntity<>(SysMsg.SUCCESSFUL, HttpStatus.OK);
    }

    @PutMapping("/{accountNumber}/withdraw/{amount}")
    public ResponseEntity<?> withdraw(@PathVariable String accountNumber, @PathVariable double amount) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        if (account == null) return new ResponseEntity<>(SysMsg.NOT_FOUND, HttpStatus.BAD_REQUEST);
        if (account.getCurrentAmount() - amount >= 0) {
            account.setCurrentAmount(account.getCurrentAmount() - amount);
            accountRepository.save(account);
            return new ResponseEntity<>(SysMsg.SUCCESSFUL, HttpStatus.OK);
        }
        return new ResponseEntity<>(SysMsg.FAILED, HttpStatus.BAD_REQUEST);
    }



    @PutMapping("/{accountNumber}/changeStatus/{status}")
    public ResponseEntity<?> changeStatus(@PathVariable String accountNumber, @PathVariable Status status) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        if (account == null) return new ResponseEntity<>(SysMsg.NOT_FOUND, HttpStatus.BAD_REQUEST);
        account.setStatus(status);
        accountRepository.save(account);
        return new ResponseEntity<>(SysMsg.SUCCESSFUL, HttpStatus.BAD_REQUEST);
    }

}
