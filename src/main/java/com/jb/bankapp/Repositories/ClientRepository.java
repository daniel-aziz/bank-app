package com.jb.bankapp.Repositories;

import com.jb.bankapp.Beans.Client;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ClientRepository extends MongoRepository<Client, String> {

    Client findClientByNationalId(long nationalId);

    boolean existsByNationalId(long nationalId);

}
