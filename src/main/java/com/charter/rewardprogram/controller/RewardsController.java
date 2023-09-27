package com.charter.rewardprogram.controller;

import com.charter.rewardprogram.entity.Customer;
import com.charter.rewardprogram.entity.Transaction;
import com.charter.rewardprogram.exception.ResourceNotFoundException;
import com.charter.rewardprogram.repository.CustomerRepository;
import com.charter.rewardprogram.repository.TransactionRepository;
import com.charter.rewardprogram.service.RewardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rewards")
@RequiredArgsConstructor
public class RewardsController {
    private final TransactionRepository transactionRepository;
    private final RewardsService rewardsService;
    private final CustomerRepository customerRepository;

    @GetMapping("/{customerId}")
    public int getRewards(@PathVariable Long customerId, @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));

        List<Transaction> transactions = transactionRepository.findByCustomerAndDateBetween(customer, startDate,
                endDate);
        int totalRewards = 0;
        for (Transaction transaction : transactions) {
            totalRewards += rewardsService.calculateRewards(transaction);
        }
        return totalRewards;
    }
}
