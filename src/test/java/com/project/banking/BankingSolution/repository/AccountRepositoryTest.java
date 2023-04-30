package com.project.banking.BankingSolution.repository;

import com.project.banking.BankingSolution.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;
    private Account account;

    @BeforeEach
    public void setUp(){
        account = new Account(1l,"SB00001", BigDecimal.valueOf(5000));
    }

    @Test
    public void givenAccountToSaveShouldReturnSameAccount(){
        accountRepository.save(account);
        Account fetchedAccount = accountRepository.findByAccountNumber(account.getAccountNumber());
        assertEquals("1l",fetchedAccount.getAccountId());
    }
}
