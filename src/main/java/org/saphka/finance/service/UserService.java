package org.saphka.finance.service;

import org.saphka.finance.model.UserAuthRequest;
import org.saphka.finance.model.UserDto;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserDto> registerUser(UserAuthRequest userRequest);

    Mono<UserDto> findByLogin(String login);

}
