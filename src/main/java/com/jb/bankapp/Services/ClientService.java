package com.jb.bankapp.Services;

import com.jb.bankapp.Beans.Account;
import com.jb.bankapp.Repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<Account> getAllAccounts(long nationalId) {
       return clientRepository.findClientByNationalId(nationalId).get().getAccounts();
    }



}
