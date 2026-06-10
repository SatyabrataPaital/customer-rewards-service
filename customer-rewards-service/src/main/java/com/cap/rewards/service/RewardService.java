package com.cap.rewards.service;



import com.cap.rewards.model.RewardSummary;
import com.cap.rewards.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardService {

    private static final Logger log = LoggerFactory.getLogger(RewardService.class);

    public Map<String, RewardSummary> calculateRewards(List<Transaction> transactions) {
        log.info("Calculating rewards for {} transactions", transactions.size());

        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::customerName))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> computeMonthlyRewards()
                ));
    }

    private RewardSummary computeMonthlyRewards() {
        return null;
    }

   private int calculatePoints(Transaction t) {
        return 10;
    }
}
