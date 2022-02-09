package com.jb.bankapp.CLR;

import com.jb.bankapp.Beans.*;
import com.jb.bankapp.Beans.Enums.ClientType;
import com.jb.bankapp.Beans.Enums.Status;
import com.jb.bankapp.Repositories.AccountRepository;
import com.jb.bankapp.Repositories.BankRepository;
import com.jb.bankapp.Repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;


//@Component
@RequiredArgsConstructor
public class Test1 implements CommandLineRunner {
    private final AccountRepository accountRepository;

    private final ClientRepository clientRepository;
    private final BankRepository bankRepository;

    @Override
    public void run(String... args) throws Exception {
        Account account = Account.builder()
                .id(UUID.randomUUID().toString())
                .currentAmount(0)
                .status(Status.ALLOWED).build();

        accountRepository.save(account);

        Client client = Client.builder()
                .id(UUID.randomUUID().toString())
                .firstName("Daniel")
                .lastName("Kola")
                .nationalId(1101010115)
                .dateOfBirth(LocalDate.of(2000, 11, 8))
                .nationality("Israel")
                .clientType(ClientType.PLATINUM)
                .accounts(Arrays.asList(account))
                .build();

    clientRepository.save(client);

        Bank bank = Bank.builder()
                .id(UUID.randomUUID().toString())
                .name("Yahav")
                .city("TLV")
                .clients(Arrays.asList(client)).branchCode(972).build();

        bankRepository.save(bank);


    }
}
