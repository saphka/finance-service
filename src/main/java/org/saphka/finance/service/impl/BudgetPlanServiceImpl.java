package org.saphka.finance.service.impl;

import lombok.RequiredArgsConstructor;
import org.saphka.finance.model.BudgetPlanDto;
import org.saphka.finance.repository.BudgetPlanRepository;
import org.saphka.finance.service.BudgetPlanService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class BudgetPlanServiceImpl implements BudgetPlanService {

    private final BudgetPlanRepository repository;

    @Override
    public Flux<BudgetPlanDto> findAll() {
        return repository.findAll().map(BudgetPlanDto::from);
    }
}
