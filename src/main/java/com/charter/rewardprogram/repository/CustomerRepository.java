package com.charter.rewardprogram.repository;

import com.charter.rewardprogram.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
