package com.jb.bankapp.Services;

import com.jb.bankapp.Beans.Bank;
import com.jb.bankapp.Repositories.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService {
    private final BankRepository bankRepository;

    public void createBank(Bank bank) throws Exception {
        try {
            bankRepository.insert(bank);
            bankRepository.exists()
        } catch (Exception err) {
            throw new Exception(err.getMessage());
        }
    }



    public List<Bank> getAllBanks() {
        return bankRepository.findAll();

    }

}
