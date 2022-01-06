package com.nimidev.bankingLedger.error;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String id){
        super("Could not find account " + id);
    }
}
