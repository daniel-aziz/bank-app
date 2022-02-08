package com.jb.bankapp.Repositories;

import com.jb.bankapp.Beans.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClientRepository extends MongoRepository<Client, String> {

    Optional<Client> findClientByNationalId(long id);


}