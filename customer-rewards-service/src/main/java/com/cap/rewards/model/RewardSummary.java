package com.cap.rewards.model;

import java.time.Month;
import java.util.Map;

public record RewardSummary(
        Map<Month, Integer> monthlyPoints,
        int totalPoints
) {}
