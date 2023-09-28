package com.charter.rewardprogram.repository;

import com.charter.rewardprogram.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
