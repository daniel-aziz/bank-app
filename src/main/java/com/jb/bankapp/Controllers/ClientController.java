package com.jb.bankapp.Controllers;

import com.jb.bankapp.Beans.Account;
import com.jb.bankapp.Beans.Client;
import com.jb.bankapp.Repositories.ClientRepository;
import com.jb.bankapp.Services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/{nationalId}/accounts")
    public List<Account> getClientAccounts(@PathVariable long nationalId) {
        return clientService.getAllAccounts(nationalId);
    }


}
