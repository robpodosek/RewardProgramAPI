package com.charter.rewardprogram.repository;

import com.charter.rewardprogram.entity.Customer;
import com.charter.rewardprogram.entity.Transaction;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findByCustomerAndDateBetween(Customer customer, LocalDate startDate, LocalDate endDate);
}
