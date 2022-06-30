package org.saphka.finance.model;

import java.util.UUID;

public record UserDto(UUID id, String login, String password) {
}
