package com.nimidev.bankingLedger.service;

import com.nimidev.bankingLedger.entity.Ledger;
import com.nimidev.bankingLedger.error.AccountNotFoundException;
import com.nimidev.bankingLedger.repository.AccountRepository;
import com.nimidev.bankingLedger.types.TransactionType;
import com.nimidev.bankingLedger.error.LedgerException;
import com.nimidev.bankingLedger.repository.LedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class LedgerService {

    @Autowired
    LedgerRepository ledgerRepository;

    @Autowired
    AccountRepository accountRepository;

    public Ledger createLedger(Ledger ledger) {
        return ledgerRepository.save(ledger);
    }

    public Ledger ReadLedger(String id) {
        return ledgerRepository.findById(id).orElseThrow(() -> new LedgerException("Ledger not found"));
    }

    public Ledger findLast(String accountNo) {
        return ledgerRepository.findTopByAccountNoOrderByDateDesc(accountNo);
    }

    public List<Ledger> findAllByAccountNo(String accountNo) {
        return ledgerRepository.findAllByAccountNoOrderByDateDesc(accountNo);
    }

    @Transactional
    public Ledger withDraw(int amount, String accountNo, String remark) {
        accountRepository.findById(accountNo).orElseThrow(() -> new AccountNotFoundException(accountNo));
        Ledger lastTransaction = ledgerRepository.findTopByAccountNoOrderByDateDesc(accountNo);
        Ledger withdraw = new Ledger();
        if (lastTransaction == null || (lastTransaction.getBalance() - amount) < 0) {
            throw new LedgerException("Insufficient balance");
        }
        withdraw.setBalance(lastTransaction.getBalance() - amount);
        withdraw.setAccountNo(accountNo);
        withdraw.setDate(new Date());
        withdraw.setDetails(remark);
        withdraw.setTransactionType(TransactionType.WITHDRAW);
        withdraw.setAmount(amount);
        return ledgerRepository.save(withdraw);
    }

    @Transactional
    public Ledger deposit(int amount, String accountNo, String remark) {
        accountRepository.findById(accountNo).orElseThrow(() -> new AccountNotFoundException(accountNo));
        Ledger lastTransaction = ledgerRepository.findTopByAccountNoOrderByDateDesc(accountNo);
        Ledger deposit = new Ledger();
        if (lastTransaction == null) {
            deposit.setBalance((long) amount);
        } else {
            deposit.setBalance(lastTransaction.getBalance() + amount);
        }
        deposit.setAccountNo(accountNo);
        deposit.setDate(new Date());
        deposit.setDetails(remark);
        deposit.setTransactionType(TransactionType.DEPOSIT);
        deposit.setAmount(amount);

        return ledgerRepository.save(deposit);
    }

    public Ledger transfer(int amount, String fromAccountNo, String toAccountNo) {
        String messageFrom = amount + " sent to " + toAccountNo;
        String messageTo = amount + " received from " + fromAccountNo;
        Ledger from = null;
        Ledger to = withDraw(amount, fromAccountNo, messageFrom);
        if(to != null){
            from = deposit(amount, toAccountNo, messageTo);
        }
        return  from;
    }
}
