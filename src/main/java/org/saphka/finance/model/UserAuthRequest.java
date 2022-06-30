package org.saphka.finance.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UserAuthRequest(
        @NotNull @Email @Size(max = 100)
        String login,
        @NotNull @NotEmpty @Size(min = 8, max = 30)
        String password
) {
}
