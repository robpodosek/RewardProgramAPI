package com.charter.rewardprogram.service;

import com.charter.rewardprogram.entity.Transaction;

public interface RewardsService {
    int calculateRewards(Transaction transaction);
}
