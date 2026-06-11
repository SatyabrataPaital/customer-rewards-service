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

    public Map<String, RewardSummary> calculateRewards(List<Transaction> transactions) {
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCustomerName))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> computeMonthlyRewards(e.getValue())
                ));
    }

    private RewardSummary computeMonthlyRewards(List<Transaction> transactions) {
        Map<Month, Integer> monthlyPoints = transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getDate().getMonth(),
                        Collectors.summingInt(this::calculatePoints)
                ));

        int totalPoints = monthlyPoints.values().stream().mapToInt(Integer::intValue).sum();
        return new RewardSummary(monthlyPoints, totalPoints);
    }

    private int calculatePoints(Transaction t) {
        double amount = t.getAmount();
        int points = 0;
        if (amount > 100) points += (int) ((amount - 100) * 2);
        if (amount > 50) points += (int) (Math.min(amount, 100) - 50);
        return points;
    }
}