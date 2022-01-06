package com.nimidev.bankingLedger.error;

public class LedgerException extends RuntimeException {
    public LedgerException(String message) {
        super(message);
    }
}
