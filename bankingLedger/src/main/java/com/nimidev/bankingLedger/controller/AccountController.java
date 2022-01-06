package com.nimidev.bankingLedger.controller;

import com.nimidev.bankingLedger.entity.Account;
import com.nimidev.bankingLedger.entity.User;
import com.nimidev.bankingLedger.service.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {
    @Autowired
    AccountService accountService;

    @ApiOperation(value = "Create account")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Account createAccount (@RequestBody Account account) {
        return accountService.createAccount(account);
    }
}
