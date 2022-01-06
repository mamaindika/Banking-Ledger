package com.nimidev.bankingLedger.service;

import com.nimidev.bankingLedger.entity.Account;
import com.nimidev.bankingLedger.entity.User;
import com.nimidev.bankingLedger.error.AccountNotFoundException;
import com.nimidev.bankingLedger.error.UserNotFoundException;
import com.nimidev.bankingLedger.repository.AccountRepository;
import com.nimidev.bankingLedger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    public Account createAccount(Account account){
        User user = userRepository.findById(account.getUserId()).orElseThrow(() -> new UserNotFoundException(account.getUserId()));
        return accountRepository.save(account);
    }

    public Account readUser(String id) {
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

    public Account updateUser(Account account, String id) {
        return accountRepository.findById(id)
                .map(a -> {
                  a.setAccountNumber(account.getAccountNumber());
                  a.setUserId(account.getUserId());
                    return accountRepository.save(a);
                }).orElseThrow(() -> new AccountNotFoundException(id));
    }

    public void deleteAccount(String id){
        accountRepository.deleteById(id);
    }
}
