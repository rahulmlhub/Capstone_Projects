//package com.metlife.filter;
//
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.function.Predicate;
//
//@Component
//public class RouteValidator {
//
//    private static final Set<String> AUTHENTICATED_PATHS = new HashSet<>(Arrays.asList(
//            "/api/users/**",
//            "/api/hotels/**",
//            "/api/guests/**",
//            "/api/reviews/**",
//            "/api/bookings/**"
//    ));
//
//    public Predicate<ServerHttpRequest> isSecured = request -> {
//        for (String path : AUTHENTICATED_PATHS) {
//            if (request.getURI().getPath().startsWith(path)) {
//                return true; // Path requires authentication
//            }
//        }
//        return false; // Path does not require authentication
//    };
//}
