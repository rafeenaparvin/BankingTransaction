package com.project.banking.BankingSolution.controller;

import com.project.banking.BankingSolution.model.Transaction;
import com.project.banking.BankingSolution.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/credit")
    public ResponseEntity creditMoney(@RequestBody Transaction transaction) {
        transactionService.creditMoney(transaction);
        return new ResponseEntity("Transfer is successful", HttpStatus.OK);
    }

    @PostMapping(value = "/debit")
    public ResponseEntity debitMoney(@RequestBody Transaction transaction) {
        transactionService.debitMoney(transaction);
        return new ResponseEntity("Transfer is successful", HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
