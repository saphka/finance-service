package org.saphka.finance.service;

import org.saphka.finance.model.BudgetPlanDto;
import reactor.core.publisher.Flux;

public interface BudgetPlanService {

    Flux<BudgetPlanDto> findAll();

}
