package com.fitness.gateway;

import com.fitness.gateway.user.RegisterRequest;
import com.fitness.gateway.user.UserService;
import com.fitness.gateway.user.mappers.JWTtoRequest;
import com.fitness.gateway.user.mappers.UserToResponse;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class KeycloakUserSyncFilter implements WebFilter {

    private final UserService userService;

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange , final WebFilterChain chain) {
        String userId = exchange.getRequest()
                .getHeaders()
                .getFirst("X-User-ID");
        String token = exchange.getRequest()
                .getHeaders()
                .getFirst("Authorization");

        if(userId != null &&  token != null) {
            return userService.validateUser(userId)
                    .flatMap(exist -> {
                        if(!exist) {
                            // Register User
                            RegisterRequest registerRequest = getUserDetails(token);
                            if(registerRequest != null) {
                                return userService.registerUser(registerRequest)
                                        .then(Mono.empty());
                            }else {
                                return Mono.empty();
                            }
                        } else  {
                            log.info("User {} already exist , Skipping Sync", userId);
                            return Mono.empty();
                        }
                    })
                    .then(Mono.defer(() -> {
                        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                                .header("X-User-ID" , userId)
                                .build();
                        return chain.filter(exchange.mutate().request(mutatedRequest).build());
                    }));
        }

    }

    private RegisterRequest getUserDetails(final String token) {
        try {
            String tokenWithoutBearer = token.replace("Bearer ", "").trim();
            SignedJWT signedJWT = SignedJWT.parse(tokenWithoutBearer);
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
            return JWTtoRequest.jwtToRequest(claims);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
