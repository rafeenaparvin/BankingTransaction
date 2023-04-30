package com.project.banking.BankingSolution.service;

import com.project.banking.BankingSolution.entity.Account;
import com.project.banking.BankingSolution.repository.AccountRepository;
import com.project.banking.BankingSolution.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AccountServiceTest {
    @Mock
    AccountRepository accountRepository;
    @Autowired
    @InjectMocks
    AccountServiceImpl accountService;
    private
    Account account;
    @BeforeEach
    public void setup(){
        account = new Account().builder()
                .accountId(1l)
                .accountNumber("BS0001")
                .balance(BigDecimal.valueOf(5000))
                .build();
    }

    @Test
    public void saveAccount_thenReturnAccount(){
       when(accountRepository.save(any())).thenReturn(account);
       String accNo = accountService.save(account);
       assertEquals("BS0001",accNo);
    }



}
