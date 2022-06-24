package org.saphka.finance.model;

import org.saphka.finance.domain.BudgetPlan;

import java.util.UUID;

public record BudgetPlanDto(UUID id, String description) {

    public static BudgetPlanDto from(BudgetPlan entity) {
        return new BudgetPlanDto(entity.getId(),
                entity.getDescription());
    }
}
