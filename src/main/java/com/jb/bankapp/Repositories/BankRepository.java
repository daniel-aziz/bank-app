package com.jb.bankapp.Repositories;

import com.jb.bankapp.Beans.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankRepository extends MongoRepository<Bank, String> {

}
