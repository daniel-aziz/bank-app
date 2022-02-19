package com.jb.bankapp.Repositories;

import com.jb.bankapp.Beans.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {

    Account findAccountByAccountNumber(String accountNumber);
}
