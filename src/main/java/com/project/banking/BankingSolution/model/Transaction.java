package com.project.banking.BankingSolution.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private String currency;
    private BigDecimal amount;
    private String sourceAccountId;
}
