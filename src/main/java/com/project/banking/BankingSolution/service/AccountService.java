package com.project.banking.BankingSolution.service;

import com.project.banking.BankingSolution.entity.Account;
import com.project.banking.BankingSolution.entity.Transaction;
import com.project.banking.BankingSolution.exception.AccountException;
import com.project.banking.BankingSolution.model.AccountStatement;
import com.project.banking.BankingSolution.model.Transfer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {
    String save(Account account);
    Long sendMoney(Transfer transfer);
    AccountStatement getStatement(String accountNumber);

}
