package com.pinin.alex.repository;

import com.pinin.alex.document.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface CustomCrudRepository<T> extends MongoRepository<T, String> {

    Optional<T> findByIdAndAccount(String id, Account account);

    List<T> findAllByAccount(Account account);

    int deleteByIdAndAccount(String id, Account account);
}
