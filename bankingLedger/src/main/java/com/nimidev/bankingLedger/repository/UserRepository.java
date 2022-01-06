package com.nimidev.bankingLedger.repository;

import com.nimidev.bankingLedger.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}
