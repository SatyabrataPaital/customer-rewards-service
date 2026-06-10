package com.cap.rewards.controller;

import com.cap.rewards.model.RewardSummary;
import com.cap.rewards.model.Transaction;
import com.cap.rewards.repository.TransactionRepository;
import com.cap.rewards.service.RewardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private final RewardService rewardService;
    private final TransactionRepository transactionRepository;

    public RewardController(RewardService rewardService, TransactionRepository transactionRepository) {
        this.rewardService = rewardService;
        this.transactionRepository = transactionRepository;
    }

    // Calculate rewards from request body, trying to input manualy
    @PostMapping("/calculate")
    public Map<String, RewardSummary> calculate(@RequestBody List<Transaction> transactions) {
        return rewardService.calculateRewards(transactions);
    }

    // Calculating here rewards for all transactions in DB
    @GetMapping("/calculate/all")
    public Map<String, RewardSummary> calculateAll() {
        List<Transaction> transactions = transactionRepository.findAll();
        return rewardService.calculateRewards(transactions);
    }
}