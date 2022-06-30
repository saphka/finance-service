package org.saphka.finance.service.impl;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.saphka.finance.domain.User;
import org.saphka.finance.exception.BaseException;
import org.saphka.finance.model.ErrorCode;
import org.saphka.finance.model.UserAuthRequest;
import org.saphka.finance.model.UserDto;
import org.saphka.finance.repository.UserRepository;
import org.saphka.finance.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<UserDto> registerUser(UserAuthRequest userRequest) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setLogin(userRequest.login());
        user.setEncodedPassword(passwordEncoder.encode(userRequest.password()));
        return repository.save(user).map(this::mapToDto);
    }

    @Override
    public Mono<UserDto> findByLogin(String login) {
        return repository.findByLogin(login)
                .switchIfEmpty(
                        Mono.error(() -> new BaseException(ErrorCode.NOT_FOUND, "User not found by login " + login))
                ).map(this::mapToDto);
    }

    @NotNull
    private UserDto mapToDto(User u) {
        return new UserDto(
                u.getId(),
                u.getLogin(),
                u.getEncodedPassword());
    }
}
