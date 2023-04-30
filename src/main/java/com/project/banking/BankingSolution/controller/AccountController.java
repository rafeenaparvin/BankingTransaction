package com.project.banking.BankingSolution.controller;

import com.project.banking.BankingSolution.entity.Account;
import com.project.banking.BankingSolution.entity.Transaction;
import com.project.banking.BankingSolution.exception.AccountException;
import com.project.banking.BankingSolution.model.AccountStatement;
import com.project.banking.BankingSolution.model.Transfer;
import com.project.banking.BankingSolution.service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;


@RestController
@RequestMapping(value = "/banking/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/create")
    @ApiOperation(value = "Create new account")
    @ApiResponses(
            {
                    @ApiResponse(
                            code = 200,
                            message = "Account created",
                            response = Account.class
                    )
            }
    )
    public ResponseEntity create(@Validated @RequestBody Account account){
        String accountNo = accountService.save(account);
        return new ResponseEntity<>("Account created with acc no" + accountNo, HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/transfer")
    @ApiOperation("Transfer money from one account to another")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Money transfer successful",
                    responseContainer = "List",
                    response = Transaction.class
            )
    })
    public ResponseEntity sendMoney(@Validated @RequestBody Transfer transfer){
        Long id = accountService.sendMoney(transfer);
        return new ResponseEntity<>("Transaction Successful - Transaction Number is " + id,HttpStatus.OK);
    }

    @PostMapping(value = "/statement/{Id}")
    @ApiOperation("Get statement of a account")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Received statement",
                    response = AccountStatement.class
            )
    })
    public ResponseEntity getStatement(@RequestParam String accountNumber){
        AccountStatement accountStatement = accountService.getStatement(accountNumber);
        return ResponseEntity.ok().body(accountStatement);
    }


    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
