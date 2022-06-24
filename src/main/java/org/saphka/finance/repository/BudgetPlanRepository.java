package org.saphka.finance.repository;

import org.saphka.finance.domain.BudgetPlan;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface BudgetPlanRepository extends R2dbcRepository<BudgetPlan, UUID> {
}
