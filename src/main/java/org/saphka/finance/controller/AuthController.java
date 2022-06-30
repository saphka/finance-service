package org.saphka.finance.controller;

import lombok.RequiredArgsConstructor;
import org.saphka.finance.model.TokenResponse;
import org.saphka.finance.model.UserAuthRequest;
import org.saphka.finance.model.UserDto;
import org.saphka.finance.service.AuthService;
import org.saphka.finance.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    private final UserService userService;

    @PostMapping("token")
    Mono<TokenResponse> issueToken(@RequestBody @Valid UserAuthRequest userAuthRequest) {
        return authService.issueToken(userAuthRequest);
    }

    @PostMapping("register")
    Mono<UserDto> registerUser(@RequestBody @Valid UserAuthRequest userAuthRequest) {
        return userService.registerUser(userAuthRequest);
    }

}
