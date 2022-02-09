package com.jb.bankapp.Controllers;

import com.jb.bankapp.Beans.Account;
import com.jb.bankapp.Beans.Bank;
import com.jb.bankapp.Beans.Client;
import com.jb.bankapp.Beans.Enums.SysMsg;
import com.jb.bankapp.Repositories.AccountRepository;
import com.jb.bankapp.Repositories.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/bank")
@RequiredArgsConstructor
public class BankController {
    private final BankRepository bankRepository;
    private final AccountRepository accountRepository;

    @GetMapping("all")
    public ResponseEntity<?> getAllBanks() {
        return new ResponseEntity<>(bankRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("getOne")
    public ResponseEntity<?> getOneBank(@PathVariable String id) {
        return new ResponseEntity<>(bankRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addOneBank(@PathVariable Bank bank) {
        return new ResponseEntity<>(bankRepository.findAll(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> removeOneBank(@PathVariable String id) {
        return new ResponseEntity<>(bankRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/{nationalId}")
    public ResponseEntity<?> addClient(@PathVariable long nationalId, @RequestBody Account account) {
        ;
        Client client = clientRepository.findClientByNationalId(nationalId);
        if (client == null) return new ResponseEntity<>(SysMsg.NOT_FOUND, HttpStatus.OK);
        Account fixedAccount = returnNewAccount(client, account);
        client.getAccounts().add(fixedAccount);
        accountRepository.insert(fixedAccount);
        clientRepository.save(client);
        return new ResponseEntity<>(SysMsg.SUCCESSFUL, HttpStatus.OK);
    }

    @PostMapping("/{nationalId}/{accountId}")
    public ResponseEntity<?> removeClient(@PathVariable long nationalId, @PathVariable long accountId) {
        Client client = clientRepository.findClientByNationalId(nationalId);
        if (client == null) return new ResponseEntity<>(SysMsg.NOT_FOUND, HttpStatus.OK);
        System.out.println(client.getAccounts());
        client.getAccounts().removeIf(account -> account.getAccountNumber() == accountId);
        clientRepository.save(client);
        return new ResponseEntity<>(SysMsg.SUCCESSFUL, HttpStatus.OK);
    }

    @GetMapping("/{branchCode}/reports/totalMoney")
    public ResponseEntity<?> getTotalMoney(@PathVariable int branchCode) {
        Bank bank = bankRepository.findBankByBranchCode(branchCode);
        if (bank == null) return new ResponseEntity<>(SysMsg.NOT_FOUND, HttpStatus.OK);
        double totalMoney = bank.getClients().getAccounts().stream().map(Account::getCurrentAmount).mapToDouble(Double::doubleValue).sum();
        List<Account> accounts = new ArrayList<>();

        return new ResponseEntity<>(totalMoney, HttpStatus.OK);
    }

    @GetMapping("/reports/allAccounts")
    public ResponseEntity<?> getAllAccounts(@PathVariable long nationalId) {
        Client client = clientRepository.findClientByNationalId(nationalId);
        if (client == null) return new ResponseEntity<>(SysMsg.NOT_FOUND, HttpStatus.OK);
        return new ResponseEntity<>(client.getAccounts(), HttpStatus.OK);
    }

}
