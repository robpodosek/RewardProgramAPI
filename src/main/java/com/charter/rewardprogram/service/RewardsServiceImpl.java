package com.charter.rewardprogram.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.charter.rewardprogram.entity.Transaction;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RewardsServiceImpl implements RewardsService {
    /*
     * A customer receives 2 points for every dollar spent over $100 in each
     * transaction, plus 1 point for every dollar spent between $50 and $100 in each
     * transaction.
     * (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
     */
    @Override
    public int calculateRewards(Transaction transaction) {
        BigDecimal amount = transaction.getAmount();
        int points = 0;
        if (amount.compareTo(BigDecimal.valueOf(100)) > 0) {
            // 2 points for every dollar OVER 100 (hence the substraction before
            // multiplication)
            points += (amount.subtract(BigDecimal.valueOf(100)).intValue() * 2);

            // Use Math.min, e.g. if they only spend $75 they'll only get 75-50 = 20 points,
            // whereas if they spent $120 they'll get 120-50 = 70 > 50, ergo will only
            // receive the min 50.
            points += Math.min(amount.subtract(BigDecimal.valueOf(50)).intValue(), 50);
        }
        return points;
    }
}
