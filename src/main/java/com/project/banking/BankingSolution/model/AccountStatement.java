package com.project.banking.BankingSolution.model;

import com.project.banking.BankingSolution.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountStatement {
    private String accountNumber;
    List<Transaction> transactionHistory;
}
