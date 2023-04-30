package com.project.banking.BankingSolution.service;

import com.project.banking.BankingSolution.model.Transaction;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

public interface TransactionService {
    void creditMoney(Transaction transaction);
    void debitMoney(Transaction transaction);
    BigDecimal convertMoney(Transaction transaction);
}
