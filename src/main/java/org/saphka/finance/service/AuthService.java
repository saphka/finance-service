package org.saphka.finance.service;

import org.saphka.finance.model.TokenResponse;
import org.saphka.finance.model.UserAuthRequest;
import reactor.core.publisher.Mono;

public interface AuthService {

    Mono<TokenResponse> issueToken(UserAuthRequest userAuthRequest);

}
