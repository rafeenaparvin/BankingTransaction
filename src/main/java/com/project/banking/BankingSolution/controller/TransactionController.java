package com.project.banking.BankingSolution.controller;

import com.project.banking.BankingSolution.model.Transaction;
import com.project.banking.BankingSolution.service.TransactionService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("credit the money to the given account ID")
    public ResponseEntity creditMoney(@RequestBody Transaction transaction) {
        transactionService.creditMoney(transaction);
        return new ResponseEntity("Transfer is successful", HttpStatus.OK);
    }

    @PostMapping(value = "/debit")
    @ApiOperation("Debit the money to the given account ID")
    public ResponseEntity debitMoney(@RequestBody Transaction transaction) {
        transactionService.debitMoney(transaction);
        return new ResponseEntity("Transfer is successful", HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
