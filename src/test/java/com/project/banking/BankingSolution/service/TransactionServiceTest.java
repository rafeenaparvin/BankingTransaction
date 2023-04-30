package com.project.banking.BankingSolution.service;

import com.project.banking.BankingSolution.entity.Account;
import com.project.banking.BankingSolution.model.Transaction;
import com.project.banking.BankingSolution.repository.AccountRepository;
import com.project.banking.BankingSolution.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    AccountRepository accountRepository;
    @Autowired
    @InjectMocks
    TransactionServiceImpl transactionService;
    Account account;
    Transaction transaction;
    @BeforeEach
    public void setup(){
        account = new Account(1l,"BS00001", BigDecimal.valueOf(5000));
        transaction = new Transaction("USD",BigDecimal.valueOf(5000),"BS00001");
    }
    @Test
    public void creditMoneyTest(Transaction transaction){
        Account account1 = accountRepository.save(account);
        account.setBalance(account.getBalance().add(transaction.getAmount()));
        transactionService.creditMoney(transaction);
        assertEquals(account.getBalance().add(transaction.getAmount()), BigDecimal.valueOf(1000));

    }
}
