package com.jb.bankapp.Repositories;

import com.jb.bankapp.Beans.Bank;
import com.jb.bankapp.Beans.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BankRepository extends MongoRepository<Bank, String> {

    Bank findBankByBranchCode(int branchCode);

    boolean existsByBranchCode(int branchCode);

}
