package com.nimidev.bankingLedger.controller;

import com.nimidev.bankingLedger.entity.Ledger;
import com.nimidev.bankingLedger.service.LedgerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path="/ledger")
public class LedgerController {

    @Autowired
    LedgerService ledgerService;

    @ApiOperation(value = "withdraw")
    @RequestMapping(value = "/withdraw/", method = RequestMethod.POST)
    @ResponseBody
    public Ledger withdraw(@RequestParam("amount") int amount,
                           @RequestParam("accountNo") String accountNo,
                           @RequestParam("remark") String remark) {
        return ledgerService.withDraw(amount, accountNo, remark);
    }

    @ApiOperation(value = "deposit")
    @RequestMapping(value = "/deposit/", method = RequestMethod.POST)
    @ResponseBody
    public Ledger deposit(@RequestParam("amount") int amount,
                           @RequestParam("accountNo") String accountNo,
                           @RequestParam("remark") String remark) {
        return ledgerService.deposit(amount, accountNo, remark);
    }

    @ApiOperation(value = "find all ledgers")
    @RequestMapping(value = "/find-all/", method = RequestMethod.GET)
    @ResponseBody
    public List<Ledger> findAll(@RequestParam("accountNo") String accountNo) {
        return ledgerService.findAllByAccountNo(accountNo);
    }

    @ApiOperation(value = "transfer")
    @RequestMapping(value = "/transfer/", method = RequestMethod.POST)
    @ResponseBody
    public Ledger transfer(@RequestParam("amount") int amount,
                                 @RequestParam("fromAccountNo") String fromAccountNo,
                                 @RequestParam("toAccountNo") String toAccountNo
    ) {
        return ledgerService.transfer(amount, fromAccountNo, toAccountNo);
    }
}
