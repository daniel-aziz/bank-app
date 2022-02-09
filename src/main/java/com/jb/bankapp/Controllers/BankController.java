package com.jb.bankapp.Controllers;

import com.jb.bankapp.Beans.Account;
import com.jb.bankapp.Beans.Bank;
import com.jb.bankapp.Beans.Client;
import com.jb.bankapp.Beans.Enums.SysMsg;
import com.jb.bankapp.Repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/bank")
@RequiredArgsConstructor
public class BankController {
    private final BankRepository bankRepository;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    @GetMapping("/allBanks")
    public ResponseEntity<?> getAllBanks() {
        return new ResponseEntity<>(bankRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getBank/{branchCode}")
    public ResponseEntity<?> getBank(@PathVariable int branchCode) {
        if (!bankRepository.existsByBranchCode(branchCode))
            return new ResponseEntity<>(SysMsg.NOT_FOUND, HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(bankRepository.findBankByBranchCode(branchCode), HttpStatus.OK);
    }

    @PostMapping("/addOneBank")
    public ResponseEntity<?> addBank(@RequestBody Bank bank) {
        if (bankRepository.existsByBranchCode(bank.getBranchCode())) {
            return new ResponseEntity<>(SysMsg.FAILED, HttpStatus.BAD_REQUEST);
        }
        bank.setId(UUID.randomUUID().toString());
        bankRepository.insert(bank);
        return new ResponseEntity<>(SysMsg.SUCCESSFUL, HttpStatus.OK);
    }

    @DeleteMapping("/deleteBank/{branchCode}")
    public ResponseEntity<?> removeBank(@PathVariable int branchCode) {
        if (bankRepository.existsByBranchCode(branchCode)) {
            for (Client client : bankRepository.findBankByBranchCode(branchCode).getClients()) {
                for (Account account : client.getAccounts()) {
                    accountRepository.delete(account);
                }
                clientRepository.delete(client);
            }
            bankRepository.delete(bankRepository.findBankByBranchCode(branchCode));
            return new ResponseEntity<>(SysMsg.SUCCESSFUL, HttpStatus.OK);
        }
        return new ResponseEntity<>(SysMsg.NOT_FOUND, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/addClient/{branchCode}")
    public ResponseEntity<?> addClient(@PathVariable int branchCode, @RequestBody Client client) {
        if (clientRepository.existsByNationalId(client.getNationalId()))
            return new ResponseEntity<>(SysMsg.FAILED, HttpStatus.OK);
        client.setId(UUID.randomUUID().toString());
        clientRepository.insert(client);
        Bank thisBank = bankRepository.findBankByBranchCode(branchCode);
        thisBank.getClients().add(client);
        bankRepository.save(thisBank);
        return new ResponseEntity<>(SysMsg.SUCCESSFUL, HttpStatus.OK);
    }

    @DeleteMapping("/removeClient/{branchCode}/{nationalId}")
    public ResponseEntity<?> removeClient(@PathVariable int branchCode, @PathVariable long nationalId) {
        if (!clientRepository.existsByNationalId(nationalId) || !bankRepository.existsByBranchCode(branchCode))
            return new ResponseEntity<>(SysMsg.NOT_FOUND, HttpStatus.OK);
        Client client = clientRepository.findClientByNationalId(nationalId);
        for (Account account : client.getAccounts()) {
            accountRepository.delete(account);
        }
        clientRepository.delete(client);
        return new ResponseEntity<>(SysMsg.SUCCESSFUL, HttpStatus.OK);
    }


    @GetMapping("/reports/totalMoney/{branchCode}/")
    public ResponseEntity<?> getTotalMoney(@PathVariable int branchCode) {
        double totalMoney = 0;
        Bank bank = bankRepository.findBankByBranchCode(branchCode);
        if (bank == null) return new ResponseEntity<>(SysMsg.NOT_FOUND, HttpStatus.OK);
        for (Client client : bank.getClients()) {
            totalMoney += client.getAccounts().stream().map(Account::getCurrentAmount).mapToDouble(Double::doubleValue).sum();
        }
        return new ResponseEntity<>(totalMoney, HttpStatus.OK);
    }

    @GetMapping("/reports/allAccounts/{branchCode}/")
    public ResponseEntity<?> getAllAccounts(@PathVariable int branchCode) {
        List<Account> accounts = new ArrayList<>();
        Bank bank = bankRepository.findBankByBranchCode(branchCode);
        if (bank == null) return new ResponseEntity<>(SysMsg.NOT_FOUND, HttpStatus.OK);
        for (Client client : bank.getClients()) {
            accounts.addAll(client.getAccounts());
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

}
