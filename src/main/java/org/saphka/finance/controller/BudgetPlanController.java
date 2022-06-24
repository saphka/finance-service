package org.saphka.finance.controller;

import lombok.RequiredArgsConstructor;
import org.saphka.finance.model.BudgetPlanDto;
import org.saphka.finance.service.BudgetPlanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("plans")
public class BudgetPlanController {

    private final BudgetPlanService service;

    @GetMapping
    public Flux<BudgetPlanDto> findAll() {
        return service.findAll();
    }

}
