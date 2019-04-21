package com.pinin.alex.repository;

import com.pinin.alex.document.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, String> {

    Optional<Account> findByLogin(String login);
}
