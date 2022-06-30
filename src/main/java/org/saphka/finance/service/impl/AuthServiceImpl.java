package org.saphka.finance.service.impl;

import lombok.RequiredArgsConstructor;
import org.saphka.finance.exception.BaseException;
import org.saphka.finance.model.ErrorCode;
import org.saphka.finance.model.TokenResponse;
import org.saphka.finance.model.UserAuthRequest;
import org.saphka.finance.service.AuthService;
import org.saphka.finance.service.UserService;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtEncoder jwtEncoder;
    private final OAuth2ResourceServerProperties jwtProperties;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<TokenResponse> issueToken(UserAuthRequest userAuthRequest) {
        return userService.findByLogin(userAuthRequest.login())
                .flatMap(u -> passwordEncoder.matches(userAuthRequest.password(), u.password()) ?
                        Mono.just(u) :
                        Mono.error(() -> new BaseException(ErrorCode.BAD_PASSWORD, "Passwords do not match for user " + userAuthRequest.login()))
                )
                .map(userLogin -> JwtClaimsSet.builder()
                        .issuedAt(Instant.now())
                        .issuer(jwtProperties.getJwt().getIssuerUri())
                        .subject(userAuthRequest.login())
                        .build()
                )
                .map(JwtEncoderParameters::from)
                .map(jwtEncoder::encode)
                .map(AbstractOAuth2Token::getTokenValue)
                .map(TokenResponse::new);
    }
}
