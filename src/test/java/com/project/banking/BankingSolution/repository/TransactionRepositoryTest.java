package com.project.banking.BankingSolution.repository;

import com.project.banking.BankingSolution.entity.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TransactionRepositoryTest {
    @Autowired
    private TransactionRepository transactionRepository;
    private Transaction transaction;

    @BeforeEach
    public void setUp(){
        transaction = new Transaction(1l,"BS00001",BigDecimal.valueOf(5000),"USD","CREDIT");

    }

    @Test
    public void givenTransactionToSaveReturnSame(){
        transactionRepository.save(transaction);
        Transaction fetched = transactionRepository.findByAccountNumber(transaction.getAccountNumber()).get(0);
        assertEquals("BS00001",fetched.getAccountNumber());

    }
}
