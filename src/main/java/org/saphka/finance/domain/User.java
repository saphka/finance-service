package org.saphka.finance.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table("user_table")
public class User {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    private String login;

    private String encodedPassword;
}
