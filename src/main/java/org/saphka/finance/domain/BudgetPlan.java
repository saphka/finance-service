package org.saphka.finance.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BudgetPlan {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    private String description;

}
