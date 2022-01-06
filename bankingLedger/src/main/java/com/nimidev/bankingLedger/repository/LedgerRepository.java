package com.nimidev.bankingLedger.repository;

import com.nimidev.bankingLedger.entity.Ledger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LedgerRepository extends CrudRepository<Ledger, String> {
    List<Ledger> findAllByAccountNoOrderByDateDesc(String accountNo);
    Ledger findTopByAccountNoOrderByDateDesc(String accountNo);
}
