package com.jb.bankapp.Controllers;

import com.jb.bankapp.Beans.Account;
import com.jb.bankapp.Beans.Client;
import com.jb.bankapp.Beans.Enums.SysMsg;
import com.jb.bankapp.Repositories.AccountRepository;
import com.jb.bankapp.Repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/client")
@RequiredArgsConstructor
public class ClientController {
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    @PostMapping("/{nationalId}")
    public ResponseEntity<?> addAccount(@PathVariable String nationalId, @RequestBody Account account) {
        Client client = clientRepository.findClientByNationalId(nationalId);
        if (client == null) return new ResponseEntity<>(SysMsg.NOT_FOUND, HttpStatus.OK);
        Account fixedAccount = returnNewAccount(client, account);
        client.getAccounts().add(fixedAccount);
        accountRepository.insert(fixedAccount);
        clientRepository.save(client);
        return new ResponseEntity<>(SysMsg.SUCCESSFUL, HttpStatus.OK);
    }

    @DeleteMapping("/{nationalId}/{accountId}")
    public ResponseEntity<?> removeAccount(@PathVariable String nationalId, @PathVariable String accountId) {
        Client client = clientRepository.findClientByNationalId(nationalId);
        if (client == null) return new ResponseEntity<>(SysMsg.NOT_FOUND, HttpStatus.OK);
        accountRepository.delete(accountRepository.findAccountByAccountNumber(accountId));
        client.getAccounts().removeIf(account -> account.getAccountNumber().equals(accountId));
        clientRepository.save(client);
        return new ResponseEntity<>(SysMsg.SUCCESSFUL, HttpStatus.OK);
    }

    @GetMapping("/{nationalId}/reports/totalMoney")
    public ResponseEntity<?> getTotalMoney(@PathVariable String nationalId) {
        Client client = clientRepository.findClientByNationalId(nationalId);
        if (client == null) return new ResponseEntity<>(SysMsg.NOT_FOUND, HttpStatus.OK);
        double totalMoney = client.getAccounts().stream().map(Account::getCurrentAmount).mapToDouble(Double::doubleValue).sum();
        return new ResponseEntity<>(totalMoney, HttpStatus.OK);
    }

    @GetMapping("/{nationalId}/reports/allAccounts")
    public ResponseEntity<?> getAllAccounts(@PathVariable String nationalId) {
        Client client = clientRepository.findClientByNationalId(nationalId);
        if (client == null) return new ResponseEntity<>(SysMsg.NOT_FOUND, HttpStatus.OK);
        return new ResponseEntity<>(client.getAccounts(), HttpStatus.OK);
    }


    private static Account returnNewAccount(Client client, Account account) {
        account.setId(UUID.randomUUID().toString());
        if (client.getAccounts().size() > 0) {
            String lastAccountNumber = client.getAccounts().get(client.getAccounts().size() - 1).getAccountNumber();
            //account.setAccountNumber(lastAccountNumber + 1);
            account.setAccountNumber((Long.parseLong(lastAccountNumber)+1)+"");
        } else {
           // account.setAccountNumber((client.getNationalId() + "100") + 1);
            account.setAccountNumber((Long.parseLong((client.getNationalId() + "100")) + 1)+"");
        }
        return account;
    }


}
