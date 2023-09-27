package com.charter.rewardprogram.repository;

import com.charter.rewardprogram.entity.Customer;
import com.charter.rewardprogram.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCustomerAndDateBetween(Customer customer, LocalDate startDate, LocalDate endDate);
}
