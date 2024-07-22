package com.bank.authcore.repository;

import com.bank.authcore.repository.model.AccountModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthAccountRepository extends MongoRepository<AccountModel, String> {
}
