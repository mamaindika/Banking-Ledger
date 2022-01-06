package com.nimidev.bankingLedger.repository;

import com.nimidev.bankingLedger.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

}
