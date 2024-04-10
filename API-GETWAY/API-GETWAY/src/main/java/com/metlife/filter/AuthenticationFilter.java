//package com.metlife.filter;
//
//import com.metlife.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Component
//public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
//
//    @Autowired
//    private RouteValidator validator;
//
//    @Autowired
//    private RestTemplate template;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    public AuthenticationFilter() {
//        super(Config.class);
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return ((exchange, chain) -> {
//            if (validator.isSecured.test(exchange.getRequest())) {
//                HttpHeaders headers = exchange.getRequest().getHeaders();
//                if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
//                    return unauthorized(exchange, "Missing authorization header");
//                }
//
//                String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
//                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//                    return unauthorized(exchange, "Invalid authorization header format");
//                }
//
//                String token = authHeader.substring(7);
//                try {
//                    // Make REST call to validate the token
//                    String response = template.getForObject("http://localhost:8080/api/users/validate?token=" + token, String.class);
//                    // Validate token locally if needed
//                    jwtUtil.validateToken(token);
//                } catch (Exception e) {
//                    return unauthorized(exchange, "Invalid access: " + e.getMessage());
//                }
//            }
//            return chain.filter(exchange);
//        });
//    }
//
//    private Mono<Void> unauthorized(ServerWebExchange exchange, String message) {
//        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//        exchange.getResponse().getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
//        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
//                .bufferFactory().wrap(message.getBytes())));
//    }
//
//    public static class Config {
//        // No configuration properties needed for now
//    }
//}
