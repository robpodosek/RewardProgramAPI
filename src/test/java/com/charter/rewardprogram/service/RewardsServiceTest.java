package com.charter.rewardprogram.service;

import com.charter.rewardprogram.entity.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
public class RewardsServiceTest {

    private final RewardsServiceImpl rewardsService = new RewardsServiceImpl();

    /*
     * A customer receives 2 points for every dollar spent over $100 in each
     * transaction, plus 1 point for every dollar spent between $50 and $100 in each
     * transaction.
     * (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
     * Therefore we need to test all boundary conditions.
     */

    @Test
    public void testCalculateRewards_SpendingUnder50() {
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(30));
        int points = rewardsService.calculateRewards(transaction);
        assertEquals(0, points);
    }

    @Test
    public void testCalculateRewards_SpendingExactly50() {
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(50));
        int points = rewardsService.calculateRewards(transaction);
        assertEquals(0, points);
    }

    @Test
    public void testCalculateRewards_SpendingBetween50And100() {
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(70));
        int points = rewardsService.calculateRewards(transaction);
        assertEquals(20, points);
    }

    @Test
    public void testCalculateRewards_SpendingExactly100() {
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(100));
        int points = rewardsService.calculateRewards(transaction);
        assertEquals(50, points);
    }

    @Test
    public void testCalculateRewards_SpendingOver100() {
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(120));
        int points = rewardsService.calculateRewards(transaction);
        assertEquals(90, points);
    }

    @Test
    public void testCalculateRewards_SpendingExactly200() {
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(200));
        int points = rewardsService.calculateRewards(transaction);
        assertEquals(250, points);
    }

    @Test
    public void testCalculateRewards_SpendingOver200() {
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(220));
        int points = rewardsService.calculateRewards(transaction);
        assertEquals(290, points);
    }
}